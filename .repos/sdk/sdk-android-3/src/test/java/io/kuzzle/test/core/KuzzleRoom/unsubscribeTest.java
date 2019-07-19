package io.kuzzle.test.core.KuzzleRoom;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;
import java.util.Timer;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.core.Room;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import io.kuzzle.test.testUtils.RoomExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class unsubscribeTest {
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
      .put("state", "ALL")
      .put("scope", "ALL")
      .put("volatile", new JSONObject())
      .put("result", new JSONObject())
      .put("requestId", "42");
    mockResponse.put("result", new JSONObject().put("channel", "channel").put("roomId", "42"));
    k = mock(Kuzzle.class);
    when(k.getHeaders()).thenReturn(new JSONObject());
    room = new RoomExtend(new Collection(k, "test", "index"));
  }

  @Test
  public void testUnsubscribe() throws JSONException, URISyntaxException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    WebSocketClient s = mock(WebSocketClient.class);

    KuzzleExtend kuzzle = new KuzzleExtend("localhost", opts, null);
    kuzzle.setState(States.CONNECTED);
    kuzzle.setSocket(s);

    kuzzle = spy(kuzzle);
    room = new RoomExtend(new Collection(kuzzle, "test", "index"));
    room.setRoomId("42");
    room.superUnsubscribe();
    assertEquals(room.getRoomId(), null);
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "realtime");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "unsubscribe");
  }

  @Test
  public void testUnsubscribeWhileSubscribing() throws JSONException, URISyntaxException, InterruptedException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);
    extended = spy(extended);
    room = new RoomExtend(new Collection(extended, "test", "index"));
    room.setRoomId("foobar");
    room.setSubscribing(true);
    room.superUnsubscribe();
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
        if (invocation.getArguments()[3] != null) {
          ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(result);
          ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
        }
        verify(room).dequeue();
        return null;
      }
    }).when(extended).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    room.renew(listener);
  }

  @Test
  public void testUnsubscribeWithPendingSubscriptions() throws URISyntaxException, JSONException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    WebSocketClient s = mock(WebSocketClient.class);

    KuzzleExtend kuzzle = new KuzzleExtend("localhost", opts, null);
    kuzzle.setState(States.CONNECTED);
    kuzzle.setSocket(s);

    kuzzle = spy(kuzzle);
    kuzzle.getPendingSubscriptions().put("42", mock(Room.class));

    room = new RoomExtend(new Collection(kuzzle, "test", "index"));
    room.setRoomId("42");
    room.superUnsubscribe();

    assertEquals(room.getRoomId(), null);
    verify(kuzzle, never()).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
  }

  @Test(expected = RuntimeException.class)
  public void testUnsubscribeException() throws URISyntaxException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);
    extended = spy(extended);
    doThrow(JSONException.class).when(extended).removeRoom(any(String.class));
    room = new RoomExtend(new Collection(extended, "test", "index"));
    room.setRoomId("foobar");
    room.superUnsubscribe();
  }

  @Test
  public void testUnsubscribeTask() throws URISyntaxException, JSONException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);
    extended = spy(extended);

    room = new RoomExtend(new Collection(extended, "test", "index"));
    room.setRoomId("foobar");
    room.unsubscribeTask(new Timer(), room.getRoomId(), new JSONObject()).run();
    verify(extended).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
  }

  @Test(expected = RuntimeException.class)
  public void testUnsubscribeTaskException() throws URISyntaxException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);
    extended = spy(extended);
    doThrow(JSONException.class).when(extended).getPendingSubscriptions();
    room = new RoomExtend(new Collection(extended, "test", "index"));
    room.setRoomId("foobar");
    room.unsubscribeTask(new Timer(), room.getRoomId(), new JSONObject()).run();
  }

}
