package io.kuzzle.sdk.core;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.SubscribeListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.responses.NotificationResponse;

public class Document {
  private final Collection dataCollection;
  private final String collection;
  private final Kuzzle kuzzle;
  private JSONObject headers;

  private String id;
  private JSONObject content;
  private JSONObject meta;
  private long version = -1;

  /**
   * Kuzzle handles documents either as real-time messages or as stored documents.
   * Document is the object representation of one of these documents.
   *
   * @param kuzzleDataCollection - An instantiated Collection object
   * @param id                   - Unique document identifier
   * @param content              - The content of the document
   * @param meta                 - Document metadata
   * @throws JSONException 
   */
  public Document(@NonNull final Collection kuzzleDataCollection, final String id, final JSONObject content, final JSONObject meta) throws JSONException {
    if (kuzzleDataCollection == null) {
      throw new IllegalArgumentException("Document: Collection argument missing");
    }

    this.dataCollection = kuzzleDataCollection;
    this.collection = kuzzleDataCollection.getCollection();
    this.kuzzle = kuzzleDataCollection.getKuzzle();
    this.setId(id);
    this.setContent(content, true);

    if (meta != null) {
      this.meta = new JSONObject(meta.toString());
    }

    this.headers = kuzzleDataCollection.getHeaders();
  }

  /**
   * Kuzzle handles documents either as real-time messages or as stored documents.
   * Document is the object representation of one of these documents.
   *
   * @param kuzzleDataCollection - An instantiated Collection object
   * @param id                   - Unique document identifier
   * @param content              - The content of the document
   * @throws JSONException 
   */
  public Document(@NonNull final Collection kuzzleDataCollection, final String id, final JSONObject content) throws JSONException {
    this(kuzzleDataCollection, id, content, null);
  }

  /**
   * Kuzzle handles documents either as real-time messages or as stored documents.
   * Document is the object representation of one of these documents.
   *
   * @param kuzzleDataCollection - An instantiated Collection object
   * @throws JSONException 
   */
  public Document(final Collection kuzzleDataCollection) throws JSONException {
    this(kuzzleDataCollection, null, null, null);
  }


  /**
   * Kuzzle handles documents either as real-time messages or as stored documents.
   * Document is the object representation of one of these documents.
   *
   * @param kuzzleDataCollection - An instantiated Collection object
   * @param id                   - Unique document identifier
   * @throws JSONException 
   */
  public Document(final Collection kuzzleDataCollection, final String id) throws JSONException {
    this(kuzzleDataCollection, id, null, null);
  }

  /**
   * Kuzzle handles documents either as real-time messages or as stored documents.
   * Document is the object representation of one of these documents.
   *
   * @param kuzzleDataCollection - An instantiated Collection object
   * @param content              - The content of the document
   * @throws JSONException 
   */
  public Document(final Collection kuzzleDataCollection, final JSONObject content) throws JSONException {
    this(kuzzleDataCollection, null, content, null);
  }

  /**
   * Kuzzle handles documents either as real-time messages or as stored documents.
   * Document is the object representation of one of these documents.
   *
   * @param kuzzleDataCollection - An instantiated Collection object
   * @param content              - The content of the document
   * @param meta                 - Document metadata
   * @throws JSONException 
   */
  public Document(final Collection kuzzleDataCollection, final JSONObject content, final JSONObject meta) throws JSONException {
    this(kuzzleDataCollection, null, content, meta);
  }

  /**
   * {@link #delete(Options, ResponseListener)}
   */
  public void delete(final Options options) {
    this.delete(options, null);
  }

  /**
   * {@link #delete(Options, ResponseListener)}
   */
  public void delete(final ResponseListener<String> listener) {
    this.delete(null, listener);
  }

  /**
   * {@link #delete(Options, ResponseListener)}
   */
  public void delete() {
    this.delete(null, null);
  }

  /**
   * Delete this document from Kuzzle
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void delete(final Options options, final ResponseListener<String> listener) {
    try {
      if (this.id == null) {
        throw new IllegalStateException("Document.delete: cannot delete a document without a document ID");
      }

      this.kuzzle.query(this.dataCollection.makeQueryArgs("document", "delete"), this.serialize(), options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject object) {
          setId(null);
          if (listener != null) {
            try {
              listener.onSuccess(object.getString("result"));
            } catch (JSONException e) {
              e.printStackTrace();
            };
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
  }

  /**
   * {@link #exists(Options, ResponseListener)}
   */
  public void exists(@NonNull final ResponseListener<Boolean> listener) {
    this.exists(null, listener);
  }

  /**
   * Ask Kuzzle if this document exists
   * 
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void exists(final Options options, @NonNull final ResponseListener<Boolean> listener) {
    if (this.id == null) {
      throw new IllegalStateException("Document.exists: cannot check if the document exists if no id has been provided");
    }

    if (listener == null) {
      throw new IllegalArgumentException("Document.exists: a valid ResponseListener object is required");
    }

    try {
      this.kuzzle.query(this.dataCollection.makeQueryArgs("document", "exists"), this.serialize(), options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject object) {
          try {
            listener.onSuccess(object.getBoolean("result"));
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
   * {@link #refresh(Options, ResponseListener)}
   */
  public void refresh(@NonNull final ResponseListener<Document> listener) {
    this.refresh(null, listener);
  }

  /**
   * Gets a refreshed copy of this document from Kuzzle
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void refresh(final Options options, @NonNull final ResponseListener<Document> listener) {
    if (this.id == null) {
      throw new IllegalStateException("Document.refresh: cannot retrieve a document if no id has been provided");
    }

    if (listener == null) {
      throw new IllegalArgumentException("Document.refresh: a valid ResponseListener object is required");
    }

    try {
      JSONObject content = new JSONObject();
      content.put("_id", this.getId());

      this.kuzzle.query(this.dataCollection.makeQueryArgs("document", "get"), content, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject args) {
          try {
            JSONObject result = args.getJSONObject("result");
            Document newDocument = new Document(
              Document.this.dataCollection,
              result.getString("_id"),
              result.getJSONObject("_source"),
              result.getJSONObject("_meta")
            );

            newDocument.setVersion(result.getLong("_version"));
            listener.onSuccess(newDocument);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject arg) {
          listener.onError(arg);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #save(Options, ResponseListener)}
   */
  public Document save() {
    return save(null, null);
  }

  /**
   * {@link #save(Options, ResponseListener)}
   */
  public Document save(final Options options) {
    return this.save(options, null);
  }

  /**
   * {@link #save(Options, ResponseListener)}
   */
  public Document save(final ResponseListener<Document> listener) {
    return save(null, listener);
  }

  /**
   * Saves this document into Kuzzle.
   * If this is a new document, this function will create it in Kuzzle and the id property will be made available.
   * Otherwise, this method will replace the latest version of this document in Kuzzle by the current content of this object.
   *
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Document save(final Options options, final ResponseListener<Document> listener) {
    try {
      kuzzle.query(this.dataCollection.makeQueryArgs("document", "createOrReplace"), this.serialize(), options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONObject result = response.getJSONObject("result");
            Document.this.setId(result.getString("_id"));
            Document.this.setVersion(result.getLong("_version"));

            if (listener != null) {
              listener.onSuccess(Document.this);
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
   * {@link #publish(Options)}
   */
  public Document publish() {
    return this.publish(null);
  }

  /**
   * Sends the content of this document as a real-time message.
   *
   * @param options - Request options
   * @return this
   */
  public Document publish(final Options options) {
    try {
      kuzzle.query(this.dataCollection.makeQueryArgs("realtime", "publish"), this.serialize(), options, null);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  /**
   * {@link #setContent(JSONObject, boolean)}
   */
  public Document setContent(final JSONObject content) throws JSONException {
    this.setContent(content, false);
    return this;
  }

  /**
   * Sets this document content
   *
   * @param content - New content for this document
   * @param replace - true: replace the current content, false (default): update/append it
   * @return this
   * @throws JSONException 
   */
  public Document setContent(final JSONObject content, final boolean replace) throws JSONException {
    if (replace) {
      if (content != null) {
        this.content = new JSONObject(content.toString());
      }
      else {
        this.content = new JSONObject();
      }
    } else if (content != null) {
      for (Iterator iterator = content.keys(); iterator.hasNext(); ) {
        String key = (String) iterator.next();
        this.content.put(key, content.get(key));
      }
    }

    if (this.content.has("version")) {
      Object version = this.content.get("version");
      if (version instanceof Long || version instanceof Integer) {
        this.version = this.content.getLong("version");
        this.content.remove("version");
      }
    }

    return this;
  }

  /**
   * Set a document field value
   *
   * @param key - Field name to set
   * @param value - New field value
   * @return this
   * @throws JSONException 
   */
  public Document setContent(@NonNull final String key, final Object value) throws JSONException {
    if (key == null) {
      throw new IllegalArgumentException("Document.setContent: key required");
    }

    this.content.put(key, value);
    return this;
  }

  /**
   * {@link #subscribe(RoomOptions, ResponseListener)}
   */
  public SubscribeListener subscribe(@NonNull final ResponseListener<NotificationResponse> listener) {
    return this.subscribe(null, listener);
  }

  /**
   * Subscribe to changes occuring on this document.
   * Throws an error if this document has not yet been created in Kuzzle.
   *
   * @param options - Room object constructor options
   * @param listener - Response callback listener
   * @return an object with a "onDone" callback triggered when the subscription is active
   */
  public SubscribeListener subscribe(final RoomOptions options, @NonNull final ResponseListener<NotificationResponse> listener) {
    if (this.id == null) {
      throw new IllegalStateException("Document.subscribe: cannot subscribe to a document if no ID has been provided");
    }

    SubscribeListener returnValue;

    try {
      JSONObject filters = new JSONObject("{" +
        "\"ids\": {" +
          "\"values\": [\"" + this.id + "\"]" +
        "}" +
      "}");
      returnValue = this.dataCollection.subscribe(filters, options, listener);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return returnValue;
  }

  /**
   * Get the parent data collection name
   *
   * @return parent data collection name
   */
  public String getCollection() {
    return collection;
  }

  /**
   * Document content getter
   *
   * @return current document content
   */
  public JSONObject getContent() {
    return this.content;
  }


  /**
   * Get document content field
   *
   * @param key - Field name to get
   * @return field value
   * @throws JSONException 
   */
  public Object getContent(final String key) throws JSONException {
    if (this.content.has(key)) {
      return this.content.get(key);
    }

    return null;
  }

  /**
   * Document metadata getter
   *
   * @return this document metadata
   */
  public JSONObject getMeta() {
    return this.meta;
  }


  /**
   * Get a metadata field value
   *
   * @param key - Metadata field name to get
   * @return metadata field value
   * @throws JSONException 
   */
  public Object getMeta(final String key) throws JSONException {
    if (this.meta.has(key)) {
      return this.meta.get(key);
    }

    return null;
  }

  /**
   * {@link #setHeaders(JSONObject, boolean)}
   */
  public Document setHeaders(final JSONObject content) {
    return this.setHeaders(content, false);
  }

  /**
   * Replace or append/update global headers for this object
   * 
   * @param content - new headers content
   * @param replace - true: replace the current headers, false (default): append/update
   * @return this
   */
  public Document setHeaders(final JSONObject content, final boolean replace) {
    try {
      if (content == null) {
        if (replace) {
          this.content = new JSONObject();
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
   * Get the global headers for this object
   *
   * @return global headers for this object
   */
  public JSONObject getHeaders() {
    return this.headers;
  }

  /**
   * Unique identifier getter
   *
   * @return this document unique identifier
   */
  public String getId() {
    return this.id;
  }

  /**
   * Set this document unique identifier
   *
   * @param id - New document unique identifier
   * @return this
   */
  public Document setId(final String id) {
    this.id = id;
    return this;
  }

  /**
   * Get this document version 
   *
   * @return this document version
   */
  public long getVersion() {
    return this.version;
  }

  /**
   * Serializes this document into a plain old JSON object
   *
   * @return JSON object representing this document
   */
  public JSONObject serialize() {
    JSONObject data = new JSONObject();

    try {
      if (this.id != null) {
        data.put("_id", this.getId());
      }

      if (this.version != -1) {
        data.put("_version", this.version);
      }

      data.put("body", this.getContent());
      this.kuzzle.addHeaders(data, getHeaders());
    }
    catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return data;
  }

  public String toString() {
    return this.serialize().toString();
  }

  /**
   * Set this document version
   *
   * @param version - New document version
   * @return  this
   */
  public Document setVersion(long version) {
    if (version > 0) {
      this.version = version;
    }

    return this;
  }
}
