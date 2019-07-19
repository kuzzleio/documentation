package io.kuzzle.test.core.KuzzleRoom;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Options;
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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class countTest {
  private ResponseListener listener = mock(ResponseListener.class);
  private ResponseListener spyListener = spy(new ResponseListener() {
    @Override
    public void onSuccess(Object response) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
  private JSONObject mockNotif = new JSONObject();
  private JSONObject  mockResponse = new JSONObject();
  private KuzzleExtend k;
  private RoomExtend room;

  @Before
  public void setUp() throws JSONException, URISyntaxException {
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
    k = spy(new KuzzleExtend("localhost", null, null));
    k.setSocket(mock(WebSocketClient.class));
    k.setState(States.CONNECTED);
    when(k.getHeaders()).thenReturn(new JSONObject());
    room = new RoomExtend(new Collection(k, "test", "index"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCountIllegalArgument() {
    room.count(null);
  }

  @Test
  public void testCountWhileSubscribing() throws JSONException, URISyntaxException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);
    extended = spy(extended);
    room = new RoomExtend(new Collection(extended, "test", "index"));
    room.setRoomId("foobar");
    room.setSubscribing(true);
    room.count(spyListener);
    room = spy(room);
    room.setSubscribing(false);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        //Mock response
        JSONObject result = new JSONObject();
        result.put("result", new JSONObject()
            .put("channel", "channel")
            .put("roomId", "42")
            .put("count", 42));
        //Call callback with response
        if (invocation.getArguments()[3] != null) {
          ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(result);
          ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
          verify(room).dequeue();

        }
        return null;
      }
    }).when(extended).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    room.renew(listener);
  }

  @Test(expected = RuntimeException.class)
  public void testCountQueryException() throws JSONException {
    room.setRoomId("foobar");
    doThrow(JSONException.class).when(k).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(OnQueryDoneListener.class));
    room.count(listener);
  }

  @Test(expected = RuntimeException.class)
  public void testCountException() throws JSONException {
    room.setRoomId("foobar");
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[2]).onSuccess(new JSONObject().put("result", new JSONObject().put("count", 42)));
        return null;
      }
    }).when(k).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(OnQueryDoneListener.class));
    doThrow(JSONException.class).when(listener).onSuccess(any(Integer.class));
    room.count(listener);
  }

  @Test(expected = IllegalStateException.class)
  public void testCountNoId() {
    room.count(listener);
  }

  @Test
  public void testCount() throws JSONException, URISyntaxException {
    JSONObject o = mock(JSONObject.class);
    when(o.put(any(String.class), any(Object.class))).thenReturn(new JSONObject());

    KuzzleExtend extended = new KuzzleExtend("localhost", null, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);
    extended = spy(extended);
    room = new RoomExtend(new Collection(extended, "test", "index"));
    room.setRoomId("foobar");

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener)invocation.getArguments()[2]).onSuccess(new JSONObject().put("result", new JSONObject().put("count", 42)));
        ((OnQueryDoneListener)invocation.getArguments()[2]).onError(new JSONObject());
        return null;
      }
    }).when(extended).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(OnQueryDoneListener.class));
    room.setRoomId("foobar");
    room.count(mock(ResponseListener.class));
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(extended, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "realtime");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "count");
  }

}
