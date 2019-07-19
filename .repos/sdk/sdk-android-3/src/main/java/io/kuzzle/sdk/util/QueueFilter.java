package io.kuzzle.sdk.util;

import org.json.JSONObject;

public interface QueueFilter {
  boolean filter(JSONObject object);
}
