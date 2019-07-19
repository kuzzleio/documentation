package io.kuzzle.sdk.listeners;

import org.json.JSONObject;

/**
 * The interface Response listener.
 */
public interface ResponseListener<T> {
  /**
   * On success.
   *
   * @param response Raw Kuzzle API response
   */
  void onSuccess(T response);

  /**
   * On error.
   *
   * @param error Raw Kuzzle API error content
   */
  void onError(JSONObject error);
}
