package io.kuzzle.test.core.KuzzleDataCollection;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class publishMessageTest {
  private Kuzzle kuzzle;
  private Collection collection;

  @Mock
  private ResponseListener<JSONObject> listener;


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

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void checkSignaturesVariants() {
    Document doc = mock(Document.class);

    when(doc.getContent()).thenReturn(new JSONObject());
    collection = spy(collection);

    collection.publishMessage(doc);
    collection.publishMessage(doc, mock(Options.class));
    collection.publishMessage(doc, listener);
    collection.publishMessage(doc, mock(Options.class), listener);
    collection.publishMessage(mock(JSONObject.class));
    collection.publishMessage(mock(JSONObject.class), listener);
    collection.publishMessage(mock(JSONObject.class), mock(Options.class));
    collection.publishMessage(mock(JSONObject.class), mock(Options.class), listener);
    verify(collection, times(8)).publishMessage(any(JSONObject.class), any(Options.class), any(ResponseListener.class));
  }


  @Test(expected = IllegalArgumentException.class)
  public void publishWithNoDocument() {
    collection.publishMessage((Document) null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void publishWithNoContent() {
    collection.publishMessage((JSONObject)null);
  }

  @Test(expected = RuntimeException.class)
  public void testPublishMessageException() throws JSONException {
    doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    collection.publishMessage(mock(Document.class), mock(Options.class));
  }

  @Test
  public void testPublishMessage() throws JSONException {
    Document doc = new Document(collection);
    doc.setContent("foo", "bar");
    collection.publishMessage(doc);
    collection.publishMessage(doc, new Options());
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(2)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "realtime");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "publish");
  }

  @Test
  public void testCallback() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener)invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("acknowledged", true)));
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    JSONObject message = new JSONObject().put("foo", "bar");
    collection.publishMessage(message, listener);
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "realtime");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "publish");
  }

  @Test(expected = RuntimeException.class)
  public void testPublishMEssageException() {
    doThrow(JSONException.class).when(kuzzle).addHeaders(any(JSONObject.class), any(JSONObject.class));
    collection.publishMessage(mock(JSONObject.class));
  }

}
