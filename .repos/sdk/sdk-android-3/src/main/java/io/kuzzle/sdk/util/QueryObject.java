package io.kuzzle.sdk.util;

import org.json.JSONObject;

import java.util.Date;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;

public class QueryObject {
  private JSONObject query;
  private String action;
  private Options options;
  private OnQueryDoneListener cb;
  private Date  timestamp;

  /**
   * @return Callback to invoke with the query result
   */
  public OnQueryDoneListener getCb() {
    return cb;
  }

  /**
   * @param cb Callback to invoke with the query result
   */
  public void setCb(OnQueryDoneListener cb) {
    this.cb = cb;
  }

  /**
   * @return Controller action name to execute
   */
  public String getAction() {
    return action;
  }

  /**
   * @param action Controller action name to execute
   */
  public void setAction(String action) {
    this.action = action;
  }

  /**
   * @return Query options
   */
  public Options getOptions() {
    return options;
  }

  /**
   * @param options Query options
   */
  public void setOptions(Options options) {
    this.options = options;
  }

  /**
   * @return Query timestamp (Epoch time)
   */
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * @param timestamp Query timestamp (Epoch time)
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * @return Query content
   */
  public JSONObject getQuery() {
    return query;
  }

  /**
   * @param query Query content
   */
  public void setQuery(JSONObject query) {
    this.query = query;
  }
}
