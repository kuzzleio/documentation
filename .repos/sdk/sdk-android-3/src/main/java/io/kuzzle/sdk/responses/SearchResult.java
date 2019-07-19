package io.kuzzle.sdk.responses;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.kuzzle.sdk.core.Document;
import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.listeners.ResponseListener;

public class SearchResult implements KuzzleList<Document> {
  private Collection collection;
  private long total;
  private List<Document> documents;
  private JSONObject aggregations;
  private Options options;
  private JSONObject filters;
  private long fetchedDocument;

  public SearchResult(Collection collection, long total, List<Document> documents) {
    this(collection, total, documents, null, new Options(), new JSONObject(), null);
  }

  public SearchResult(Collection collection, long total, List<Document> documents, JSONObject aggregations) {
    this(collection, total, documents, aggregations, new Options(), new JSONObject(), null);
  }

  public SearchResult(Collection collection, long total, List<Document> documents, JSONObject aggregations, Options options, JSONObject filters) {
    this(collection, total, documents, aggregations, options, filters, null);
  }

  public SearchResult(Collection collection, long total, List<Document> documents, JSONObject aggregations, Options options, @NonNull JSONObject filters, SearchResult previous) {
    this.collection = collection;
    this.total = total;
    this.documents = documents;
    this.aggregations = aggregations;
    this.options = options;
    this.filters = filters;
    this.fetchedDocument = previous != null ? documents.size() + previous.getFetchedDocument() : documents.size();
  }

  /**
   * @return Fetched documents list
   */
  public List<Document> getDocuments() {
    return documents;
  }

  /**
   * @return Total number of fetchable documents
   */
  public long getTotal() {
    return total;
  }

  /**
   * @return Search request aggregations parameters
   */
  public JSONObject getAggregations() {
    return aggregations;
  }

  /**
   * @return Number of fetched documents so far
   */
  public long getFetchedDocument() {
    return fetchedDocument;
  }

  /**
   * @return Parent data collection
   */
  public Collection getCollection() {
    return collection;
  }

  /**
   * @return Search request options
   */
  public Options getOptions() {
    return options;
  }

  /**
   * @return Search request filters
   */
  public JSONObject getFilters() {
    return filters;
  }

  /**
   * Fetches the next batch of documents
   * @param listener Response callback listener
   */
  public void fetchNext(ResponseListener<SearchResult> listener) {
    JSONObject filters;
    Options options;

    try {
      options = new Options(this.options);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    options.setPrevious(this);

    // retrieve next results with scroll if original search use it
    if (options.getScrollId() != null) {
      if (this.fetchedDocument >= this.getTotal()) {
        listener.onSuccess(null);

        return;
      }

      if (options.getFrom() != null) {
        options.setFrom(null);
      }

      if (options.getSize() != null) {
        options.setSize(null);
      }

      this.collection.scroll(options.getScrollId(), options, this.filters, listener);

      return;
    }

    // retrieve next results using ES's search_after
    if (options.getSize() != null && this.filters.has("sort")) {
      if (this.fetchedDocument >= this.getTotal()) {
        listener.onSuccess(null);

        return;
      }

      if (options.getFrom() != null) {
        options.setFrom(null);
      }

      try {
        JSONArray searchAfter = new JSONArray();

        for (int i = 0; i < this.filters.getJSONArray("sort").length(); i++) {
          Document doc = this.getDocuments().get(this.getDocuments().size() - 1);
          searchAfter.put(doc.getContent().get(this.filters.getJSONArray("sort").getJSONObject(i).keys().next()));
        }

        this.filters.put("search_after", searchAfter);
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }

      this.collection.search(this.filters, options, listener);

      return;
    }

    // retrieve next results with  from/size if original search use it
    if (options.getFrom() != null && options.getSize() != null) {
      try {
        filters = new JSONObject(this.filters.toString());
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }

      options.setFrom(options.getFrom() + options.getSize());

      if (options.getFrom() >= this.getTotal()) {
        listener.onSuccess(null);

        return;
      }

      this.collection.search(filters, options, listener);

      return;
    }

    JSONObject error;
    try {
      error = new JSONObject("Unable to retrieve next results from search: missing scrollId or from/size params");
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    listener.onError(error);
  }
}
