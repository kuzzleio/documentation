package io.kuzzle.sdk.responses;

import org.json.JSONException;
import org.json.JSONObject;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Document;
import io.kuzzle.sdk.enums.Scope;
import io.kuzzle.sdk.enums.State;
import io.kuzzle.sdk.enums.Users;

public class NotificationResponse {
  private int status;
  private String  index;
  private String  collection;
  private String  controller;
  private String  action;
  private State   state;
  private Scope   scope;
  private Users   users;
  private JSONObject  _volatile;
  private String requestId;
  private Document document;
  private JSONObject  result;

  /**
   * Response notification representation
   * @see  <a href="http://docs.kuzzle.io/sdk-reference/essentials/notifications/">SDK Reference</a>
   * @param  kuzzle Kuzzle instance to attach
   * @param  object Raw Kuzzle API notification
   */
  public NotificationResponse(final Kuzzle kuzzle, final JSONObject object) {
    try {
      this.status = object.getInt("status");
      this.index = object.getString("index");
      this.collection = object.getString("collection");
      this.controller = object.getString("controller");
      this.action = object.getString("action");
      this.state = (object.isNull("state") ? null : State.valueOf(object.getString("state").toUpperCase()));
      this._volatile = object.isNull("volatile") ? new JSONObject() : object.getJSONObject("volatile");
      this.requestId = object.isNull("requestId") ? null : object.getString("requestId");
      this.result = (object.isNull("result") ? null : object.getJSONObject("result"));
      this.scope = (object.isNull("scope") ? null : Scope.valueOf(object.getString("scope").toUpperCase()));
      this.users = (object.isNull("user") ? null : Users.valueOf(object.getString("user").toUpperCase()));
      if (!object.getJSONObject("result").isNull("_source")) {
        JSONObject content = object.getJSONObject("result");
        String id = content.getString("_id");
        JSONObject meta = content.isNull("_meta") ? new JSONObject() : content.getJSONObject("_meta");
        content.remove("_id");
        content.remove("_meta");
        this.document = new Document(new Collection(kuzzle, this.collection, this.index), id, content, meta);
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @return Notification status value
   */
  public int getStatus() {
    return status;
  }

  /**
   * @return Impacted data index value
   */
  public String getIndex() {
    return index;
  }

  /**
   * @return Impacted data collection value
   */
  public String getCollection() {
    return collection;
  }

  /**
   * @return Invoked Kuzzle API controller 
   */
  public String getController() {
    return controller;
  }

  /**
   * @return Executed API controller action 
   */
  public String getAction() {
    return action;
  }

  /**
   * @return Notification state 
   */
  public State getState() {
    return state;
  }

  /**
   * @return Notification scope 
   */
  public Scope getScope() {
    return scope;
  }

  /**
   * @return Notification volatile data 
   */
  public JSONObject getVolatile() {
    return _volatile;
  }

  /**
   * @return Origin request unique ID
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * @return Notification content
   */
  public Document getDocument() {
    return document;
  }

  /**
   * @return Notification raw content
   */
  public JSONObject getResult() {
    return result;
  }

  /**
   * @return Notification users state
   */
  public Users getUsers() {
    return users;
  }
}
