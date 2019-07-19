package io.kuzzle.sdk.listeners;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.kuzzle.sdk.core.Room;

public class SubscribeListener {
  private JSONObject  error;
  private Room room;
  private List<ResponseListener<Room>>  cbs = new ArrayList<>();

  public void onDone(ResponseListener<Room> cb) {
    if (this.error != null) {
      cb.onError(this.error);
    } else if (this.room != null) {
      cb.onSuccess(this.room);
    } else {
      this.cbs.add(cb);
    }
  }

  public SubscribeListener done(JSONObject error, Room room) {
    this.error = error;
    this.room = room;

    for (ResponseListener<Room> cb : cbs) {
      if (this.error != null) {
        cb.onError(this.error);
      } else if (this.room != null) {
        cb.onSuccess(this.room);
      }
    }
    return this;
  }

  public JSONObject getError() {
    return error;
  }

  public Room getRoom() {
    return room;
  }
}
