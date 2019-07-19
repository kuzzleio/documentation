package io.kuzzle.test.core.KuzzleDataCollection;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Document;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.sdk.util.KuzzleJSONObject;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class updateDocumentTest {
  private Kuzzle kuzzle;
  private Collection collection;
  private ResponseListener listener;

  @Before
  public void setUp() throws URISyntaxException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);

    kuzzle = spy(extended);
    when(kuzzle.getHeaders()).thenReturn(new JSONObject());

    collection = new Collection(kuzzle, "test", "index");
    listener = mock(ResponseListener.class);
  }

  @Test
  public void checkSignaturesVariants() {
    JSONObject content = mock(JSONObject.class);
    String id = "foo";
    collection = spy(collection);

    collection.updateDocument(id, content);
    collection.updateDocument(id, content, mock(Options.class));
    collection.updateDocument(id, content, listener);

    verify(collection, times(3)).updateDocument(any(String.class), any(JSONObject.class), any(Options.class), any(ResponseListener.class));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testUpdateDocumentIllegalDocumentId() {
    collection.updateDocument(null, mock(JSONObject.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateDocumentIllegalContent() {
    collection.updateDocument("id", null);
  }

  @Test(expected = RuntimeException.class)
  public void testupdateDocumentQueryException() throws JSONException {
    doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    collection.updateDocument("id", mock(JSONObject.class), listener);
  }

  @Test(expected = RuntimeException.class)
  public void testupdateDocumentException() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject()));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    doThrow(JSONException.class).when(listener).onSuccess(any(String.class));
    collection.updateDocument("id", mock(JSONObject.class), listener);
  }

  @Test
  public void testUpdateDocument() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject()
          .put("result", new JSONObject()
              .put("_id", "42")
              .put("_version", 1337)
              .put("_source", new JSONObject())
              .put("_meta", new JSONObject())
          );
        if (invocation.getArguments()[3] != null) {
          ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
          ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
        }
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    Document doc = new Document(collection);
    collection.updateDocument("42", doc.serialize());
    collection.updateDocument("42", doc.serialize(), new Options());
    collection.updateDocument("42", doc.serialize(), new ResponseListener<Document>() {
      @Override
      public void onSuccess(Document document) {
        assertEquals(document.getId(), "42");
        assertEquals(document.getVersion(), 1337);
      }

      @Override
      public void onError(JSONObject error) {

      }
    });
    collection.updateDocument("42", doc.serialize(), new Options(), new ResponseListener<Document>() {
      @Override
      public void onSuccess(Document document) {
        assertEquals(document.getId(), "42");
      }

      @Override
      public void onError(JSONObject error) {

      }
    });
    verify(kuzzle, times(6)).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
  }

  @Test
  public void testRetryOnConflict() throws JSONException {
    Options opts = new Options().setRetryOnConflict(42);
    Document doc = new Document(collection);
    ArgumentCaptor capturedQuery = ArgumentCaptor.forClass(KuzzleJSONObject.class);

    collection.updateDocument("foo", doc.serialize(), opts);
    verify(kuzzle).query(any(Kuzzle.QueryArgs.class), (JSONObject)capturedQuery.capture(), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((JSONObject)capturedQuery.getValue()).getInt("retryOnConflict"), 42);
  }
}
