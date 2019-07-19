package io.kuzzle.test.core.KuzzleRoom;

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
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.core.Room;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.responses.NotificationResponse;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import io.kuzzle.test.testUtils.RoomExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class renewTest {
  @Mock
  private ResponseListener<NotificationResponse> listener;

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
      .put("state", "ALL")
      .put("scope", "ALL")
      .put("volatile", new JSONObject())
      .put("result", new JSONObject())
      .put("requestId", "42");
    mockResponse.put("result", new JSONObject().put("channel", "channel").put("roomId", "42"));
    k = mock(Kuzzle.class);
    when(k.getHeaders()).thenReturn(new JSONObject());
    room = new RoomExtend(new Collection(k, "text", "index"));

    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRenewIllegalListener() {
    room.renew(null, null);
  }

  @Test
  public void testRenew() throws JSONException, URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    WebSocketClient s = mock(WebSocketClient.class);
    KuzzleExtend kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setState(States.CONNECTED);
    kuzzle.setSocket(s);

    final Kuzzle kuzzleSpy = spy(kuzzle);
    Room testRoom = new Room(new Collection(kuzzleSpy, "collection", "index"));

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        //Mock response
        JSONObject result = new JSONObject();
        result.put("result", new JSONObject().put("channel", "channel").put("roomId", "42"));
        //Call callback with response
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(result);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
        ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
        verify(kuzzleSpy, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "realtime");
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "subscribe");

        return null;
      }
    }).when(kuzzleSpy).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    testRoom.renew(new JSONObject(), listener, null);
  }

  @Test
  public void testNoRenewal() throws JSONException, URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    KuzzleExtend kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setState(States.CONNECTED);
    kuzzle.setSocket(mock(WebSocketClient.class));

    final Kuzzle kuzzleSpy = spy(kuzzle);
    RoomExtend testRoom = new RoomExtend(new Collection(kuzzleSpy, "collection", "index"));
    testRoom.setRoomId("foobar");
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        //Mock response
        JSONObject result = new JSONObject();
        result.put("result", new JSONObject().put("channel", "channel").put("roomId", "42"));
        //Call callback with response
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(result);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
        verify(kuzzleSpy, times(1)).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

        return null;
      }
    }).when(kuzzleSpy).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    testRoom.renew(listener);
    testRoom.renew(listener);
  }

  @Test
  public void testRenewWhileSubscribing() throws JSONException, URISyntaxException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);
    extended = spy(extended);
    room = new RoomExtend(new Collection(extended, "test", "index"));
    room.setRoomId("foobar");
    room.setSubscribing(true);
    room.renew(listener);
    room = spy(room);
    room.setSubscribing(false);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        //Mock response
        JSONObject result = new JSONObject();
        result.put("result", new JSONObject()
            .put("channel", "channel")
            .put("roomId", "42"));
        //Call callback with response
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(result);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
        verify(room).dequeue();

        return null;
      }
    }).when(extended).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    room.renew(listener);
  }
}
