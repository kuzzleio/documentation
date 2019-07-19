package io.kuzzle.test.core.KuzzleDataCollection;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import io.kuzzle.sdk.core.Kuzzle.QueryArgs;
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

public class createTest {
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
    collection = spy(collection);

    collection.create();
    collection.create(mock(Options.class));
    collection.create(listener);
    collection.create(mock(Options.class), listener);
    collection.create(mock(JSONObject.class));
    collection.create(mock(JSONObject.class), mock(Options.class));
    collection.create(mock(JSONObject.class), listener);

    verify(collection, times(7)).create(any(JSONObject.class), any(Options.class), any(ResponseListener.class));
  }

  @Test(expected = RuntimeException.class)
  public void testCreateQueryException() throws JSONException {
    doThrow(JSONException.class).when(kuzzle).query(any(QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    collection.create(listener);
  }

  @Test(expected = RuntimeException.class)
  public void testCreateException() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", mock(JSONObject.class)));
        return null;
      }
    }).when(kuzzle).query(any(QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    doThrow(JSONException.class).when(listener).onSuccess(any(JSONObject.class));
    collection.create(listener);
  }

  @Test
  public void testCreateWithoutMapping() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", mock(JSONObject.class)));
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    collection.create(new Options(), listener);
    collection.create(listener);
    collection.create(new Options());
    collection.create();

    ArgumentCaptor argument = ArgumentCaptor.forClass(QueryArgs.class);
    verify(kuzzle, times(4)).query((QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((QueryArgs) argument.getValue()).controller, "collection");
    assertEquals(((QueryArgs) argument.getValue()).action, "create");
  }

  @Test
  public void testCreateWithMapping() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", mock(JSONObject.class)));
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    JSONObject mapping = new JSONObject().put("foo", "bar");

    collection.create(mapping);
    collection.create(mapping, new Options(), listener);
    collection.create(mapping, listener);
    collection.create(mapping, new Options());

    ArgumentCaptor
      capturedQargs = ArgumentCaptor.forClass(QueryArgs.class),
      capturedMappings = ArgumentCaptor.forClass(JSONObject.class);

    verify(kuzzle, times(4)).query((QueryArgs)capturedQargs.capture(), (JSONObject)capturedMappings.capture(), any(Options.class), any(OnQueryDoneListener.class));

    assertEquals(((QueryArgs) capturedQargs.getValue()).controller, "collection");
    assertEquals(((QueryArgs) capturedQargs.getValue()).action, "create");

    String expected = (new JSONObject().put("body", mapping)).toString();
    for (Object m : capturedMappings.getAllValues()) {
      assertEquals(expected, m.toString());
    }
  }
}
