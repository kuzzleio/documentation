package io.kuzzle.test.core.KuzzleDocument;

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
import io.kuzzle.sdk.core.RoomOptions;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class subscribeTest {
  private Kuzzle k;
  private Document doc;
  private Collection mockCollection;

  @Before
  public void setUp() throws URISyntaxException, JSONException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setState(States.CONNECTED);
    extended.setSocket(mock(WebSocketClient.class));
    k = spy(extended);
    mockCollection = mock(Collection.class);
    doc = new Document(new Collection(k, "test", "index"));
  }

  @Test
  public void checkSignaturesVariants() {
    doc.setId("foo");
    doc = spy(doc);
    doc.subscribe(mock(ResponseListener.class));
    verify(doc).subscribe(eq((RoomOptions)null), any(ResponseListener.class));
  }

  @Test(expected = RuntimeException.class)
  public void testSubscribeException() throws JSONException {
    doc = new Document(mockCollection);
    doc.setId("42");
    doThrow(JSONException.class).when(mockCollection).subscribe(any(JSONObject.class), any(RoomOptions.class), any(ResponseListener.class));
    doc.subscribe(mock(ResponseListener.class));
  }

  @Test(expected = IllegalStateException.class)
  public void testSubscribeNullId() {
    doc.subscribe(mock(ResponseListener.class));
  }

  @Test
  public void testSubscribe() throws JSONException {
    final ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        //Mock response
        JSONObject result = new JSONObject();
        result.put("result", new JSONObject().put("channel", "channel").put("roomId", "42"));
        //Call callback with response
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(result);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
        verify(k, atLeastOnce()).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "realtime");
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "subscribe");

        return null;
      }
    }).when(k).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    doc.setId("42");
    doc.subscribe(mock(ResponseListener.class));
    doc.subscribe(new RoomOptions(), mock(ResponseListener.class));
  }

}
