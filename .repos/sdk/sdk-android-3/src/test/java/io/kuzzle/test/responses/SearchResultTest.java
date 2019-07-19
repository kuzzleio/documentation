package io.kuzzle.test.responses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Document;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.responses.SearchResult;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchResultTest {
  private Collection collection;
  private Kuzzle kuzzle;
  private ResponseListener listener;
  private long total;
  private ArrayList<Document> documents;
  private Options options;
  private String scrollId;
  private String scroll;

  @Before
  public void setUp() throws URISyntaxException {
    options = new Options();
    options.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", options, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);
    kuzzle = spy(extended);
    when(kuzzle.getHeaders()).thenReturn(new JSONObject());

    collection = new Collection(kuzzle, "test", "index");
    listener = mock(ResponseListener.class);
    total = (long) 42;
    documents = new ArrayList<>();
    scrollId = "someScrollId";
    scroll = "someScroll";
    try {
      documents.add(new Document(collection, "foo", new JSONObject().put("name", "John").put("age", 42)));
      documents.add(new Document(collection, "bar", new JSONObject().put("name", "Michael").put("age", 36)));
    } catch(JSONException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void checkConstructorArguments() {
    SearchResult searchResult = new SearchResult(collection, total, documents, null, options, null);
    assertEquals(searchResult.getCollection(), collection);
    assertEquals(searchResult.getTotal(), total);
    assertEquals(searchResult.getDocuments(), documents);
    assertEquals(searchResult.getAggregations(), null);
    assertEquals(searchResult.getOptions(), options);
    assertEquals(searchResult.getFilters(), null);
    assertEquals(searchResult.getFetchedDocument(), (long) 2);
  }

  @Test
  public void fetchNextBySearchAfter() throws JSONException {
    Options localOptions = new Options(options);
    localOptions
      .setSize((long) 2);

    JSONObject filters = new JSONObject()
      .put("sort", new JSONArray()
        .put(new JSONObject()
          .put("age", "desc")
        )
        .put(new JSONObject()
          .put("name", "asc")
        )
      );

    collection = spy(collection);
    SearchResult searchResult = new SearchResult(collection, total, documents, null, localOptions, filters);

    searchResult.fetchNext(listener);

    JSONObject expectedFilters = new JSONObject()
      .put("search_after", new JSONArray()
        .put(36)
        .put("Michael")
      )
      .put("sort", new JSONArray()
        .put(new JSONObject()
          .put("age", "desc")
        )
        .put(new JSONObject()
          .put("name", "asc")
        )
      );

    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(collection).search((JSONObject) argument.capture(), any(Options.class), eq(listener));

    assertEquals(argument.getValue().toString(), expectedFilters.toString());
  }

  @Test
  public void fetchNextByScroll() throws JSONException {
    Options localOptions = new Options(options);
    localOptions
      .setScroll(scroll)
      .setScrollId(scrollId);

    collection = spy(collection);
    SearchResult searchResult = new SearchResult(collection, total, documents, null, localOptions, null);

    searchResult.fetchNext(listener);

    verify(collection).scroll(eq(scrollId), any(Options.class), any(JSONObject.class), eq(listener));
  }

  @Test
  public void fetchNextBySearch() throws JSONException {
    Options localOptions = new Options(options);
    localOptions
      .setFrom((long) 0)
      .setSize((long) 2);

    collection = spy(collection);
    SearchResult searchResult = new SearchResult(collection, total, documents, null, localOptions, new JSONObject());

    searchResult.fetchNext(listener);

    verify(collection).search(any(JSONObject.class), any(Options.class), eq(listener));
  }

  @Test(expected = RuntimeException.class)
  public void fetchNextOnError() throws JSONException {
    Options localOptions = new Options(options);

    collection = spy(collection);
    SearchResult searchResult = new SearchResult(collection, total, documents, null, localOptions, new JSONObject());

    searchResult.fetchNext(listener);
  }
}
