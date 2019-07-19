package io.kuzzle.test.listeners;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.kuzzle.sdk.core.Room;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.SubscribeListener;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class KuzzleSubscribeListenerTest {

  private SubscribeListener subListener;
  private ResponseListener<Room> callback;
  private JSONObject  json = new JSONObject();

  @Before
  public void setUp() {
    subListener = new SubscribeListener();
    callback = spy(new ResponseListener<Room>() {
      @Override
      public void onSuccess(Room response) {

      }

      @Override
      public void onError(JSONObject error) {

      }
    });
  }

  @Test
  public void testOnDoneError() {
    subListener.onDone(callback);
    subListener.done(json, null);
    verify(callback).onError(any(JSONObject.class));
  }

  @Test
  public void testOnDoneSuccess() {
    subListener.onDone(callback);
    subListener.done(null, mock(Room.class));
    verify(callback).onSuccess(any(Room.class));
  }

  @Test
  public void testPostOnDoneError() {
    subListener.done(json, null);
    subListener.onDone(callback);
    verify(callback).onError(any(JSONObject.class));
  }

  @Test
  public void testPostOnDoneSuccess() {
    subListener.done(null, mock(Room.class));
    subListener.onDone(callback);
    verify(callback).onSuccess(any(Room.class));
  }

}
