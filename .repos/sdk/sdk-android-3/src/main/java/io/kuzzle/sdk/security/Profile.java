package io.kuzzle.sdk.security;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;

/**
 * This class handles profiles management in Kuzzle
 */
public class Profile extends AbstractSecurityDocument {
  private ArrayList<JSONObject> policies = new ArrayList<>();

  /**
   * Instantiates a new Kuzzle profile.
   *
   * @param kuzzle  Kuzzle instance to attach
   * @param id      Profile unique ID
   * @param content Profile content
   * @param meta Profile metadata
   * @throws JSONException 
   */
  public Profile(final Kuzzle kuzzle, @NonNull final String id, final JSONObject content, final JSONObject meta) throws JSONException {
    super(kuzzle, id, null, meta);
    this.deleteActionName = "deleteProfile";
    this.updateActionName = "updateProfile";

    if (content != null) {
      this.content = new JSONObject(content.toString());

      if (content.has("policies")) {
        JSONArray arr = content.getJSONArray("policies");

        for (int i = 0; i < arr.length(); i++) {
          this.policies.add(arr.getJSONObject(i));
        }

        this.content.remove("policies");
      }
    }
  }

  /**
   * Save this profile in Kuzzle
   *
   * @param options  Request optional arguments
   * @param listener Callback listener
   * @return this 
   * @throws JSONException 
   */
  public Profile save(final Options options, final ResponseListener<Profile> listener) throws JSONException {
    JSONObject data;

    if (this.policies.size() == 0) {
      throw new IllegalArgumentException("Cannot save the profile " + this.id + ": no policy defined");
    }

    data = this.serialize();

    if (listener != null) {
      this.kuzzle.query(this.kuzzleSecurity.buildQueryArgs("createOrReplaceProfile"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          listener.onSuccess(Profile.this);
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(this.kuzzleSecurity.buildQueryArgs("createOrReplaceProfile"), data, options);
    }

    return this;
  }

  /**
   * {@link #save(Options, ResponseListener)}
   */
  public Profile save(final ResponseListener<Profile> listener) throws JSONException {
    return this.save(null, listener);
  }

  /**
   * {@link #save(Options, ResponseListener)}
   */
  public Profile save(final Options options) throws JSONException {
    return this.save(options, null);
  }

  /**
   * {@link #save(Options, ResponseListener)}
   */
  public Profile save() throws JSONException {
    return this.save(null, null);
  }

  /**
   * Add a new role to the list of allowed roles of this profile
   *
   * @param policy Policy to add to this profile
   * @return this 
   * @throws IllegalArgumentException
   */
  public Profile addPolicy(final JSONObject policy) throws IllegalArgumentException {
    if (!policy.has("roleId")) {
      throw new IllegalArgumentException("The policy must have, at least, a roleId set.");
    }

    this.policies.add(policy);
    return this;
  }

  /**
   * Add a new policy to the list of policies of this profile via its roleId
   *
   * @param  roleId Name of the role to add to this profile
   * @return this 
   */
  public Profile addPolicy(final String roleId) {
    JSONObject policy = new JSONObject();
    try {
      policy.put("roleId", roleId);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    this.policies.add(policy);
    return this;
  }

  /**
   * Replace the current policies list with a new one
   *
   * @param policies New policies list
   * @return this 
   * @throws IllegalArgumentException
   */
  public Profile setPolicies(final JSONObject[] policies) throws IllegalArgumentException {
    for(JSONObject policy : policies) {
      if (!policy.has("roleId")) {
        throw new IllegalArgumentException("All pocicies must have at least a roleId set.");
      }
    }

    this.policies = new ArrayList<>(Arrays.asList(policies));

    return this;
  }

  /**
   * Replace the current policies list with a new one via its rolesIds
   *
   * @param roleIds New roles identifiers list
   * @return this 
   */
  public Profile setPolicies(final String[] roleIds) {
    this.policies.clear();

    try {
      for (String roleId : roleIds) {
        this.policies.add(new JSONObject().put("roleId", roleId));
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * Serialize the content of this object to a JSON Object
   * @return a serialized version of this object
   * @throws JSONException 
   */
  public JSONObject serialize() throws JSONException {
    JSONObject
      data = new JSONObject(),
      content = new JSONObject(this.content.toString());

    if (this.policies.size() > 0) {
      content.put("policies", new JSONArray(this.policies));
    }

    data
      .put("_id", this.id)
      .put("body", content);

    return data;
  }

  /**
   * Return the list of the policies assigned to this profile
   *
   * @return a JSONArray of policies objects
   */
  public JSONObject[] getPolicies() {
    return this.policies.toArray(new JSONObject[0]);
  }
}
