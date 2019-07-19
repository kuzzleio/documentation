package io.kuzzle.test.testUtils;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Room;
import io.kuzzle.sdk.core.RoomOptions;
import io.kuzzle.sdk.listeners.ResponseListener;

public class RoomExtend extends Room {

  public RoomExtend(Collection kuzzleDataCollection) {
    super(kuzzleDataCollection);
  }

  public RoomExtend(Collection kuzzleDataCollection, RoomOptions options) {
    super(kuzzleDataCollection, options);
  }

  public void callAfterRenew(Object args) {
    super.callAfterRenew(args);
  }

  public void setListener(final ResponseListener listener) {
    this.listener = listener;
  }

  public void setRoomId(final String id) {
    this.roomId = id;
  }

  public void setSubscribing(final boolean isSubscribing) {
    super.subscribing = isSubscribing;
  }

  public void dequeue() {
    super.dequeue();
  }

  @Override
  public Room unsubscribe() {
    // do nothing
    return this;
  }

  public Room superUnsubscribe() {
    return super.unsubscribe();
  }

  public TimerTask  unsubscribeTask(final Timer timer, final String roomId, final JSONObject data) {
    return super.unsubscribeTask(timer, roomId, data);
  }

  public Room makeHeadersNull() {
    super.headers = null;
    return this;
  }

}
