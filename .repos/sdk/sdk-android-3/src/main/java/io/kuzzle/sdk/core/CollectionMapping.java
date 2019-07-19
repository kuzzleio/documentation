package io.kuzzle.sdk.core;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;

public class CollectionMapping {

  private JSONObject headers;
  private JSONObject mapping;
  private Kuzzle kuzzle;
  private String collection;
  private Collection dataCollection;

  /**
   * Constructor
   *
   * @param kuzzleDataCollection - Parent data collection
   */
  public CollectionMapping(final Collection kuzzleDataCollection) {
    this(kuzzleDataCollection, null);
  }

  /**
   * Constructor
   *
   * @param kuzzleDataCollection - Parent data collection
   * @param mapping - Mapping content
   */
  public CollectionMapping(final Collection kuzzleDataCollection, final JSONObject mapping) {
    try {
      this.headers = new JSONObject(kuzzleDataCollection.getHeaders().toString());
    }
    catch(JSONException e) {
      throw new RuntimeException(e);
    }

    this.kuzzle = kuzzleDataCollection.getKuzzle();
    this.collection = kuzzleDataCollection.getCollection();
    this.mapping = mapping == null ? new JSONObject() : mapping;
    this.dataCollection = kuzzleDataCollection;
  }

  /**
   * Copy constructor
   *
   * @param kuzzleDataMapping - The CollectionMapping object to copy
   */
  public CollectionMapping(final CollectionMapping kuzzleDataMapping) {
    this(kuzzleDataMapping.dataCollection, kuzzleDataMapping.mapping);
  }

  /**
   * {@link #apply(Options, ResponseListener)}
   */
  public CollectionMapping apply() {
    return this.apply(null, null);
  }

  /**
   * {@link #apply(Options, ResponseListener)}
   */
  public CollectionMapping apply(final Options options) {
    return this.apply(options, null);
  }

  /**
   * {@link #apply(Options, ResponseListener)}
   */
  public CollectionMapping apply(final ResponseListener<CollectionMapping> listener) {
    return this.apply(null, listener);
  }

  /**
   * Applies this mapping content to the data collection.
   *
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public CollectionMapping apply(final Options options, final ResponseListener<CollectionMapping> listener) {
    JSONObject data = new JSONObject();
    JSONObject properties = new JSONObject();
    try {
      properties.put("properties", this.mapping);
      data.put("body", properties);
      this.kuzzle.addHeaders(data, this.headers);
      this.kuzzle.query(this.dataCollection.makeQueryArgs("collection", "updateMapping"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            listener.onSuccess(CollectionMapping.this);
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
   * {@link #refresh(Options, ResponseListener)}
   */
  public void refresh(final ResponseListener<CollectionMapping> listener) {
    refresh(null, listener);
  }

  /**
   * Gets a refreshed copy of the current object
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void refresh(final Options options, @NonNull final ResponseListener<CollectionMapping> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("CollectionMapping.refresh: listener callback missing");
    }

    JSONObject data = new JSONObject();
    try {
      this.kuzzle.addHeaders(data, this.headers);
      this.kuzzle.query(this.dataCollection.makeQueryArgs("collection", "getMapping"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject args) {
          try {
            CollectionMapping newMapping = new CollectionMapping(CollectionMapping.this.dataCollection);
            JSONObject mappings = args.getJSONObject(CollectionMapping.this.dataCollection.getIndex()).getJSONObject("mappings");
            newMapping.mapping = mappings.getJSONObject(CollectionMapping.this.collection);

            listener.onSuccess(newMapping);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject object) {
          listener.onError(object);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Remove a field from this mapping
   * Changes made by this function won't be applied until you call the apply method
   *
   * @param field - Field name to remove
   * @return this
   */
  public CollectionMapping remove(final String field) {
    if (this.mapping.has(field)) {
      CollectionMapping.this.mapping.remove(field);
    }

    return this;
  }

  /**
   * Attach a mapping to a field
   *
   * @param field - Field name
   * @param mapping - Field mapping
   * @return this
   * @throws JSONException 
   */
  public CollectionMapping set(final String field, final JSONObject mapping) throws JSONException {
    this.mapping.put(field, mapping);
    return this;
  }

  /**
   * Get the global headers for this object
   *
   * @return global headers for this object
   */
  public JSONObject getHeaders() {
    return headers;
  }

  /**
   * {@link #setHeaders(JSONObject, boolean)}
   */
  public CollectionMapping setHeaders(final JSONObject content) {
    return this.setHeaders(content, false);
  }

  /**
   * Helper function allowing to set headers while chaining calls.
   * If the replace argument is set to true, replace the current headers with the provided content.
   * Otherwise, it appends the content to the current headers, only replacing already existing values
   *
   * @param content - Headers to append or replace
   * @param replace - false (default): append the content, true: replace the current headers
   * @return this
   */
  public CollectionMapping setHeaders(final JSONObject content, final boolean replace) {
    try {
      if (content == null) {
        if (replace) {
          this.headers = new JSONObject();
        }

        return this;
      }

      if (replace) {
        this.headers = new JSONObject(content.toString());
      } else {
        for (Iterator ite = content.keys(); ite.hasNext(); ) {
          String key = (String) ite.next();
          this.headers.put(key, content.get(key));
        }
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * Get this mapping raw content
   *
   * @return mapping raw content
   */
  public JSONObject getMapping() {
    return mapping;
  }
}
