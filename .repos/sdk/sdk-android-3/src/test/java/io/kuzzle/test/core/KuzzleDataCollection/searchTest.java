package io.kuzzle.test.core.KuzzleDataCollection;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.responses.SearchResult;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class searchTest {
  private Kuzzle kuzzle;
  private Collection collection;
  private ResponseListener listener;

  @Before
  public void setUp() throws URISyntaxException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setSocket(mock(WebSocketClient.class));
    extended.setState(States.CONNECTED);
    kuzzle = spy(extended);
    when(kuzzle.getHeaders()).thenReturn(new JSONObject());

    collection = new Collection(kuzzle, "test", "index");
    listener = mock(ResponseListener.class);
  }

  @Test
  public void checkSignaturesVariants() {
    collection = spy(collection);
    collection.search(new JSONObject(), listener);
    verify(collection).search(any(JSONObject.class), any(Options.class), eq(listener));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSearchIllegalListener() {
    collection.search(null, null, null);
  }

  @Test(expected = RuntimeException.class)
  public void testSearchQueryException() throws JSONException {
    doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    collection.search(null, listener);
  }

  @Test(expected = RuntimeException.class)
  public void testSearchException() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("count", 42)));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    doThrow(JSONException.class).when(listener).onSuccess(any(Integer.class));
    collection.search(null, listener);
  }

  @Test
  public void testSearch() throws JSONException {
    JSONObject filters = new JSONObject();
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject("{\"result\": {\n" +
          "    \"_shards\": {\n" +
          "      \"failed\": 0,\n" +
          "      \"successful\": 5,\n" +
          "      \"total\": 5\n" +
          "    },\n" +
          "    \"hits\": [\n" +
          "      {\n" +
          "        \"_id\": \"AVJAwyDMZAGQHg9Dhfw2\",\n" +
          "        \"_index\": \"cabble\",\n" +
          "        \"_score\": 1,\n" +
          "        \"_source\": {\n" +
          "          \"pos\": {\n" +
          "            \"lat\": 43.6073821,\n" +
          "            \"lon\": 3.9130721\n" +
          "          },\n" +
          "          \"sibling\": \"none\",\n" +
          "          \"status\": \"idle\",\n" +
          "          \"type\": \"customer\"\n" +
          "        },\n" +
          "        \"_meta\": {\n" +
          "          \"author\": \"foo\"\n" +
          "        },\n" +
          "        \"_type\": \"users\"\n" +
          "      },\n" +
          "      {\n" +
          "        \"_id\": \"AVJAwyOvZAGQHg9Dhfw3\",\n" +
          "        \"_index\": \"cabble\",\n" +
          "        \"_score\": 1,\n" +
          "        \"_source\": {\n" +
          "          \"pos\": {\n" +
          "            \"lat\": 43.6073683,\n" +
          "            \"lon\": 3.8999983\n" +
          "          },\n" +
          "          \"sibling\": \"none\",\n" +
          "          \"status\": \"idle\",\n" +
          "          \"type\": \"cab\"\n" +
          "        },\n" +
          "        \"_meta\": {\n" +
          "          \"author\": \"foo\"\n" +
          "        },\n" +
          "        \"_type\": \"users\"\n" +
          "      }\n" +
          "    ],\n" +
          "    \"max_score\": 1,\n" +
          "    \"timed_out\": false,\n" +
          "    \"took\": 307,\n" +
          "    \"total\": 2\n" +
          "  }" +
          "}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    collection.search(filters, new ResponseListener<SearchResult>() {
      @Override
      public void onSuccess(SearchResult result) {
        assertEquals(result.getTotal(), 2);
        try {
          assertEquals(result.getDocuments().get(1).getContent("sibling"), "none");
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
      }
    });
    collection.search(filters, mock(ResponseListener.class));
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(2)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "document");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "search");
  }

  @Test
  public void testSearchWithAggregations() throws JSONException {
    JSONObject filters = new JSONObject();
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject("{\"result\": {\n" +
                "    \"_shards\": {\n" +
                "      \"failed\": 0,\n" +
                "      \"successful\": 5,\n" +
                "      \"total\": 5\n" +
                "    },\n" +
                "    \"hits\": [\n" +
                "      {\n" +
                "        \"_id\": \"AVJAwyDMZAGQHg9Dhfw2\",\n" +
                "        \"_index\": \"cabble\",\n" +
                "        \"_score\": 1,\n" +
                "        \"_source\": {\n" +
                "          \"pos\": {\n" +
                "            \"lat\": 43.6073821,\n" +
                "            \"lon\": 3.9130721\n" +
                "          },\n" +
                "          \"sibling\": \"none\",\n" +
                "          \"status\": \"idle\",\n" +
                "          \"type\": \"customer\"\n" +
                "        },\n" +
                "        \"_meta\": {\n" +
                "          \"author\": \"foo\"\n" +
                "        },\n" +
                "        \"_type\": \"users\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"_id\": \"AVJAwyOvZAGQHg9Dhfw3\",\n" +
                "        \"_index\": \"cabble\",\n" +
                "        \"_score\": 1,\n" +
                "        \"_source\": {\n" +
                "          \"pos\": {\n" +
                "            \"lat\": 43.6073683,\n" +
                "            \"lon\": 3.8999983\n" +
                "          },\n" +
                "          \"sibling\": \"none\",\n" +
                "          \"status\": \"idle\",\n" +
                "          \"type\": \"cab\"\n" +
                "        },\n" +
                "        \"_meta\": {\n" +
                "          \"author\": \"foo\"\n" +
                "        },\n" +
                "        \"_type\": \"users\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"aggregations\": {\n" +
                "      \"aggs_name\": {\n" +
                "        \"buckets\": [\n" +
                "          {\n" +
                "            \"doc_count\": 5,\n" +
                "            \"key\": \"i\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"doc_count\": 2,\n" +
                "            \"key\": \"hate\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"doc_count\": 1,\n" +
                "            \"key\": \"admir\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"doc_count_error_upper_bound\": 0,\n" +
                "        \"sum_other_doc_count\": 1\n" +
                "      }\n" +
                "    },\n" +
                "    \"max_score\": 1,\n" +
                "    \"timed_out\": false,\n" +
                "    \"took\": 307,\n" +
                "    \"total\": 2\n" +
                "  }" +
                "}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    collection.search(filters, new ResponseListener<SearchResult>() {
      @Override
      public void onSuccess(SearchResult result) {
        assertEquals(result.getTotal(), 2);
        try {
          assertEquals(result.getAggregations().getJSONObject("aggs_name").getJSONArray("buckets").getJSONObject(0).getString("key"), "i");
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
        try {
          assertEquals(result.getDocuments().get(1).getContent("sibling"), "none");
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
      }
    });
    collection.search(filters, mock(ResponseListener.class));
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(2)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "document");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "search");
  }
}
