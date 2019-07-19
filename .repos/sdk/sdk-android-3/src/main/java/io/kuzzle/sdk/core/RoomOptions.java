package io.kuzzle.sdk.core;

import org.json.JSONObject;

import io.kuzzle.sdk.enums.Scope;
import io.kuzzle.sdk.enums.State;
import io.kuzzle.sdk.enums.Users;

public class RoomOptions {

  private boolean subscribeToSelf = true;
  private JSONObject  _volatile = new JSONObject();
  private Scope scope = Scope.ALL;
  private State state = State.DONE;
  private Users users = Users.NONE;

  /**
   * subscribeToSelf property getter
   *
   * @return subscribeToSelf property value
   */
  public boolean isSubscribeToSelf() {
    return subscribeToSelf;
  }

  /**
   * subscribeToSelf property setter
   *
   * @param subscribeToSelf New subscribeToSelf value
   * @return  this
   */
  public RoomOptions setSubscribeToSelf(boolean subscribeToSelf) {
    this.subscribeToSelf = subscribeToSelf;

    return this;
  }

  /**
   * volatile property getter
   * @return volatile property value
   */
  public JSONObject getVolatile() {
    return _volatile;
  }

  /**
   * volatile property setter
   * @param  _volatile New volatile value
   * @return this
   */
  public RoomOptions setVolatile(JSONObject _volatile) {
    this._volatile = _volatile;

    return this;
  }

  /**
   * scope property getter
   * @return scope property value
   */
  public Scope getScope() {
    return scope;
  }

  /**
   * scope property setter
   * @param  scope New scope value
   * @return this
   */
  public RoomOptions setScope(Scope scope) {
    this.scope = scope;

    return this;
  }

  /**
   * state property getter
   * @return state property value
   */
  public State getState() {
    return state;
  }

  /**
   * state property setter
   * @param  state New state value
   * @return this
   */
  public RoomOptions setState(State state) {
    this.state = state;

    return this;
  }

  /**
   * users property getter
   * @return users property value
   */
  public Users getUsers() {
    return users;
  }

  /**
   * users property setter
   * @param  users New users value
   * @return this
   */
  public RoomOptions setUsers(Users users) {
    this.users = users;

    return this;
  }

}
