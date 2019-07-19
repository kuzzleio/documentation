package io.kuzzle.sdk.util;

import org.json.JSONException;
import org.json.JSONObject;

public class KuzzleJSONObject extends JSONObject {

  @Override
  public KuzzleJSONObject put(final String name, final Object value) {
    try {
      super.put(name, value);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  @Override
  public KuzzleJSONObject put(final String name, final long value) {
    try {
      super.put(name, value);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  @Override
  public KuzzleJSONObject put(final String name, final int value) {
    try {
      super.put(name, value);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  @Override
  public KuzzleJSONObject put(final String name, final double value) {
    try {
      super.put(name, value);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  @Override
  public KuzzleJSONObject put(final String name, final boolean value) {
    try {
      super.put(name, value);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

}
