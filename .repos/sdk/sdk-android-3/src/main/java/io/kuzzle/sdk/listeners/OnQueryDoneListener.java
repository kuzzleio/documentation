package io.kuzzle.sdk.listeners;

import org.json.JSONObject;

public interface OnQueryDoneListener {
  void onSuccess(JSONObject response);
  void onError(JSONObject error);
}
