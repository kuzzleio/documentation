package io.kuzzle.test.core.Kuzzle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class getStatisticsTest {
  private KuzzleExtend kuzzle;
  private ResponseListener listener;

  @Before
  public void setUp() throws URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    options.setDefaultIndex("testIndex");

    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setSocket(mock(WebSocketClient.class));

    listener = new ResponseListener<Object>() {
      @Override
      public void onSuccess(Object object) {

      }

      @Override
      public void onError(JSONObject error) {

      }
    };
  }

  @Test
  public void checkAllSignaturesVariants() {
    kuzzle = spy(kuzzle);
    listener = spy(listener);
    kuzzle.getStatistics(listener);
    kuzzle.getStatistics(System.currentTimeMillis(), listener);
    verify(kuzzle).getStatistics(any(Options.class), any(ResponseListener.class));
    verify(kuzzle).getStatistics(any(long.class), any(Options.class), any(ResponseListener.class));
  }

  @Test(expected = RuntimeException.class)
  public void testGetStatisticsException() throws JSONException {
    listener = spy(listener);
    kuzzle = spy(kuzzle);
    doThrow(JSONException.class).when(listener).onSuccess(any(JSONArray.class));
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("hits", mock(JSONArray.class))));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.getStatistics(System.currentTimeMillis(), listener);
  }

  @Test(expected = RuntimeException.class)
  public void testGetStatisticsQueryException() throws JSONException {
    kuzzle = spy(kuzzle);
    doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.getStatistics(System.currentTimeMillis(), listener);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetStatsIllegalListener() {
    kuzzle.getStatistics(System.currentTimeMillis(), null);
  }

  @Test
  public void testGetStatistics() throws JSONException {
    kuzzle = spy(kuzzle);
    final JSONObject response = new JSONObject("{ result: {\n" +
      "    total: 25,\n" +
      "    hits: [\n" +
      "      {\n" +
      "        completedRequests: {\n" +
      "          websocket: 148,\n" +
      "          rest: 24,\n" +
      "          mq: 78\n" +
      "        },\n" +
      "        failedRequests: {\n" +
      "          websocket: 3\n" +
      "        },\n" +
      "        ongoingRequests: {\n" +
      "          mq: 8,\n" +
      "          rest: 2\n" +
      "        },\n" +
      "        connections: {\n" +
      "          websocket: 13\n" +
      "        },\n" +
      "        \"timestamp\": \"2016-01-13T13:46:19.917Z\"\n" +
      "      }\n"  +
      "    ]\n" +
      "  }" +
      "}");

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.getStatistics(System.currentTimeMillis(), mock(ResponseListener.class));
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "server");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "getStats");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetStatisticsWithoutListener() {
    kuzzle.getStatistics(null);
  }
}
