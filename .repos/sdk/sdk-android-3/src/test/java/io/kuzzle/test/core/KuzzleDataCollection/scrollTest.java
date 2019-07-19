package io.kuzzle.test.core.KuzzleDataCollection;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.listeners.ResponseListener;
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

public class scrollTest {
  private Kuzzle kuzzle;
  private Collection collection;
  private ResponseListener listener;
  private String scrollId;
  private String scroll;
  private Options options;

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
    scrollId = "someScrollId";
    options = mock(Options.class);
  }

  @Test
  public void checkSignaturesVariants() {
    collection = spy(collection);
    collection.scroll(scrollId, listener);
    verify(collection).scroll(eq(scrollId), any(Options.class), any(JSONObject.class), eq(listener));

    collection.scroll(scrollId, options, listener);
    verify(collection).scroll(eq(scrollId), eq(options), any(JSONObject.class), eq(listener));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testScrollIllegalListener() {
    collection.scroll(scrollId, null);
  }

  @Test(expected = RuntimeException.class)
  public void testScrollQueryException() throws JSONException {
    doThrow(JSONException.class).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    collection.scroll(scrollId, listener);
  }

  @Test(expected = RuntimeException.class)
  public void testScrollException() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("count", 42)));
        return null;
      }
    }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    doThrow(JSONException.class).when(listener).onSuccess(any(Integer.class));
    collection.scroll(scrollId, listener);
  }

  @Test
  public void testScroll() throws JSONException {
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
    }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    collection.scroll(scrollId, new ResponseListener<SearchResult>() {
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
    collection.scroll(scrollId, mock(ResponseListener.class));
    ArgumentCaptor argument = ArgumentCaptor.forClass(Kuzzle.QueryArgs.class);
    verify(kuzzle, times(2)).query((Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).controller, "document");
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).action, "scroll");
  }
}
