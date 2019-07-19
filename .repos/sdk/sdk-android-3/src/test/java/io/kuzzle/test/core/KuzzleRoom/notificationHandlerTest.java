package io.kuzzle.test.core.KuzzleRoom;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;
import java.util.Date;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.core.RoomOptions;
import io.kuzzle.sdk.enums.Event;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.EventListener;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import io.kuzzle.test.testUtils.RoomExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class notificationHandlerTest {
  private ResponseListener listener = mock(ResponseListener.class);
  private JSONObject mockNotif = new JSONObject();
  private JSONObject  mockResponse = new JSONObject();
  private Kuzzle k;
  private RoomExtend room;

  @Before
  public void setUp() throws JSONException {
    mockNotif.put("type", "type")
      .put("index", "index")
      .put("status", 200)
      .put("collection", "collection")
      .put("controller", "controller")
      .put("action", "action")
      .put("state", "all")
      .put("scope", "all")
      .put("volatile", new JSONObject())
      .put("result", new JSONObject())
      .put("requestId", "42");
    mockResponse.put("result", new JSONObject().put("channel", "channel").put("roomId", "42"));
    k = mock(Kuzzle.class);
    when(k.getHeaders()).thenReturn(new JSONObject());
    room = new RoomExtend(new Collection(k, "test", "index"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCallAfterRenewWithNoResponse() {
    RoomExtend renew = new RoomExtend(new Collection(k, "test", "index"));
    // Should throw an exception
    renew.callAfterRenew(null);
  }

  @Test
  public void testCallAfterRenew() throws JSONException {
    RoomExtend renew = new RoomExtend(new Collection(k, "test", "index"));
    renew.setListener(listener);
    JSONObject mockResponse = new JSONObject().put("result", new JSONObject());
    mockResponse.put("requestId", "42");
    renew.callAfterRenew(mockNotif);
  }

  @Test
  public void testCallAfterRenewWithSubscribeToSelf() throws JSONException, URISyntaxException {
    RoomOptions options = new RoomOptions();
    options.setSubscribeToSelf(true);
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    WebSocketClient s = mock(WebSocketClient.class);
    extended.setSocket(s);
    extended.setState(States.CONNECTED);
    extended = spy(extended);
    RoomExtend renew = new RoomExtend(new Collection(extended, "test", "index"), options);
    renew.setListener(listener);
    extended.getRequestHistory().put("42", new Date());
    renew.callAfterRenew(mockNotif);
    verify(listener, atLeastOnce()).onSuccess(any(JSONObject.class));
  }

  @Test
  public void testRenewOn() throws JSONException, URISyntaxException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    WebSocketClient s = mock(WebSocketClient.class);
    extended.setSocket(s);
    extended.setState(States.CONNECTED);
    extended = spy(extended);
    room = new RoomExtend(new Collection(extended, "collection", "index"));
    room.setRoomId("foobar");
    room = spy(room);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        //Mock response
        JSONObject result = new JSONObject();
        result.put("result", new JSONObject().put("channel", "channel").put("roomId", "42"));
        //Call callback with response
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(result);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
        return null;
      }
    }).when(extended).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    room.renew(listener);
  }

  @Test
  public void testTokenExpiredNotification() throws JSONException, URISyntaxException {
    k = new Kuzzle("localhost");
    EventListener listener = spy(new EventListener() {
      @Override
      public void trigger(Object... args) {

      }
    });
    k.addListener(Event.tokenExpired, listener);
    RoomExtend renew = new RoomExtend(new Collection(k, "test", "index"));
    renew.setListener(mock(ResponseListener.class));
    JSONObject mockResponse = new JSONObject().put("result", new JSONObject());
    mockResponse.put("requestId", "42");
    mockNotif.put("type", "TokenExpired");
    renew.callAfterRenew(mockNotif);
    verify(listener, times(1)).trigger();
  }

  @Test(expected = RuntimeException.class)
  public void testCallAfterRenewException() throws URISyntaxException, JSONException {
    RoomOptions options = new RoomOptions();
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    WebSocketClient s = mock(WebSocketClient.class);
    extended.setSocket(s);
    extended.setState(States.CONNECTED);
    extended = spy(extended);
    RoomExtend renew = new RoomExtend(new Collection(extended, "test", "index"), options);
    mockNotif = spy(mockNotif);
    doThrow(JSONException.class).when(mockNotif).isNull(any(String.class));
    renew.setListener(listener);
    extended.getRequestHistory().put("42", new Date());
    mockNotif.put("error", mock(JSONObject.class));
    renew.callAfterRenew(mockNotif);
    // should trigger listener.onError
    verify(listener, atLeastOnce()).onError(any(JSONObject.class));
    // Now simulate exception on the onError
    doThrow(JSONException.class).when(listener).onError(any(JSONObject.class));
    mockNotif.remove("error");
    renew.callAfterRenew(mockNotif);
  }
}
