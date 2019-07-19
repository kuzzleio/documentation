package io.kuzzle.sdk.core;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.SubscribeListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.responses.SearchResult;
import io.kuzzle.sdk.responses.NotificationResponse;

public class Collection {
  private final Kuzzle kuzzle;
  private final String collection;
  private final String index;
  private ResponseListener<Room> subscribeCallback;
  private JSONObject subscribeError = null;
  private Room subscribeRoom = null;

  protected JSONObject headers;

  /**
   * Constructor
   *
   * @param kuzzle  Kuzzle instance
   * @param collection  Data collection name
   * @param index  Parent data index name
   */
  public Collection(@NonNull final Kuzzle kuzzle, @NonNull final String collection, @NonNull final String index) {
    if (kuzzle == null) {
      throw new IllegalArgumentException("Collection: need a Kuzzle instance to initialize");
    }

    if (index == null || collection == null) {
      throw new IllegalArgumentException("Collection: index and collection required");
    }
    this.kuzzle = kuzzle;
    this.collection = collection;
    this.index = index;

    try {
      this.headers = new JSONObject(kuzzle.getHeaders().toString());
    }
    catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #search(JSONObject, Options, ResponseListener)}
   */
  public void search(final JSONObject filter, final ResponseListener<SearchResult> listener) {
    this.search(filter, new Options(), listener);
  }

  /**
   * Executes a search on the data collection.
   * /!\ There is a small delay between documents creation and their existence in our search layer,
   * usually a couple of seconds.
   * That means that a document that was just been created won’t be returned by this function.
   *
   * @param filters  Search filters to apply
   * @param options  Request options
   * @param listener  Response callback listener
   */
  public void search(final JSONObject filters, @NonNull final Options options, @NonNull final ResponseListener<SearchResult> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("listener cannot be null");
    }
    this.kuzzle.isValid();
    JSONObject data = new JSONObject();
    try {
      if (filters != null) {
        data.put("body", filters);
      }

      this.kuzzle.addHeaders(data, this.getHeaders());

      this.kuzzle.query(makeQueryArgs("document", "search"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject object) {
          try {
            SearchResult response;
            JSONObject aggregations = null;
            JSONArray hits = object.getJSONObject("result").getJSONArray("hits");
            List<Document> docs = new ArrayList<Document>();

            for (int i = 0; i < hits.length(); i++) {
              JSONObject hit = hits.getJSONObject(i);
              Document doc = new Document(Collection.this, hit.getString("_id"), hit.getJSONObject("_source"), hit.getJSONObject("_meta"));

              docs.add(doc);
            }

            if (object.getJSONObject("result").has("_scroll_id")) {
              options.setScrollId(object.getJSONObject("result").getString("_scroll_id"));
            }

            if (object.getJSONObject("result").has("aggregations")) {
              aggregations = object.getJSONObject("result").getJSONObject("aggregations");
            }

            response = new SearchResult(
              Collection.this,
              object.getJSONObject("result").getInt("total"),
              docs,
              aggregations,
              options,
              filters,
              options.getPrevious()
            );

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
   * {@link #scroll(String, Options, ResponseListener)}
   */
  public void scroll(String scrollId, final ResponseListener<SearchResult> listener) {
    this.scroll(scrollId, new Options(), new JSONObject(), listener);
  }

  /**
   * {@link #scroll(String, Options, ResponseListener)}
   */
  public void scroll(String scrollId, final Options options, final ResponseListener<SearchResult> listener) {
    this.scroll(scrollId, options, new JSONObject(), listener);
  }

  /**
   * Gets the next page of results from a previous search or scroll request
   *
   * @param scrollId  Scroll unique identifier
   * @param options  Request options
   * @param listener  Response callback listener
   */
  public void scroll(String scrollId, final Options options, final JSONObject filters, final ResponseListener<SearchResult> listener) {
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

    if (scrollId == null) {
      throw new RuntimeException("Collection.scroll: scrollId is required");
    }

    options.setScrollId(scrollId);

    try {
      this.kuzzle.query(this.kuzzle.buildQueryArgs("document", "scroll"), request, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject object) {
          try {
            SearchResult response;
            JSONArray hits = object.getJSONObject("result").getJSONArray("hits");
            List<Document> docs = new ArrayList<Document>();

            for (int i = 0; i < hits.length(); i++) {
              JSONObject hit = hits.getJSONObject(i);
              Document doc = new Document(Collection.this, hit.getString("_id"), hit.getJSONObject("_source"), hit.getJSONObject("_meta"));

              docs.add(doc);
            }

            if (object.getJSONObject("result").has("_scroll_id")) {
              options.setScrollId(object.getJSONObject("result").getString("_scroll_id"));
            }

            response = new SearchResult(
              Collection.this,
              object.getJSONObject("result").getInt("total"),
              docs,
              new JSONObject(),
              options,
              filters,
              options.getPrevious()
            );

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
   * {@link #scrollSpecifications(String, Options, ResponseListener)}
   */
  public void scrollSpecifications(@NonNull final String scrollId, @NonNull final ResponseListener<JSONObject> listener) {
    this.scrollSpecifications(scrollId, new Options(), listener);
  }

  /**
   * Scrolls through specifications using the provided scrollId
   *
   * @param scrollId  Scroll unique identifier
   * @param options  Request options
   * @param listener  Response callback listener
   */
  public void scrollSpecifications(@NonNull final String scrollId, final Options options, @NonNull final ResponseListener<JSONObject> listener) {
    this.kuzzle.isValid();

    JSONObject data = new JSONObject();

    if (scrollId == null) {
      throw new RuntimeException("Collection.scrollSpecifications: scrollId is required");
    }

    if (listener == null) {
      throw new IllegalArgumentException("listener cannot be null");
    }

    try {
      data.put("scrollId", scrollId);

      this.kuzzle.addHeaders(data, this.getHeaders());

      this.kuzzle.query(makeQueryArgs("collection", "scrollSpecifications"), data, options, new OnQueryDoneListener() {
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
   * {@link #searchSpecifications(JSONObject, Options, ResponseListener)}
   */
  public void searchSpecifications(@NonNull final ResponseListener<JSONObject> listener) {
    this.searchSpecifications(null, new Options(), listener);
  }

  /**
   * {@link #searchSpecifications(JSONObject, Options, ResponseListener)}
   */
  public void searchSpecifications(final JSONObject filters, @NonNull final ResponseListener<JSONObject> listener) {
    this.searchSpecifications(filters, new Options(), listener);
  }

  /**
   * {@link #searchSpecifications(JSONObject, Options, ResponseListener)}
   */
  public void searchSpecifications(final Options options, @NonNull final ResponseListener<JSONObject> listener) {
    this.searchSpecifications(null, options, listener);
  }

  /**
   * Searches specifications across indexes/collections according to the provided filters
   *
   * @param filters Optional filters in ElasticSearch Query DSL format
   * @param options  Request options
   * @param listener  Response callback listener
   */
  public void searchSpecifications(final JSONObject filters, final Options options, @NonNull final ResponseListener<JSONObject> listener) {
    this.kuzzle.isValid();

    JSONObject data = new JSONObject();

    if (listener == null) {
      throw new IllegalArgumentException("listener cannot be null");
    }

    try {
      if (filters != null) {
        data.put("body", new JSONObject()
          .put("query", filters)
        );
      }

      this.kuzzle.addHeaders(data, this.getHeaders());

      this.kuzzle.query(makeQueryArgs("collection", "searchSpecifications"), data, options, new OnQueryDoneListener() {
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
   * Make query args kuzzle . query args.
   *
   * @param controller  API Controller to invoke
   * @param action  Controller action name
   * @return Built query arguments
   */
  protected io.kuzzle.sdk.core.Kuzzle.QueryArgs makeQueryArgs(final String controller, final String action) {
    io.kuzzle.sdk.core.Kuzzle.QueryArgs args = new io.kuzzle.sdk.core.Kuzzle.QueryArgs();
    args.action = action;
    args.controller = controller;
    args.index = this.index;
    args.collection = this.collection;
    return args;
  }

  /**
   * Returns the provided array of Documents with each one being serialized
   *
   * @param documents  Array of Document objects
   * @return A list of serialized documents
   */
  private JSONArray serializeDocuments(Document[] documents) throws JSONException {
    JSONArray serializedDocuments = new JSONArray();

    for (Document document : documents) {
      serializedDocuments.put(document.serialize());
    }

    return serializedDocuments;
  }

  /**
   * {@link #count(JSONObject, Options, ResponseListener)}
   */
  public void count(final JSONObject filters, @NonNull final ResponseListener<Integer> listener) {
    this.count(filters, null, listener);
  }

  /**
   * {@link #count(JSONObject, Options, ResponseListener)}
   */
  public void count(@NonNull final ResponseListener<Integer> listener) {
    this.count(null, null, listener);
  }

  /**
   * Returns the number of documents matching the provided set of filters.
   * There is a small delay between documents creation and their existence in our search layer,
   * usually a couple of seconds.
   * That means that a document that was just been created won’t be returned by this function
   *
   * @param filters  Search filters
   * @param options  Request options
   * @param listener  Response callback listener
   */
  public void count(final JSONObject filters, final Options options, @NonNull final ResponseListener<Integer> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Collection.count: listener required");
    }
    JSONObject data = new JSONObject();
    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      data.put("body", filters);
      this.kuzzle.query(makeQueryArgs("document", "count"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result").getInt("count"));
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
   * {@link #create(JSONObject, Options, ResponseListener)}
   */
  public Collection create(final Options options) {
    return this.create(null, options, null);
  }

  /**
   * {@link #create(JSONObject, Options, ResponseListener)}
   */
  public Collection create() {
    return this.create(null, null, null);
  }

  /**
   * {@link #create(JSONObject, Options, ResponseListener)}
   */
  public Collection create(final ResponseListener<JSONObject> listener) {
    return this.create(null, null, listener);
  }

  /**
   * {@link #create(JSONObject, Options, ResponseListener)}
   */
  public Collection create(final Options options, final ResponseListener<JSONObject> listener) {
    return this.create(null, options, listener);
  }

  /**
   * {@link #create(JSONObject, Options, ResponseListener)}
   */
  public Collection create(final JSONObject mapping) {
    return this.create(mapping, null, null);
  }

  /**
   * {@link #create(JSONObject, Options, ResponseListener)}
   */
  public Collection create(final JSONObject mapping, final Options options) {
    return this.create(mapping, options, null);
  }

  /**
   * {@link #create(JSONObject, Options, ResponseListener)}
   */
  public Collection create(final JSONObject mapping, final ResponseListener<JSONObject> listener) {
    return this.create(mapping, null, listener);
  }

 /**
   * Create a new empty data collection, with associated mappings.
   * Kuzzle automatically creates data collections when storing documents,
   * but there are cases where we want to create and prepare data collections
   * before storing documents in it.
   *
   * @param mapping  Collection mapping
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection create(final JSONObject mapping, final Options options, final ResponseListener<JSONObject> listener) {
    JSONObject data = new JSONObject();

    try {
      if (mapping != null) {
        data.put("body", mapping);
      }

      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("collection", "create"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              listener.onSuccess(response.getJSONObject("result"));
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #createDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection createDocument(final String id, @NonNull final JSONObject content) throws JSONException {
    return this.createDocument(id, content, null, null);
  }

  /**
   * {@link #createDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection createDocument(final String id, @NonNull final JSONObject content, Options options) throws JSONException {
    return this.createDocument(id, content, options, null);
  }

  /**
   * {@link #createDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection createDocument(final String id, @NonNull final JSONObject content, final ResponseListener<Document> listener) throws JSONException {
    return this.createDocument(id, content, null, listener);
  }

  /**
   * {@link #createDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection createDocument(@NonNull final JSONObject content) throws JSONException {
    return this.createDocument(null, content, null, null);
  }

  /**
   * {@link #createDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection createDocument(@NonNull final JSONObject content, Options options) throws JSONException {
    return this.createDocument(null, content, options, null);
  }

  /**
   * {@link #createDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection createDocument(@NonNull final JSONObject content, final ResponseListener<Document> listener) throws JSONException {
    return this.createDocument(null, content, null, listener);
  }

  /**
   * {@link #createDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection createDocument(@NonNull final JSONObject content, Options options, final ResponseListener<Document> listener) throws JSONException {
    return this.createDocument(null, content, options, listener);
  }

  /**
   * Create document kuzzle data collection.
   *
   * @param id        document ID
   * @param content   document content
   * @param options      Request options
   * @param listener  Response callback listener
   * @return this
   * @throws JSONException
   */
  public Collection createDocument(final String id, @NonNull final JSONObject content, Options options, final ResponseListener<Document> listener) throws JSONException {
    if (content == null) {
      throw new IllegalArgumentException("Cannot create an empty document");
    }

    Document doc = new Document(this, id, content);
    return this.createDocument(doc, options, listener);
  }

  /**
   * {@link #createDocument(Document, Options, ResponseListener)}
   */
  public Collection createDocument(final Document document) {
    return this.createDocument(document, null, null);
  }

  /**
   * {@link #createDocument(Document, Options, ResponseListener)}
   */
  public Collection createDocument(final Document document, final Options options) {
    return this.createDocument(document, options, null);
  }

  /**
   * {@link #createDocument(Document, Options, ResponseListener)}
   */
  public Collection createDocument(final Document document, final ResponseListener<Document> listener) {
    return this.createDocument(document, null, listener);
  }

  /**
   * Create a new document in kuzzle
   *
   * @param document the document
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection createDocument(final Document document, final Options options, final ResponseListener<Document> listener) {
    String action = "create";
    JSONObject data = document.serialize();

    if (options != null && options.getIfExist().equals("replace")) {
      action = "createOrReplace";
    }

    this.kuzzle.addHeaders(data, this.getHeaders());

    try {
      this.kuzzle.query(makeQueryArgs("document", action), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              JSONObject result = response.getJSONObject("result");
              Document document = new Document(Collection.this, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta"));
              document.setVersion(result.getLong("_version"));
              listener.onSuccess(document);
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #collectionMapping(JSONObject)}
   */
  public CollectionMapping collectionMapping() {
    return new CollectionMapping(this);
  }

  /**
   * CollectionMapping constructor, attaching it to this
   * Collection object
   *
   * @param mapping  Raw mapping to declare
   * @return a newly instantiated CollectionMapping object
   */
  public CollectionMapping collectionMapping(JSONObject mapping) {
    return new CollectionMapping(this, mapping);
  }

  /**
   * {@link #deleteDocument(String, Options, ResponseListener)}
   */
  public Collection deleteDocument(@NonNull final String documentId) {
    return this.deleteDocument(documentId, null, null);
  }

  /**
   * {@link #deleteDocument(String, Options, ResponseListener)}
   */
  public Collection deleteDocument(@NonNull final String documentId, Options options) {
    return this.deleteDocument(documentId, options, null);
  }

  /**
   * {@link #deleteDocument(String, Options, ResponseListener)}
   */
  public Collection deleteDocument(@NonNull final String documentId, final ResponseListener<String> listener) {
    return this.deleteDocument(documentId, null, listener);
  }

  /**
   * Delete a single document
   *
   * @param documentId  Document unique identifier
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection deleteDocument(@NonNull final String documentId, final Options options, final ResponseListener<String> listener) {
    if (documentId == null) {
      throw new IllegalArgumentException("Collection.deleteDocument: documentId required");
    }
    return this.deleteDocument(documentId, null, options, listener, null);
  }

  /**
   * {@link #deleteDocument(JSONObject, Options, ResponseListener)}
   */
  public Collection deleteDocument(@NonNull final JSONObject filters) {
    return this.deleteDocument(filters, null, null);
  }

  /**
   * {@link #deleteDocument(JSONObject, Options, ResponseListener)}
   */
  public Collection deleteDocument(@NonNull final JSONObject filters, final Options options) {
    return this.deleteDocument(filters, options, null);
  }

  /**
   * {@link #deleteDocument(JSONObject, Options, ResponseListener)}
   */
  public Collection deleteDocument(@NonNull final JSONObject filters, final ResponseListener<String[]> listener) {
    return this.deleteDocument(filters, null, listener);
  }

  /**
   * Delete documents using search filters
   *
   * @param filters  Search filters
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection deleteDocument(@NonNull final JSONObject filters, final Options options, final ResponseListener<String[]> listener) {
    if (filters == null) {
      throw new IllegalArgumentException("Collection.deleteDocument: filters required");
    }
    return this.deleteDocument(null, filters, options, null, listener);
  }

  /**
   * Delete either a single document or multiple ones using search filters
   *
   * @param documentId  Document unique identifier
   * @param filter  Search fitlers
   * @param options  Request options
   * @param listener  Response callback listener (single document delete)
   * @param listener2  Response callback listener (document search delete)
   * @return this
   */
  protected Collection deleteDocument(final String documentId, final JSONObject filter, final Options options, final ResponseListener<String> listener, final ResponseListener<String[]> listener2) {
    JSONObject data = new JSONObject();
    String action;
    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      if (documentId != null) {
        data.put("_id", documentId);
        action = "delete";
      } else {
        data.put("body", new JSONObject().put("query", filter));
        action = "deleteByQuery";
      }
      this.kuzzle.query(makeQueryArgs("document", action), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            if (listener != null) {
              listener.onSuccess(response.getJSONObject("result").getString("_id"));
            } else if (listener2 != null) {
              JSONArray array = response.getJSONObject("result").getJSONArray("hits");
              int length = array.length();
              String[] ids = new String[length];
              for (int i = 0; i < length; i++) {
                ids[i] = array.getString(i);
              }
              listener2.onSuccess(ids);
            }
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          } else if (listener2 != null) {
            listener2.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  /**
   * {@link #deleteSpecifications(Options, ResponseListener)}
   */
  public Collection deleteSpecifications() throws JSONException {
    return this.deleteSpecifications(new Options(), null);
  }

  /**
   * {@link #deleteSpecifications(Options, ResponseListener)}
   */
  public Collection deleteSpecifications(final Options options) throws JSONException {
    return this.deleteSpecifications(options, null);
  }

  /**
   * {@link #deleteSpecifications(Options, ResponseListener)}
   */
  public Collection deleteSpecifications(final ResponseListener<JSONObject> listener) throws JSONException {
    return this.deleteSpecifications(new Options(), listener);
  }

  /**
   * Deletes the current specifications for this collection
   *
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   * @throws JSONException
   */
  public Collection deleteSpecifications(final Options options, final ResponseListener<JSONObject> listener) throws JSONException {
    JSONObject data = new JSONObject();

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("collection", "deleteSpecifications"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              listener.onSuccess(response.getJSONObject("result"));
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #document(String, JSONObject)}
   */
  public Document document() throws JSONException {
    return new Document(this);
  }

  /**
   * {@link #document(String, JSONObject)}
   */
  public Document document(final String id) throws JSONException {
    return new Document(this, id);
  }

  /**
   * {@link #document(String, JSONObject)}
   */
  public Document document(final JSONObject content) throws JSONException {
    return new Document(this, content);
  }

  /**
   * Instantiates a Document object with a preset unique
   * identifier and content.
   * This document is attached to this data collection
   *
   * @param id  Document unique identifier
   * @param content  Document content
   * @return newly instantiated Document object
   * @throws JSONException
   */
  public Document document(final String id, final JSONObject content) throws JSONException {
    return new Document(this, id, content);
  }

  /**
   * {@link #documentExists(String, Options, ResponseListener)}
   */
  public void documentExists(@NonNull final String documentId, @NonNull final ResponseListener<JSONObject> listener) {
    this.documentExists(documentId, null, listener);
  }

  /**
   * Asks Kuzzle API if the provided document exists
   *
   * @param documentId  Document unique identifier
   * @param options  Request options
   * @param listener  Response callback listener
   */
  public void documentExists(@NonNull final String documentId, final Options options, final ResponseListener<JSONObject> listener) {
    if (documentId == null) {
      throw new IllegalArgumentException("Collection.documentExists: documentId required");
    }
    if (listener == null) {
      throw new IllegalArgumentException("Collection.documentExists: listener required");
    }

    try {
      JSONObject data = new JSONObject().put("_id", documentId);
      this.kuzzle.addHeaders(data, this.getHeaders());

      this.kuzzle.query(makeQueryArgs("document", "exists"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          listener.onSuccess(response);
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
   * {@link #fetchDocument(String, Options, ResponseListener)}
   */
  public void fetchDocument(@NonNull final String documentId, @NonNull final ResponseListener<Document> listener) {
    this.fetchDocument(documentId, null, listener);
  }

  /**
   * Fetch a document from Kuzzle
   *
   * @param documentId  Document unique identifier
   * @param options  Request options
   * @param listener  Response callback listener
   */
  public void fetchDocument(@NonNull final String documentId, final Options options, final ResponseListener<Document> listener) {
    if (documentId == null) {
      throw new IllegalArgumentException("Collection.fetchDocument: documentId required");
    }
    if (listener == null) {
      throw new IllegalArgumentException("Collection.fetchDocument: listener required");
    }

    try {
      JSONObject data = new JSONObject().put("_id", documentId);
      this.kuzzle.addHeaders(data, this.getHeaders());

      this.kuzzle.query(makeQueryArgs("document", "get"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONObject result = response.getJSONObject("result");
            Document document = new Document(Collection.this, result.getString("_id"), result.getJSONObject("_source"));

            document.setVersion(result.getLong("_version"));
            listener.onSuccess(document);
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
   * {@link #getMapping(Options, ResponseListener)}
   */
  public void getMapping(@NonNull final ResponseListener<CollectionMapping> listener) {
    this.getMapping(null, listener);
  }

  /**
   * Get the mapping for this data collection
   *
   * @param options  Request options
   * @param listener  Response callback listener
   */
  public void getMapping(final Options options, @NonNull final ResponseListener<CollectionMapping> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Collection.getMapping: listener required");
    }
    new CollectionMapping(this).refresh(options, listener);
  }

  /**
   * {@link #getSpecifications(Options, ResponseListener)}
   */
  public void getSpecifications(@NonNull final ResponseListener<JSONObject> listener) throws JSONException {
    this.getSpecifications(new Options(), listener);
  }

  /**
   * Get the specifications for this collection
   *
   * @param options  Request options
   * @param listener  Response callback listener
   * @throws JSONException
   */
  public void getSpecifications(final Options options, @NonNull final ResponseListener<JSONObject> listener) throws JSONException {
    JSONObject data = new JSONObject()
      .put("body", new JSONObject());

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("collection", "getSpecifications"), data, options, new OnQueryDoneListener() {
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
   * Create multiple documents
   *
   * @param documents  Array of Document objects to create
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   * @throws JSONException
   */
  public Collection mCreateDocument(final Document[] documents, final Options options, final ResponseListener<JSONObject> listener) throws JSONException {
    if (documents.length == 0) {
      throw new IllegalArgumentException("Collection.mCreateDocument: The document array should not be empty");
    }

    JSONObject data = new JSONObject()
      .put("body", new JSONObject()
        .put("documents", this.serializeDocuments(documents))
      );

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("document", "mCreate"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              listener.onSuccess(response.getJSONObject("result"));
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #mCreateDocument(Document[], Options, ResponseListener)}
   */
  public Collection mCreateDocument(final Document[] documents, final ResponseListener<JSONObject> listener) throws JSONException {
    return this.mCreateDocument(documents, new Options(), listener);
  }

  /**
   * {@link #mCreateDocument(Document[], Options, ResponseListener)}
   */
  public Collection mCreateDocument(final Document[] documents, Options options) throws JSONException {
    return this.mCreateDocument(documents, options, null);
  }

  /**
   * {@link #mCreateDocument(Document[], Options, ResponseListener)}
   */
  public Collection mCreateDocument(final Document[] documents) throws JSONException {
    return this.mCreateDocument(documents, new Options(), null);
  }

  /**
   * Create or replace multiple documents
   *
   * @param documents  Array of Document objects to create or replace
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   * @throws JSONException
   */
  public Collection mCreateOrReplaceDocument(final Document[] documents, final Options options, final ResponseListener<JSONObject> listener) throws JSONException {
    if (documents.length == 0) {
      throw new IllegalArgumentException("Collection.mCreateOrReplaceDocument: The document array should not be empty");
    }

    JSONObject data = new JSONObject()
      .put("body", new JSONObject()
        .put("documents", this.serializeDocuments(documents))
      );

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("document", "mCreateOrReplace"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              listener.onSuccess(response.getJSONObject("result"));
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #mCreateOrReplaceDocument(Document[], Options, ResponseListener)}
   */
  public Collection mCreateOrReplaceDocument(final Document[] documents, final ResponseListener<JSONObject> listener) throws JSONException {
    return this.mCreateOrReplaceDocument(documents, new Options(), listener);
  }

  /**
   * {@link #mCreateOrReplaceDocument(Document[], Options, ResponseListener)}
   */
  public Collection mCreateOrReplaceDocument(final Document[] documents, Options options) throws JSONException {
    return this.mCreateOrReplaceDocument(documents, options, null);
  }

  /**
   * {@link #mCreateOrReplaceDocument(Document[], Options, ResponseListener)}
   */
  public Collection mCreateOrReplaceDocument(final Document[] documents) throws JSONException {
    return this.mCreateOrReplaceDocument(documents, new Options(), null);
  }

  /**
   * Delete multiple documents using their unique IDs
   *
   * @param documentIds  Array of document IDs to delete
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   * @throws JSONException
   */
  public Collection mDeleteDocument(final String[] documentIds, final Options options, final ResponseListener<JSONArray> listener) throws JSONException {
    if (documentIds.length == 0) {
      throw new IllegalArgumentException("Collection.mDeleteDocument: The document IDs array should not be empty");
    }

    JSONObject data = new JSONObject()
      .put("body", new JSONObject()
        .put("ids", new JSONArray(Arrays.asList(documentIds)))
      );

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("document", "mDelete"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              listener.onSuccess(response.getJSONArray("result"));
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #mDeleteDocument(String[], Options, ResponseListener)}
   */
  public Collection mDeleteDocument(final String[] documentIds, final ResponseListener<JSONArray> listener) throws JSONException {
    return this.mDeleteDocument(documentIds, new Options(), listener);
  }

  /**
   * {@link #mDeleteDocument(String[], Options, ResponseListener)}
   */
  public Collection mDeleteDocument(final String[] documentIds, Options options) throws JSONException {
    return this.mDeleteDocument(documentIds, options, null);
  }

  /**
   * {@link #mDeleteDocument(String[], Options, ResponseListener)}
   */
  public Collection mDeleteDocument(final String[] documentIds) throws JSONException {
    return this.mDeleteDocument(documentIds, new Options(), null);
  }

  /**
   * Fetch multiple documents
   *
   * @param documentIds  Array of document IDs to retrieve
   * @param options  Request options
   * @param listener  Response callback listener
   * @throws JSONException
   */
  public void mGetDocument(final String[] documentIds, final Options options, @NonNull final ResponseListener<JSONObject> listener) throws JSONException {
    if (documentIds.length == 0) {
      throw new IllegalArgumentException("Collection.mGetDocument: The document IDs array should not be empty");
    }

    JSONObject data = new JSONObject()
      .put("body", new JSONObject()
        .put("ids", new JSONArray(Arrays.asList(documentIds)))
      );

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("document", "mGet"), data, options, new OnQueryDoneListener() {
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
   * {@link #mGetDocument(String[], Options, ResponseListener)}
   */
  public void mGetDocument(final String[] documentIds, @NonNull final ResponseListener<JSONObject> listener) throws JSONException {
    this.mGetDocument(documentIds, new Options(), listener);
  }

  /**
   * Replace multiple documents
   *
   * @param documents  Array of Document objects to replace
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   * @throws JSONException
   */
  public Collection mReplaceDocument(final Document[] documents, final Options options, final ResponseListener<JSONObject> listener) throws JSONException {
    if (documents.length == 0) {
      throw new IllegalArgumentException("Collection.mReplaceDocument: The document array should not be empty");
    }

    JSONObject data = new JSONObject()
      .put("body", new JSONObject()
        .put("documents", this.serializeDocuments(documents))
      );

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("document", "mReplace"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              listener.onSuccess(response.getJSONObject("result"));
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #mReplaceDocument(Document[], Options, ResponseListener)}
   */
  public Collection mReplaceDocument(final Document[] documents, final ResponseListener<JSONObject> listener) throws JSONException {
    return this.mReplaceDocument(documents, new Options(), listener);
  }

  /**
   * {@link #mReplaceDocument(Document[], Options, ResponseListener)}
   */
  public Collection mReplaceDocument(final Document[] documents, Options options) throws JSONException {
    return this.mReplaceDocument(documents, options, null);
  }

  /**
   * {@link #mReplaceDocument(Document[], Options, ResponseListener)}
   */
  public Collection mReplaceDocument(final Document[] documents) throws JSONException {
    return this.mReplaceDocument(documents, new Options(), null);
  }

  /**
   * Update multiple documents
   *
   * @param documents  Array of Document objects to update
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   * @throws JSONException
   */
  public Collection mUpdateDocument(final Document[] documents, final Options options, final ResponseListener<JSONObject> listener) throws JSONException {
    if (documents.length == 0) {
      throw new IllegalArgumentException("Collection.mUpdateDocument: The document array should not be empty");
    }

    JSONObject data = new JSONObject()
      .put("body", new JSONObject()
        .put("documents", this.serializeDocuments(documents))
      );

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("document", "mUpdate"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              listener.onSuccess(response.getJSONObject("result"));
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #mUpdateDocument(Document[], Options, ResponseListener)}
   */
  public Collection mUpdateDocument(final Document[] documents, final ResponseListener<JSONObject> listener) throws JSONException {
    return this.mUpdateDocument(documents, new Options(), listener);
  }

  /**
   * {@link #mUpdateDocument(Document[], Options, ResponseListener)}
   */
  public Collection mUpdateDocument(final Document[] documents, Options options) throws JSONException {
    return this.mUpdateDocument(documents, options, null);
  }

  /**
   * {@link #mUpdateDocument(Document[], Options, ResponseListener)}
   */
  public Collection mUpdateDocument(final Document[] documents) throws JSONException {
    return this.mUpdateDocument(documents, new Options(), null);
  }

  /**
   * {@link #publishMessage(Document, Options, ResponseListener)}
   */
  public Collection publishMessage(final Document document) {
    return this.publishMessage(document, null, null);
  }

  /**
   * {@link #publishMessage(Document, Options, ResponseListener)}
   */
  public Collection publishMessage(@NonNull final Document document, final ResponseListener<JSONObject> listener) {
    return this.publishMessage(document, null, listener);
  }

  /**
   * {@link #publishMessage(Document, Options, ResponseListener)}
   */
  public Collection publishMessage(@NonNull final Document document, final Options options) {
    return this.publishMessage(document, options, null);
  }

  /**
   * Publish a real-time message
   *
   * @param document  Document to publish
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection publishMessage(@NonNull final Document document, final Options options, final ResponseListener<JSONObject> listener) {
    if (document == null) {
      throw new IllegalArgumentException("Cannot publish a null document");
    }

    return this.publishMessage(document.getContent(), options, listener);
  }

  /**
   * {@link #publishMessage(JSONObject, Options, ResponseListener)}
   */
  public Collection publishMessage(@NonNull final JSONObject content) {
    return this.publishMessage(content, null, null);
  }

  /**
   * {@link #publishMessage(JSONObject, Options, ResponseListener)}
   */
  public Collection publishMessage(@NonNull final JSONObject content, final ResponseListener<JSONObject> listener) {
    return this.publishMessage(content, null, listener);
  }

  /**
   * {@link #publishMessage(JSONObject, Options, ResponseListener)}
   */
  public Collection publishMessage(@NonNull final JSONObject content, final Options options) {
    return this.publishMessage(content, options, null);
  }

  /**
   * Publish a real-time message
   *
   * @param content  Message content
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection publishMessage(@NonNull final JSONObject content, final Options options, final ResponseListener<JSONObject> listener) {
    if (content == null) {
      throw new IllegalArgumentException("Cannot publish null content");
    }

    try {
      JSONObject data = new JSONObject().put("body", content);
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("realtime", "publish"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            listener.onSuccess(response);
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
   * {@link #replaceDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection replaceDocument(@NonNull final String documentId, final JSONObject content) {
    return this.replaceDocument(documentId, content, null, null);
  }

  /**
   * {@link #replaceDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection replaceDocument(@NonNull final String documentId, final JSONObject content, final ResponseListener<Document> listener) {
    return this.replaceDocument(documentId, content, null, listener);
  }

  /**
   * {@link #replaceDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection replaceDocument(@NonNull final String documentId, final JSONObject content, final Options options) {
    return this.replaceDocument(documentId, content, options, null);
  }

  /**
   * Replace an existing document with a new one.
   *
   * @param documentId  Document unique identifier
   * @param content  New document content
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection replaceDocument(@NonNull final String documentId, final JSONObject content, final Options options, final ResponseListener<Document> listener) {
    if (documentId == null) {
      throw new IllegalArgumentException("Collection.replaceDocument: documentId required");
    }

    try {
      JSONObject data = new JSONObject().put("_id", documentId).put("body", content);
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("document", "createOrReplace"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              JSONObject result = response.getJSONObject("result");
              Document document = new Document(Collection.this, result.getString("_id"), result.getJSONObject("_source"));
              document.setVersion(result.getLong("_version"));
              listener.onSuccess(document);
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #validateSpecifications(JSONObject, Options, ResponseListener)}
   */
  public void validateSpecifications(@NonNull JSONObject specifications, @NonNull ResponseListener<Boolean> listener) throws JSONException {
    this.validateSpecifications(specifications, new Options(), listener);
  }

  /**
   * Validates the provided specifications
   *
   * @param specifications  Specifications content
   * @param options  Request options
   * @param listener  Response callback listener
   * @throws JSONException
   */
  public void validateSpecifications(@NonNull JSONObject specifications, Options options, @NonNull final ResponseListener<Boolean> listener) throws JSONException {
    if (specifications == null) {
      throw new IllegalArgumentException("Collection.validateSpecifications: specifications cannot be null");
    }

    if (listener == null) {
      throw new IllegalArgumentException("listener cannot be null");
    }

    JSONObject data = new JSONObject()
      .put("body", new JSONObject()
        .put(this.getIndex(), new JSONObject()
          .put(this.getCollection(), specifications)
        )
      );

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("collection", "validateSpecifications"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result").getBoolean("valid"));
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
   * {@link #room(RoomOptions)}
   */
  public Room room() {
    return this.room(null);
  }

  /**
   * Room object constructor, attaching it to this
   * data collection
   *
   * @param options  Request options
   * @return a newly instantiated Room object
   */
  public Room room(RoomOptions options) {
    return new Room(this, options);
  }

  /**
   * {@link #setHeaders(JSONObject, boolean)}
   */
  public Collection setHeaders(final JSONObject content) {
    return this.setHeaders(content, false);
  }

  /**
   * Sets headers global to this data collection
   *
   * @param content  Headers content
   * @param replace  true: replace existing headers, false: append
   * @return this
   */
  public Collection setHeaders(final JSONObject content, final boolean replace) {
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
   * {@link #subscribe(JSONObject, RoomOptions, ResponseListener)}
   */
  public SubscribeListener subscribe(final JSONObject filters, @NonNull final ResponseListener<NotificationResponse> listener) {
    return this.subscribe(filters, null, listener);
  }

  /**
   * {@link #subscribe(JSONObject, RoomOptions, ResponseListener)}
   */
  public SubscribeListener subscribe(final RoomOptions options, @NonNull final ResponseListener<NotificationResponse> listener) {
    return this.subscribe(null, options, listener);
  }

  /**
   * Subscribes to this data collection with a set of Kuzzle DSL filters.
   *
   * @param filters  Subscription filters
   * @param options  Request options
   * @param listener  Response callback listener
   * @return an object with a onDone() callback triggered when the subscription is active
   */
  public SubscribeListener subscribe(final JSONObject filters, final RoomOptions options, @NonNull final ResponseListener<NotificationResponse> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Collection.subscribe: listener required");
    }
    this.kuzzle.isValid();
    final Room room = new Room(this, options);
    final SubscribeListener subscribeResponseListener = new SubscribeListener();

    room.renew(filters, listener, subscribeResponseListener);

    return subscribeResponseListener;
  }

  /**
   * {@link #truncate(Options, ResponseListener)}
   */
  public Collection truncate() {
    return this.truncate(null, null);
  }

  /**
   * {@link #truncate(Options, ResponseListener)}
   */
  public Collection truncate(final Options options) {
    return this.truncate(options, null);
  }

  /**
   * {@link #truncate(Options, ResponseListener)}
   */
  public Collection truncate(final ResponseListener<JSONObject> listener) {
    return this.truncate(null, listener);
  }

  /**
   * Truncate the data collection, removing all stored documents but keeping all associated mappings.
   *
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection truncate(final Options options, final ResponseListener<JSONObject> listener) {
    JSONObject  data = new JSONObject();
    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("collection", "truncate"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              listener.onSuccess(response.getJSONObject("result"));
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #updateDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection updateDocument(@NonNull final String documentId, @NonNull final JSONObject content) {
    return this.updateDocument(documentId, content, null, null);
  }

  /**
   * {@link #updateDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection updateDocument(@NonNull final String documentId, @NonNull final JSONObject content, final Options options) {
    return this.updateDocument(documentId, content, options, null);
  }

  /**
   * {@link #updateDocument(String, JSONObject, Options, ResponseListener)}
   */
  public Collection updateDocument(@NonNull final String documentId, @NonNull final JSONObject content, final ResponseListener<Document> listener) {
    return this.updateDocument(documentId, content, null, listener);
  }

  /**
   * Update parts of a document
   *
   * @param documentId  Document unique identifier
   * @param content  Document content to update
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection updateDocument(@NonNull final String documentId, @NonNull final JSONObject content, final Options options, final ResponseListener<Document> listener) {
    if (documentId == null) {
      throw new IllegalArgumentException("Collection.updateDocument: documentId required");
    }
    if (content == null) {
      throw new IllegalArgumentException("Collection.updateDocument: content required");
    }

    try {
      JSONObject data = new JSONObject().put("_id", documentId).put("body", content);
      this.kuzzle.addHeaders(data, this.getHeaders());

      if (options != null && options.getRetryOnConflict() > 0) {
        data.put("retryOnConflict", options.getRetryOnConflict());
      }

      this.kuzzle.query(makeQueryArgs("document", "update"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              JSONObject result = response.getJSONObject("result");
              Document document = new Document(Collection.this, result.getString("_id"), result.getJSONObject("_source"));
              document.setVersion(result.getLong("_version"));
              document.refresh(listener);
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * {@link #updateSpecifications(JSONObject, Options, ResponseListener)}
   */
  public Collection updateSpecifications(@NonNull final JSONObject specifications) throws JSONException {
    return this.updateSpecifications(specifications, new Options(), null);
  }

  /**
   * {@link #updateSpecifications(JSONObject, Options, ResponseListener)}
   */
  public Collection updateSpecifications(@NonNull final JSONObject specifications, final Options options) throws JSONException {
    return this.updateSpecifications(specifications, options, null);
  }

  /**
   * {@link #updateSpecifications(JSONObject, Options, ResponseListener)}
   */
  public Collection updateSpecifications(@NonNull final JSONObject specifications, final ResponseListener<JSONObject> listener) throws JSONException {
    return this.updateSpecifications(specifications, new Options(), listener);
  }

  /**
   * Updates the current specifications of this collection
   *
   * @param specifications  Updated specifications content
   * @param options  Request options
   * @param listener  Response callback listener
   * @return this
   */
  public Collection updateSpecifications(@NonNull final JSONObject specifications, final Options options, final ResponseListener<JSONObject> listener) throws JSONException {
    if (specifications == null) {
      throw new IllegalArgumentException("Collection.updateSpecifications: specifications cannot be null");
    }

    JSONObject data = new JSONObject()
      .put("body", new JSONObject()
        .put(this.getIndex(), new JSONObject()
          .put(this.getCollection(), specifications)
        )
      );

    try {
      this.kuzzle.addHeaders(data, this.getHeaders());
      this.kuzzle.query(makeQueryArgs("collection", "updateSpecifications"), data, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            try {
              listener.onSuccess(response.getJSONObject("result"));
            } catch (JSONException e) {
              throw new RuntimeException(e);
            }
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
   * Get the attached Kuzzle object instance.
   *
   * @return attached Kuzzle object instance
   */
  public Kuzzle getKuzzle() {
    return kuzzle;
  }

  /**
   * Get this data collection name
   *
   * @return data collection name
   */
  public String getCollection() {
    return collection;
  }

  /**
   * Get the parent data index name
   *
   * @return parent data index name
   */
  public String getIndex() {
    return this.index;
  }

  /**
   * Get the data collection global headers
   *
   * @return data collection global headers
   */
  public JSONObject getHeaders() {
    return this.headers;
  }
}
