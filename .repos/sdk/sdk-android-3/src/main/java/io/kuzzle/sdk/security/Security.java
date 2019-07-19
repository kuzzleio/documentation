package io.kuzzle.sdk.security;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Policies;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.responses.SecurityDocumentList;
import io.kuzzle.sdk.util.Scroll;

/**
 * Kuzzle security API
 */
public class Security {
  private final Kuzzle kuzzle;

  /**
   * Instantiates a new Kuzzle security instance
   *
   * @param kuzzle Kuzzle instance to attach
   */
  public Security(final Kuzzle kuzzle) {
    this.kuzzle = kuzzle;
  }

  /**
   * Helper function meant to easily build the first Kuzzle.query() argument
   *
   * @param action Security controller action name
   * @return Kuzzle.query() 1st argument object
   * @throws JSONException 
   */
  protected Kuzzle.QueryArgs buildQueryArgs(final String controller, @NonNull final String action) throws JSONException {
    io.kuzzle.sdk.core.Kuzzle.QueryArgs args = new io.kuzzle.sdk.core.Kuzzle.QueryArgs();
    args.action = action;
    args.controller = "security";

    if (controller != null) {
      args.controller = controller;
    }
    return args;
  }

  protected Kuzzle.QueryArgs buildQueryArgs(@NonNull final String action) throws JSONException {
    return buildQueryArgs(null, action);
  }

  /**
   * Retrieves a single Role using its unique Role ID
   *
   * @param id       Unique role ID
   * @param options  Optional query arguments
   * @param listener Response callback listener
   */
  public void fetchRole(@NonNull final String id, Options options, @NonNull final ResponseListener<Role> listener) {
    JSONObject data;

    if (id == null) {
      throw new IllegalArgumentException("Security.fetchRole: a role ID is required");
    }

    if (listener == null) {
      throw new IllegalArgumentException("Security.fetchRole: a listener is required");
    }

    try {
      data = new JSONObject().put("_id", id);
      this.kuzzle.query(buildQueryArgs("getRole"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONObject result = response.getJSONObject("result");
            listener.onSuccess(new Role(Security.this.kuzzle, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta")));
          }
          catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #fetchRole(String, Options, ResponseListener)}
   */
  public void fetchRole(@NonNull final String id, @NonNull final ResponseListener<Role> listener) {
    fetchRole(id, null, listener);
  }


  /**
   * Executes a search on roles using a set of filters
   *
   * @param filters  Search filters (see ElasticSearch filters)
   * @param options  Optional query arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void searchRoles(@NonNull final JSONObject filters, final Options options, @NonNull final ResponseListener<SecurityDocumentList> listener) throws JSONException {
    if (filters == null) {
      throw new IllegalArgumentException("Security.searchRoles: filters cannot be null");
    }

    if (listener == null) {
      throw new IllegalArgumentException("Security.searchRoles: a callback listener is required");
    }

    JSONObject data = new JSONObject().put("body", filters);

    this.kuzzle.query(buildQueryArgs("searchRoles"), data, options, new OnQueryDoneListener() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          JSONObject result = response.getJSONObject("result");
          JSONArray documents = result.getJSONArray("hits");
          int documentsLength = documents.length();
          ArrayList<AbstractSecurityDocument> roles = new ArrayList<>();

          for (int i = 0; i < documentsLength; i++) {
            JSONObject document = documents.getJSONObject(i);
            roles.add(new Role(Security.this.kuzzle, document.getString("_id"), document.getJSONObject("_source"), document.getJSONObject("_meta")));
          }

          listener.onSuccess(new SecurityDocumentList(roles, result.getLong("total")));
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    });
  }

  /**
   * {@link #searchRoles(JSONObject, Options, ResponseListener)}
   */
  public void searchRoles(@NonNull final JSONObject filters, @NonNull final ResponseListener<SecurityDocumentList> listener) throws JSONException {
    searchRoles(filters, null, listener);
  }

  /**
   * Create a new role in Kuzzle.
   * If the same role already exists: throw an error by default 
   * (see the replaceIfExist request optional argument)
   *
   * @param id       New role ID
   * @param content  New role rights definitions
   * @param options  Request optional parameters
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void createRole(@NonNull final String id, @NonNull final JSONObject content, Options options, final ResponseListener<Role> listener) throws JSONException {
    String action = "createRole";

    if (id == null || content == null) {
      throw new IllegalArgumentException("Security.createRole: cannot create a role without an ID or a content");
    }

    JSONObject data = new JSONObject().put("_id", id).put("body", content);

    if (options != null && options.isReplaceIfExist()) {
      action = "createOrReplaceRole";
    }

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs(action), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONObject result = response.getJSONObject("result");
            listener.onSuccess(new Role(Security.this.kuzzle, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs(action), data, options);
    }
  }

  /**
   * {@link #createRole(String, JSONObject, Options, ResponseListener)}
   */
  public void createRole(@NonNull final String id, @NonNull final JSONObject content, final ResponseListener<Role> listener) throws JSONException {
    createRole(id, content, null, listener);
  }

  /**
   * {@link #createRole(String, JSONObject, Options, ResponseListener)}
   */
  public void createRole(@NonNull final String id, @NonNull final JSONObject content, Options options) throws JSONException {
    createRole(id, content, options, null);
  }

  /**
   * {@link #createRole(String, JSONObject, Options, ResponseListener)}
   */
  public void createRole(@NonNull final String id, @NonNull final JSONObject content) throws JSONException {
    createRole(id, content, null, null);
  }

  /**
   * Delete a role from Kuzzle
   *
   * @param id       ID of the role to delete
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @return this
   * @throws JSONException 
   */
  public Security deleteRole(@NonNull final String id, final Options options, final ResponseListener<String> listener) throws JSONException {
    if (id == null) {
      throw new IllegalArgumentException("Security.deleteRole: cannot delete role without an ID");
    }

    JSONObject data = new JSONObject().put("_id", id);

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs("deleteRole"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result").getString("_id"));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs("deleteRole"), data, options);
    }

    return this;
  }

  /**
   * {@link #deleteRole(String, Options, ResponseListener)}
   */
  public Security deleteRole(@NonNull final String id, final ResponseListener<String> listener) throws JSONException {
    return deleteRole(id, null, listener);
  }

  /**
   * {@link #deleteRole(String, Options, ResponseListener)}
   */
  public Security deleteRole(@NonNull final String id, final Options options) throws JSONException {
    return deleteRole(id, options, null);
  }

  /**
   * {@link #deleteRole(String, Options, ResponseListener)}
   */
  public Security deleteRole(@NonNull final String id) throws JSONException {
    return deleteRole(id, null, null);
  }

  /**
   * Update a role's content
   *
   * @param id ID of the role to update
   * @param content Role content to update
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   * @throws JSONException 
   */
  public Security updateRole(@NonNull final String id, final JSONObject content, final Options options, final ResponseListener<Role> listener) throws JSONException {
    if (id == null) {
      throw new IllegalArgumentException("Security.updateRole: cannot update role without an ID");
    }

    JSONObject data = new JSONObject().put("_id", id);
    data.put("body", content);

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs("updateRole"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(new Role(Security.this.kuzzle, response.getJSONObject("result").getString("_id"), response.getJSONObject("result").getJSONObject("_source"), response.getJSONObject("result").getJSONObject("_meta")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs("updateRole"), data, options);
    }

    return this;
  }

  /**
   * {@link #updateRole(String, JSONObject, Options, ResponseListener)}
   */
  public Security updateRole(@NonNull final String id, final JSONObject content, final ResponseListener<Role> listener) throws JSONException {
    return updateRole(id, content, null, listener);
  }

  /**
   * {@link #updateRole(String, JSONObject, Options, ResponseListener)}
   */
  public Security updateRole(@NonNull final String id, final JSONObject content, final Options options) throws JSONException {
    return updateRole(id, content, options, null);
  }

  /**
   * {@link #updateRole(String, JSONObject, Options, ResponseListener)}
   */
  public Security updateRole(@NonNull final String id, final JSONObject content) throws JSONException {
    return updateRole(id, content, null, null);
  }

  /**
   * Instantiate a new Role object.
   * Does not automatically create it in Kuzzle
   *
   * @param id Role unique identifier
   * @param content Role content
   * @param meta Role metadata
   * @return new Role object
   * @throws JSONException 
   */
  public Role role(@NonNull final String id, final JSONObject content, final JSONObject meta) throws JSONException {
    return new Role(this.kuzzle, id, content, meta);
  }

  /**
   * {@link #role(String, JSONObject, JSONObject)}
   */
  public Role role(@NonNull final String id, final JSONObject content) throws JSONException {
    return new Role(this.kuzzle, id, content, null);
  }

  /**
   * {@link #role(String, JSONObject, JSONObject)}
   */
  public Role role(@NonNull final String id) throws JSONException {
    return new Role(this.kuzzle, id, null, null);
  }

  /**
   * Get a specific profile from kuzzle
   *
   * @param id ID of the profile to retrieve
   * @param options Request optional arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void fetchProfile(@NonNull final String id, final Options options, @NonNull final ResponseListener<Profile> listener) throws JSONException {
    if (id == null) {
      throw new IllegalArgumentException("Security.fetchProfile: cannot get 'null' profile");
    }

    if (listener == null) {
      throw new IllegalArgumentException("Security.fetchProfile: a listener callback is required");
    }

    JSONObject data = new JSONObject().put("_id", id);

    this.kuzzle.query(buildQueryArgs("getProfile"), data, options, new OnQueryDoneListener() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          JSONObject result = response.getJSONObject("result");
          JSONArray formattedPolicies = new JSONArray();
          JSONArray policies = result.getJSONObject("_source").getJSONArray("policies");

          for (int i = 0; i < policies.length(); i++) {
            JSONObject formattedPolicy = new JSONObject()
              .put("roleId", policies.getJSONObject(i).getString("roleId"));
            if (((JSONObject) policies.get(i)).has("restrictedTo")) {
              formattedPolicy.put("restrictedTo", policies.getJSONObject(i).getJSONArray("restrictedTo"));
            }
            if (((JSONObject) policies.get(i)).has("allowInternalIndex")) {
              formattedPolicy.put("allowInternalIndex", policies.getJSONObject(i).getBoolean("allowInternalIndex"));
            }
            formattedPolicies.put(formattedPolicy);
          }

          result.getJSONObject("_source").remove("policies");
          result.getJSONObject("_source").put("policies", formattedPolicies);

          listener.onSuccess(new Profile(Security.this.kuzzle, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta")));
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    });
  }

  /**
   * {@link #fetchProfile(String, Options, ResponseListener)}
   */
  public void fetchProfile(@NonNull final String id, @NonNull final ResponseListener<Profile> listener) throws JSONException {
    fetchProfile(id, null, listener);
  }

  /**
   * Executes a search on profiles according to a set of filters
   *
   * @param filters  Search filters
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void searchProfiles(@NonNull JSONObject filters, final Options options, @NonNull final ResponseListener<SecurityDocumentList> listener) throws JSONException {
    if (filters == null) {
      throw new IllegalArgumentException("Security.searchProfiles: cannot perform a search on null filters");
    }

    if (listener == null) {
      throw new IllegalArgumentException("Security.searchProfiles: a listener callback is required");
    }

    JSONObject data = new JSONObject().put("body", filters);

    this.kuzzle.query(buildQueryArgs("searchProfiles"), data, options, new OnQueryDoneListener() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          JSONObject result = response.getJSONObject("result");
          JSONArray documents = result.getJSONArray("hits");
          int documentsLength = documents.length();
          ArrayList<AbstractSecurityDocument> profiles = new ArrayList<>();

          for (int i = 0; i < documentsLength; i++) {
            JSONObject document = documents.getJSONObject(i);
            profiles.add(new Profile(Security.this.kuzzle, document.getString("_id"), document.getJSONObject("_source"), document.getJSONObject("_meta")));
          }

          Scroll scroll = new Scroll();

          if (result.has("scrollId")) {
            scroll.setScrollId(result.getString("scrollId"));
          }

          listener.onSuccess(new SecurityDocumentList(profiles, result.getLong("total"), scroll));
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    });
  }

  /**
   * {@link #searchProfiles(JSONObject, Options, ResponseListener)}
   */
  public void searchProfiles(@NonNull JSONObject filters, @NonNull final ResponseListener<SecurityDocumentList> listener) throws JSONException {
    searchProfiles(filters, null, listener);
  }

  /**
   * Create a new profile in Kuzzle.
   * Throws an error if the profile already exists, unless
   * "replaceIfExists" is set to true in request options
   *
   * @param id       ID of the new profile
   * @param policies List of policies attached to the new profile
   * @param options  Request optional arguments
   * @param listener Callback lisener
   * @throws JSONException 
   */
  public void createProfile(@NonNull final String id, @NonNull final JSONObject[] policies, final Options options, final ResponseListener<Profile> listener) throws JSONException {
    String action = "createProfile";

    if (id == null || policies == null) {
      throw new IllegalArgumentException("Security.createProfile: cannot create a profile with null ID or policies");
    }

    JSONObject data = new JSONObject()
      .put("_id", id)
      .put("body", new JSONObject().put("policies", new JSONArray(Arrays.asList(policies))));

    if (options != null && options.isReplaceIfExist()) {
      action = "createOrReplaceProfile";
    }

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs(action), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONObject result = response.getJSONObject("result");
            listener.onSuccess(new Profile(Security.this.kuzzle, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs(action), data, options);
    }
  }

  /**
   * {@link #createProfile(String, JSONObject[], Options, ResponseListener)}
   */
  public void createProfile(@NonNull final String id, @NonNull final JSONObject[] policies, final Options options) throws JSONException {
    createProfile(id, policies, options, null);
  }

  /**
   * {@link #createProfile(String, JSONObject[], Options, ResponseListener)}
   */
  public void createProfile(@NonNull final String id, @NonNull final JSONObject[] policies, final ResponseListener<Profile> listener) throws JSONException {
    createProfile(id, policies, null, listener);
  }

  /**
   * {@link #createProfile(String, JSONObject[], Options, ResponseListener)}
   */
  public void createProfile(@NonNull final String id, @NonNull final JSONObject[] policies) throws JSONException {
    createProfile(id, policies, null, null);
  }

  /**
   * Delete a profile from Kuzzle
   *
   * @param id       ID of the profile to delete
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @return this
   * @throws JSONException 
   */
  public Security deleteProfile(@NonNull final String id, final Options options, final ResponseListener<String> listener) throws JSONException {
    if (id == null) {
      throw new IllegalArgumentException("Security.deleteProfile: cannot delete a profile with ID null");
    }

    JSONObject data = new JSONObject().put("_id", id);

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs("deleteProfile"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result").getString("_id"));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs("deleteProfile"), data, options);
    }

    return this;
  }

  /**
   * {@link #deleteProfile(String, Options, ResponseListener)}
   */
  public Security deleteProfile(@NonNull final String id, final ResponseListener<String> listener) throws JSONException {
    return deleteProfile(id, null, listener);
  }

  /**
   * {@link #deleteProfile(String, Options, ResponseListener)}
   */
  public Security deleteProfile(@NonNull final String id, final Options options) throws JSONException {
    return deleteProfile(id, options, null);
  }

  /**
   * {@link #deleteProfile(String, Options, ResponseListener)}
   */
  public Security deleteProfile(@NonNull final String id) throws JSONException {
    return deleteProfile(id, null, null);
  }

  /**
   * Returns the next batch of searched profiles with scroll
   *
   * @param scroll   Scroll object obtained doing a scroll search
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void scrollProfiles(final Scroll scroll, final Options options, final ResponseListener<SecurityDocumentList> listener) throws JSONException {
    JSONObject request;

    try {
      request = new JSONObject().put("body", new JSONObject());
    }
    catch (JSONException e) {
      throw new RuntimeException(e);
    }

    if (listener == null) {
      throw new IllegalArgumentException("listener cannot be null");
    }

    if (scroll.getScrollId() == null) {
      throw new IllegalArgumentException("Security.scrollProfiles: scrollId is required");
    }

    options.setScrollId(scroll.getScrollId());

    try {
      this.kuzzle.query(buildQueryArgs("scrollProfiles"), request, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject object) {
          try {
            JSONArray hits = object.getJSONObject("result").getJSONArray("hits");
            ArrayList<AbstractSecurityDocument> profiles = new ArrayList<>();

            for (int i = 0; i < hits.length(); i++) {
              JSONObject hit = hits.getJSONObject(i);
              Profile profile = new Profile(Security.this.kuzzle, hit.getString("_id"), hit.getJSONObject("_source"), hit.getJSONObject("_meta"));

              profiles.add(profile);
            }

            SecurityDocumentList response = new SecurityDocumentList(profiles, hits.length(), scroll);

            listener.onSuccess(response);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #scrollProfiles(Scroll, Options, ResponseListener)}
   */
  public void scrollProfiles(Scroll scroll, final ResponseListener<SecurityDocumentList> listener) throws JSONException {
    this.scrollProfiles(scroll, new Options(), listener);
  }

  /**
   * Update a profile's content
   *
   * @param id ID of the profile to update
   * @param policies List of policies to apply to this profile
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   * @throws JSONException 
   */
  public Security updateProfile(@NonNull final String id, final JSONObject[] policies, final Options options, final ResponseListener<Profile> listener) throws JSONException {
    if (id == null) {
      throw new IllegalArgumentException("Security.updateProfile: cannot update a profile with ID null");
    }

    JSONObject data = new JSONObject().put("_id", id);
    data.put("body", new JSONObject().put("policies", new JSONArray(Arrays.asList(policies))));

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs("updateProfile"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(new Profile(Security.this.kuzzle, response.getJSONObject("result").getString("_id"), response.getJSONObject("result").getJSONObject("_source"), response.getJSONObject("result").getJSONObject("_meta")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs("updateProfile"), data, options);
    }

    return this;
  }

  /**
   * {@link #updateProfile(String, JSONObject[], Options, ResponseListener)}
   */
  public Security updateProfile(@NonNull final String id, final JSONObject[] policies, final Options options) throws JSONException {
    return updateProfile(id, policies, options, null);
  }

  /**
   * {@link #updateProfile(String, JSONObject[], Options, ResponseListener)}
   */
  public Security updateProfile(@NonNull final String id, final JSONObject[] policies, final ResponseListener<Profile> listener) throws JSONException {
    return this.updateProfile(id, policies, null, listener);
  }

  /**
   * {@link #updateProfile(String, JSONObject[], Options, ResponseListener)}
   */
  public Security updateProfile(@NonNull final String id, final JSONObject[] policies) throws JSONException {
    return updateProfile(id, policies, null, null);
  }

  /**
   * Instantiate a new Profile object.
   * Does not create it in Kuzzle.
   *
   * @param id Profile unique identifier
   * @param content Profile content
   * @param meta Profile metadata
   * @return new Profile object
   * @throws JSONException 
   */
  public Profile profile(@NonNull final String id, final JSONObject content, final JSONObject meta) throws JSONException {
    return new Profile(this.kuzzle, id, content, meta);
  }

  /**
   * {@link #profile(String, JSONObject, JSONObject)}
   */
  public Profile profile(@NonNull final String id, final JSONObject content) throws JSONException {
    return new Profile(this.kuzzle, id, content, null);
  }

  /**
   * {@link #profile(String, JSONObject, JSONObject)}
   */
  public Profile profile(@NonNull final String id) throws JSONException {
    return new Profile(this.kuzzle, id, null, null);
  }

  /**
   * Get a specific user from kuzzle using its unique ID
   *
   * @param id       User ID to retrieve
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void fetchUser(@NonNull final String id, final Options options, @NonNull final ResponseListener<User> listener) throws JSONException {
    if (id == null) {
      throw new IllegalArgumentException("Security.fetchUser: cannot get user with ID null");
    }

    if (listener == null) {
      throw new IllegalArgumentException("Security.fetchUser: a callback listener is required");
    }

    JSONObject data = new JSONObject().put("_id", id);

    this.kuzzle.query(buildQueryArgs("getUser"), data, options, new OnQueryDoneListener() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          JSONObject result = response.getJSONObject("result");
          listener.onSuccess(new User(Security.this.kuzzle, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta")));
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    });
  }

  /**
   * {@link #fetchUser(String, Options, ResponseListener)}
   */
  public void fetchUser(@NonNull final String id, @NonNull final ResponseListener<User> listener) throws JSONException {
    fetchUser(id, null, listener);
  }

  /**
   * Replaces an existing user in Kuzzle.
   * The new content must contain a "profileIds" attribute, an array
   * listing the attached profiles for this user
   *
   * @param id       ID of the user to replace
   * @param content  New user content
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void replaceUser(@NonNull final String id, @NonNull final JSONObject content, final Options options, final ResponseListener<User> listener) throws JSONException {
    if (id == null) {
      throw new IllegalArgumentException("Security.replaceUser: cannot replace user without an ID");
    }

    String action = "replaceUser";

    JSONObject data = new JSONObject().put("_id", id).put("body", content);

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs(action), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONObject result = response.getJSONObject("result");
            listener.onSuccess(new User(Security.this.kuzzle, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta")));
          }
          catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs(action), data, options);
    }
  }

  /**
   * {@link #replaceUser(String, JSONObject, Options, ResponseListener)}
   */
  public void replaceUser(@NonNull final String id, @NonNull final JSONObject content, final Options options) throws JSONException {
    replaceUser(id, content, options, null);
  }

  /**
   * {@link #replaceUser(String, JSONObject, Options, ResponseListener)}
   */
  public void replaceUser(@NonNull final String id, @NonNull final JSONObject content, final ResponseListener<User> listener) throws JSONException {
    replaceUser(id, content, null, listener);
  }

  /**
   * {@link #replaceUser(String, JSONObject, Options, ResponseListener)}
   */
  public void replaceUser(@NonNull final String id, @NonNull final JSONObject content) throws JSONException {
    replaceUser(id, content, null, null);
  }

  /**
   * Searches users using a set of filters
   *
   * @param filters  Search filters
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void searchUsers(@NonNull JSONObject filters, final Options options, @NonNull final ResponseListener<SecurityDocumentList> listener) throws JSONException {

    if (filters == null) {
      throw new IllegalArgumentException("Security.searchUsers: cannot perform a search with null filters");
    }

    if (listener == null) {
      throw new IllegalArgumentException("Security.searchUsers: a callback listener is required");
    }

    JSONObject data = new JSONObject().put("body", filters);

    this.kuzzle.query(buildQueryArgs("searchUsers"), data, options, new OnQueryDoneListener() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          JSONObject result = response.getJSONObject("result");
          JSONArray documents = result.getJSONArray("hits");
          int documentsLength = documents.length();
          ArrayList<AbstractSecurityDocument> users = new ArrayList<>();

          for (int i = 0; i < documentsLength; i++) {
            JSONObject document = documents.getJSONObject(i);
            users.add(new User(Security.this.kuzzle, document.getString("_id"), document.getJSONObject("_source"), document.getJSONObject("_meta")));
          }

          Scroll scroll = new Scroll();

          if (result.has("scrollId")) {
            scroll.setScrollId(result.getString("scrollId"));
          }

          listener.onSuccess(new SecurityDocumentList(users, result.getLong("total"), scroll));
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    });
  }

  /**
   * {@link #searchUsers(JSONObject, Options, ResponseListener)}
   */
  public void searchUsers(@NonNull JSONObject filters, @NonNull final ResponseListener<SecurityDocumentList> listener) throws JSONException {
    searchUsers(filters, null, listener);
  }

  /**
   * Create a new user in Kuzzle.
   *
   * @param id       ID of the user to create
   * @param content  User content
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void createUser(@NonNull final String id, @NonNull final JSONObject content, final Options options, final ResponseListener<User> listener) throws JSONException {
    String action = "createUser";
    if (id == null || content == null) {
      throw new IllegalArgumentException("Security.createUser: cannot create a user with a null ID or content");
    }

    JSONObject data = new JSONObject().put("_id", id).put("body", content);

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs(action), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONObject result = response.getJSONObject("result");
            listener.onSuccess(new User(Security.this.kuzzle, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta")));
          }
          catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs(action), data, options);
    }
  }

  /**
   * {@link #createUser(String, JSONObject, Options, ResponseListener)}
   */
  public void createUser(@NonNull final String id, @NonNull final JSONObject content, final Options options) throws JSONException {
    createUser(id, content, options, null);
  }

  /**
   * {@link #createUser(String, JSONObject, Options, ResponseListener)}
   */
  public void createUser(@NonNull final String id, @NonNull final JSONObject content, final ResponseListener<User> listener) throws JSONException {
    createUser(id, content, null, listener);
  }

  /**
   * {@link #createUser(String, JSONObject, Options, ResponseListener)}
   */
  public void createUser(@NonNull final String id, @NonNull final JSONObject content) throws JSONException {
    createUser(id, content, null, null);
  }

  /**
   * Create a new restricted user in Kuzzle.
   *
   * This function will create a new user. It is not usable to update an existing user.
   * This function allows anonymous users to create a "restricted" user with predefined rights.
   *
   * @param id       ID of the user to create
   * @param content  New user content
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void createRestrictedUser(@NonNull final String id, @NonNull final JSONObject content, final Options options, final ResponseListener<User> listener) throws JSONException {
    if (id == null || content == null) {
      throw new IllegalArgumentException("Security.createRestrictedUser: cannot create a user with a null ID or content");
    }

    if (content.has("profileIds")) {
      throw new IllegalArgumentException("Security.createRestrictedUser: cannot provide profileIds");
    }

    JSONObject data = new JSONObject().put("_id", id).put("body", content);

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs("createRestrictedUser"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONObject result = response.getJSONObject("result");
            listener.onSuccess(new User(Security.this.kuzzle, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta")));
          }
          catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs("createRestrictedUser"), data, options);
    }
  }

  /**
   * {@link #createRestrictedUser(String, JSONObject, Options, ResponseListener)}
   */
  public void createRestrictedUser(@NonNull final String id, @NonNull final JSONObject content, final Options options) throws JSONException {
    createRestrictedUser(id, content, options, null);
  }

  /**
   * {@link #createRestrictedUser(String, JSONObject, Options, ResponseListener)}
   */
  public void createRestrictedUser(@NonNull final String id, @NonNull final JSONObject content, final ResponseListener<User> listener) throws JSONException {
    createRestrictedUser(id, content, null, listener);
  }

  /**
   * {@link #createRestrictedUser(String, JSONObject, Options, ResponseListener)}
   */
  public void createRestrictedUser(@NonNull final String id, @NonNull final JSONObject content) throws JSONException {
    createRestrictedUser(id, content, null, null);
  }

  /**
   * Delete a user from Kuzzle
   *
   * @param id       ID of the user to delete
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @return this
   * @throws JSONException 
   */
  public Security deleteUser(@NonNull final String id, final Options options, final ResponseListener<String> listener) throws JSONException {
    if (id == null) {
      throw new IllegalArgumentException("Security.deleteUser: cannot delete user with ID null");
    }

    JSONObject data = new JSONObject().put("_id", id);

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs("deleteUser"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result").getString("_id"));
          }
          catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs("deleteUser"), data, options);
    }

    return this;
  }

  /**
   * {@link #deleteUser(String, Options, ResponseListener)}
   */
  public Security deleteUser(@NonNull final String id, final Options options) throws JSONException {
    return deleteUser(id, options, null);
  }

  /**
   * {@link #deleteUser(String, Options, ResponseListener)}
   */
  public Security deleteUser(@NonNull final String id, final ResponseListener<String> listener) throws JSONException {
    return deleteUser(id, null, listener);
  }

  /**
   * {@link #deleteUser(String, Options, ResponseListener)}
   */
  public Security deleteUser(@NonNull final String id) throws JSONException {
    return deleteUser(id, null, null);
  }

  /**
   * Returns the next batch of searched users 
   *
   * @param scroll   Scroll object, obtained using a scroll search
   * @param options  Request optional arguments
   * @param listener Response callback listener
   * @throws JSONException 
   */
  public void scrollUsers(final Scroll scroll, final Options options, final ResponseListener<SecurityDocumentList> listener) throws JSONException {
    JSONObject request;

    try {
      request = new JSONObject().put("body", new JSONObject());
    }
    catch (JSONException e) {
      throw new RuntimeException(e);
    }

    if (listener == null) {
      throw new IllegalArgumentException("listener cannot be null");
    }

    if (scroll.getScrollId() == null) {
      throw new IllegalArgumentException("Security.scrollUsers: scrollId is required");
    }

    options.setScrollId(scroll.getScrollId());

    try {
      this.kuzzle.query(buildQueryArgs("scrollUsers"), request, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject object) {
          try {
            JSONArray hits = object.getJSONObject("result").getJSONArray("hits");
            ArrayList<AbstractSecurityDocument> users = new ArrayList<>();

            for (int i = 0; i < hits.length(); i++) {
              JSONObject hit = hits.getJSONObject(i);
              User user = new User(Security.this.kuzzle, hit.getString("_id"), hit.getJSONObject("_source"), hit.getJSONObject("_meta"));

              users.add(user);
            }

            SecurityDocumentList response = new SecurityDocumentList(users, hits.length(), scroll);

            listener.onSuccess(response);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #scrollUsers(Scroll, Options, ResponseListener)}
   */
  public void scrollUsers(Scroll scroll, final ResponseListener<SecurityDocumentList> listener) throws JSONException {
    this.scrollUsers(scroll, new Options(), listener);
  }

  /**
   * Update a user.
   *
   * @param id ID of the user to update
   * @param content User content to update
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   * @throws JSONException 
   */
  public Security updateUser(@NonNull final String id, final JSONObject content, final Options options, final ResponseListener<User> listener) throws JSONException {
    if (id == null) {
      throw new IllegalArgumentException("Security.updateUser: cannot update user without an ID");
    }

    JSONObject data = new JSONObject().put("_id", id);
    data.put("body", content);

    if (listener != null) {
      this.kuzzle.query(buildQueryArgs("updateUser"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(new User(Security.this.kuzzle, response.getJSONObject("result").getString("_id"), response.getJSONObject("result").getJSONObject("_source"), response.getJSONObject("result").getJSONObject("_meta")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    }
    else {
      this.kuzzle.query(buildQueryArgs("updateUser"), data, options);
    }

    return this;
  }

  /**
   * {@link #updateUser(String, JSONObject, Options, ResponseListener)}
   */
  public Security updateUser(@NonNull final String id, final JSONObject content, final ResponseListener<User> listener) throws JSONException {
    return updateUser(id, content, null, listener);
  }

  /**
   * {@link #updateUser(String, JSONObject, Options, ResponseListener)}
   */
  public Security updateUser(@NonNull final String id, final JSONObject content, final Options options) throws JSONException {
    return updateUser(id, content, options, null);
  }

  /**
   * {@link #updateUser(String, JSONObject, Options, ResponseListener)}
   */
  public Security updateUser(@NonNull final String id, final JSONObject content) throws JSONException {
    return updateUser(id, content, null, null);
  }

  /**
   * Instantiate a new User object
   * Does not create it in Kuzzle
   *
   * @param id      User unique identifier
   * @param content User content
   * @param meta    User metadata
   * @return new User object
   * @throws JSONException 
   */
  public User user(@NonNull final String id, final JSONObject content, final JSONObject meta) throws JSONException {
    return new User(this.kuzzle, id, content, meta);
  }

  /**
   * {@link #user(String, JSONObject, JSONObject)}
   */
  public User user(@NonNull final String id, final JSONObject content) throws JSONException {
    return new User(this.kuzzle, id, content, null);
  }

  /**
   * {@link #user(String, JSONObject, JSONObject)}
   */
  public User user(@NonNull final String id) throws JSONException {
    return new User(this.kuzzle, id, null, null);
  }

  /**
   * {@link #isActionAllowed(JSONObject[], String, String, String, String)}
   */
  public Policies isActionAllowed(@NonNull final JSONObject[] policies, @NonNull final String controller, @NonNull final String action) {
    return this.isActionAllowed(policies, controller, action, null, null);
  }

  /**
   * {@link #isActionAllowed(JSONObject[], String, String, String, String)}
   */
  public Policies isActionAllowed(@NonNull final JSONObject[] policies, @NonNull final String controller, @NonNull  final String action, final String index) {
    return this.isActionAllowed(policies, controller, action, index, null);
  }

  /**
   * Tells whether an action is allowed, denied or conditional based on the rights
   * policies provided as the first argument. An action is defined as a couple of
   * action and controller (required), plus a data index and a collection (optional).
   *
   * @param policies List of policies containing the current authorizations
   * @param controller Kuzzle API Controller
   * @param action Controller action
   * @param index Data index
   * @param collection Data collection
   * @return action authorization status
   */
  public Policies isActionAllowed(@NonNull final JSONObject[] policies, @NonNull final String controller, @NonNull final String action, final String index, final String collection) {
    if (policies == null) {
      throw new IllegalArgumentException("Security.isActionAllowed: policies are mandatory.");
    }
    if (controller == null || controller.isEmpty()) {
      throw new IllegalArgumentException("Security.isActionAllowed: controller is mandatory.");
    }
    if (action == null || action.isEmpty()) {
      throw new IllegalArgumentException("Security.isActionAllowed: action is mandatory.");
    }

    JSONObject[] filteredPolicies;
    try {
      filteredPolicies = filterPolicy(policies, "controller", controller);
      filteredPolicies = filterPolicy(filteredPolicies, "action", action);
      filteredPolicies = filterPolicy(filteredPolicies, "index", index);
      filteredPolicies = filterPolicy(filteredPolicies, "collection", collection);

      for (JSONObject filteredPolicy : filteredPolicies) {
        if (filteredPolicy.getString("value").equals(Policies.allowed.toString())) {
          return Policies.allowed;
        }
      }

      for (JSONObject filteredPolicy : filteredPolicies) {
        if (filteredPolicy.getString("value").equals(Policies.conditional.toString())) {
          return Policies.conditional;
        }
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return Policies.denied;
  }

  private JSONObject[] filterPolicy(final JSONObject[] policies, final String attr, final String attrInput) throws JSONException {
    ArrayList<JSONObject> filteredPolicies = new ArrayList<>();

    for (JSONObject policy : policies) {
      String attrObject = policy.getString(attr);
      if (attrObject.equals(attrInput) || attrObject.equals("*")) {
        filteredPolicies.add(policy);
      }
    }
    return filteredPolicies.toArray(new JSONObject[0]);
  }

  /**
   * {@link #getUserRights(String, Options, ResponseListener)}
   */
  public Security getUserRights(@NonNull final String id, @NonNull final ResponseListener<JSONObject[]> listener) {
    return getUserRights(id, null, listener);
  }

  /**
   * Gets the rights array of a given user.
   *
   * @param id User ID to retrieve the rights from
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public Security getUserRights(@NonNull final String id, final Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Security.getUserRights: id is mandatory.");
    }
    if (listener == null) {
      throw new IllegalArgumentException("Security.getUserRights: listener is mandatory.");
    }
    try {
      JSONObject data = new JSONObject()
        .put("_id", id);
      kuzzle.query(buildQueryArgs("getUserRights"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONArray arr = response.getJSONObject("result").getJSONArray("hits");
            JSONObject[] rights = new JSONObject[arr.length()];

            for (int i = 0; i < arr.length(); i++) {
              rights[i] = arr.getJSONObject(i);
            }
            listener.onSuccess(rights);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  /**
   * {@link #createCredentials(String, String, JSONObject, Options, ResponseListener)}
   */
  public Security createCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final JSONObject credentials) {
    return createCredentials(strategy, kuid, credentials, null, null);
  }

  /**
   * {@link #createCredentials(String, String, JSONObject, Options, ResponseListener)}
   */
  public Security createCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final JSONObject credentials, final Options options) {
    return createCredentials(strategy, kuid, credentials, options, null);
  }

  /**
   * {@link #createCredentials(String, String, JSONObject, Options, ResponseListener)}
   */
  public Security createCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final JSONObject credentials, final ResponseListener<JSONObject> listener) {
    return createCredentials(strategy, kuid, credentials, null, listener);
  }

  /**
   * Create credentials of the specified strategy for the provided user.
   *
   * @param strategy Strategy name to add to the user
   * @param kuid Kuzzle User unique Identifier for the user to update
   * @param credentials Credentials content
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public Security createCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final JSONObject credentials, final Options options, final ResponseListener<JSONObject> listener) {
    try {
      JSONObject body = new JSONObject()
              .put("strategy", strategy)
              .put("_id", kuid)
              .put("body", credentials);
      kuzzle.query(buildQueryArgs("security", "createCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            if (listener != null) {
              listener.onSuccess(response.getJSONObject("result"));
            }
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * {@link #deleteCredentials(String, String, Options, ResponseListener)}
   */
  public Security deleteCredentials(@NonNull final String strategy, @NonNull final String kuid) {
    return deleteCredentials(strategy, kuid, null, null);
  }

  /**
   * {@link #deleteCredentials(String, String, Options, ResponseListener)}
   */
  public Security deleteCredentials(@NonNull final String strategy, @NonNull final String kuid, final Options options) {
    return deleteCredentials(strategy, kuid, options, null);
  }

  /**
   * {@link #deleteCredentials(String, String, Options, ResponseListener)}
   */
  public Security deleteCredentials(@NonNull final String strategy, @NonNull final String kuid, final ResponseListener<JSONObject> listener) {
    return deleteCredentials(strategy, kuid, null, listener);
  }

  /**
   * Delete credentials of the specified strategy for the user kuid .
   *
   * @param strategy Strategy name to delete from the user
   * @param kuid Kuzzle User unique Identifier for the user to update
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public Security deleteCredentials(@NonNull final String strategy, @NonNull final String kuid, final Options options, final ResponseListener<JSONObject> listener) {
    try {
      JSONObject body = new JSONObject()
              .put("strategy", strategy)
              .put("_id", kuid);
      kuzzle.query(buildQueryArgs("security", "deleteCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            if (listener != null) {
              listener.onSuccess(response.getJSONObject("result"));
            }
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * {@link #getAllCredentialFields(Options, ResponseListener)}
   */
  public void getAllCredentialFields(@NonNull final ResponseListener<JSONObject> listener) {
    getAllCredentialFields(null, listener);
  }

  /**
   * Gets a list of all accepted fields per authentication strategy.
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void getAllCredentialFields(final Options options, @NonNull final ResponseListener<JSONObject> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Security.getAllCredentialFields: listener is mandatory.");
    }
    try {
      JSONObject body = new JSONObject();
      kuzzle.query(buildQueryArgs("security", "getAllCredentialFields"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result"));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #getCredentialFields(String, Options, ResponseListener)}
   */
  public void getCredentialFields(@NonNull final String strategy, @NonNull final ResponseListener<String[]> listener) {
    getCredentialFields(strategy, null, listener);
  }

  /**
   * Retrieve the list of accepted field names by the specified strategy.
   *
   * @param strategy Name of the strategy to get
   * @param options Request options
   * @param listener Response callback listener
   */
  public void getCredentialFields(@NonNull final String strategy, final Options options, @NonNull final ResponseListener<String[]> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Security.getAllCredentialFields: listener is mandatory.");
    }
    try {
      JSONObject body = new JSONObject()
        .put("strategy", strategy);
      kuzzle.query(buildQueryArgs("security", "getCredentialFields"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONArray array = response.getJSONObject("result").getJSONArray("hits");
            int length = array.length();
            String[] fields = new String[length];
            for (int i = 0; i < length; i++) {
              fields[i] = array.getString(i);
            }
            listener.onSuccess(fields);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #getCredentials(String, String, Options, ResponseListener)}
   */
  public void getCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final ResponseListener<JSONObject> listener) {
    getCredentials(strategy, kuid, null, listener);
  }

  /**
   * Get credential information of the specified strategy for the user kuid.
   *
   * @param strategy Strategy name to get
   * @param kuid User unique identifier
   * @param options Request options
   * @param listener Response callback listener
   */
  public void getCredentials(@NonNull final String strategy, @NonNull final String kuid, final Options options, @NonNull final ResponseListener<JSONObject> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Security.getCredentials: listener is mandatory.");
    }
    try {
      JSONObject body = new JSONObject()
        .put("strategy", strategy)
        .put("_id", kuid);
      kuzzle.query(buildQueryArgs("security", "getCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result"));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #hasCredentials(String, String, Options, ResponseListener)}
   */
  public void hasCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final ResponseListener<Boolean> listener) {
    hasCredentials(strategy, kuid, null, listener);
  }

  /**
   * Check the existence of the specified strategy’s credentials for the user kuid.
   *
   * @param strategy Strategy name to check
   * @param kuid User unique identifier
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hasCredentials(@NonNull final String strategy, @NonNull final String kuid, final Options options, @NonNull final ResponseListener<Boolean> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Security.hasCredentials: listener is mandatory.");
    }
    try {
      JSONObject body = new JSONObject()
        .put("strategy", strategy)
        .put("_id", kuid);
      kuzzle.query(buildQueryArgs("security", "hasCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getBoolean("result"));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #updateCredentials(String, String, JSONObject, Options, ResponseListener)}
   */
  public Security updateCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final JSONObject credentials) {
    return updateCredentials(strategy, kuid, credentials, null, null);
  }

  /**
   * {@link #updateCredentials(String, String, JSONObject, Options, ResponseListener)}
   */
  public Security updateCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final JSONObject credentials, final Options options) {
    return updateCredentials(strategy, kuid, credentials, options, null);
  }

  /**
   * {@link #updateCredentials(String, String, JSONObject, Options, ResponseListener)}
   */
  public Security updateCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final JSONObject credentials, final ResponseListener<JSONObject> listener) {
    return updateCredentials(strategy, kuid, credentials, null, listener);
  }

  /**
   * Updates credentials of the specified strategy for the user kuid.
   *
   * @param strategy Strategy name to update
   * @param kuid User unique identifier
   * @param credentials Credentials content to update
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public Security updateCredentials(@NonNull final String strategy, @NonNull final String kuid, @NonNull final JSONObject credentials, final Options options, final ResponseListener<JSONObject> listener) {
    try {
      JSONObject body = new JSONObject()
        .put("strategy", strategy)
        .put("_id", kuid)
        .put("body", credentials);
      kuzzle.query(buildQueryArgs("security", "updateCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            if (listener != null) {
              listener.onSuccess(response.getJSONObject("result"));
            }
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * {@link #validateCredentials(String, String, JSONObject, Options, ResponseListener)}
   */
  public void validateCredentials(@NonNull final String strategy, @NonNull final String kuid, final JSONObject credentials, @NonNull final ResponseListener<Boolean> listener) {
    validateCredentials(strategy, kuid, credentials, null, listener);
  }

  /**
   * Validate credentials of the specified strategy for the user kuid.
   *
   * @param strategy Strategy name to validate
   * @param kuid User unique identifier
   * @param options Request options
   * @param listener Response callback listener
   */
  public void validateCredentials(@NonNull final String strategy, @NonNull final String kuid, final JSONObject credentials, final Options options, @NonNull final ResponseListener<Boolean> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Security.getCredentials: listener is mandatory.");
    }
    try {
      JSONObject body = new JSONObject()
        .put("strategy", strategy)
        .put("credentials", credentials)
        .put("_id", kuid);
      kuzzle.query(buildQueryArgs("security", "validateCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getBoolean("result"));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

}
