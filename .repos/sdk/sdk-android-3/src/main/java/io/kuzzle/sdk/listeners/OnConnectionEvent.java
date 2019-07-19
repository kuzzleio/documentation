package io.kuzzle.sdk.listeners;

import org.json.JSONObject;

public interface OnConnectionEvent {
  void onSuccess(JSONObject success);
  void onError(JSONObject error);
}
