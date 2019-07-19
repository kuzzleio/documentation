package io.kuzzle.sdk.security;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;

/**
 * This class handles roles management in Kuzzle
 */
public class Role extends AbstractSecurityDocument {
  /**
   * Instantiates a new Kuzzle role.
   *
   * @param kuzzle  Kuzzle instance to attach
   * @param id      Role unique identifier
   * @param content Role content
   * @param meta Role metadata
   * @throws JSONException 
   */
  public Role(final Kuzzle kuzzle, @NonNull final String id, final JSONObject content, final JSONObject meta) throws JSONException {
    super(kuzzle, id, content, meta);
    this.deleteActionName = "deleteRole";
    this.updateActionName = "updateRole";
  }

  /**
   * Save this role in Kuzzle
   *
   * @param options  Request optional configuration
   * @param listener Optional callback listener
   * @return this
   * @throws JSONException 
   */
  public Role save(final Options options, final ResponseListener<Role> listener) throws JSONException {
    JSONObject data = this.serialize();

    if (listener != null) {
      this.kuzzle.query(this.kuzzleSecurity.buildQueryArgs("createOrReplaceRole"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          listener.onSuccess(Role.this);
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(this.kuzzleSecurity.buildQueryArgs("createOrReplaceRole"), data, options);
    }

    return this;
  }

  /**
   * {@link #save(Options, ResponseListener)}
   */
  public Role save(final ResponseListener<Role> listener) throws JSONException {
    return this.save(null, listener);
  }

  /**
   * {@link #save(Options, ResponseListener)}
   */
  public Role save(final Options options) throws JSONException {
    return this.save(options, null);
  }

  /**
   * {@link #save(Options, ResponseListener)}
   */
  public Role save() throws JSONException {
    return this.save(null, null);
  }
}
