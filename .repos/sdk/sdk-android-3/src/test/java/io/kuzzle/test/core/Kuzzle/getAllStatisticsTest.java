package io.kuzzle.test.core.Kuzzle;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;
import java.util.Iterator;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import io.kuzzle.test.testUtils.QueryArgsHelper;
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

public class getAllStatisticsTest {
  private KuzzleExtend kuzzle;
  private ResponseListener listener;

  @Before
  public void setUp() throws URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    options.setDefaultIndex("testIndex");

    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setSocket(mock(WebSocketClient.class));
    kuzzle.setState(States.CONNECTED);

    listener = new ResponseListener<Object>() {
      @Override
      public void onSuccess(Object object) {

      }

      @Override
      public void onError(JSONObject error) {

      }
    };
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetAllStatisticsNoListener() {
    kuzzle.getAllStatistics(null);
  }

  @Test
  public void testGetAllStatisticsNoOptions() {
    kuzzle = spy(kuzzle);
    kuzzle.getAllStatistics(listener);
    verify(kuzzle).getAllStatistics(any(Options.class), any(ResponseListener.class));
  }

  @Test(expected = RuntimeException.class)
  public void testGetAllStatisticsException() throws JSONException {
    listener = spy(listener);
    kuzzle = spy(kuzzle);
    doThrow(JSONException.class).when(listener).onSuccess(any(JSONObject.class));
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("hits", mock(JSONObject.class))));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.getAllStatistics(listener);
  }

  @Test(expected = RuntimeException.class)
  public void testGetAllStatisticsQueryException() throws JSONException {
    kuzzle = spy(kuzzle);
    doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.getAllStatistics(listener);
  }

  @Test
  public void testGetAllStatsSuccess() throws JSONException {
    kuzzle = spy(kuzzle);
    final JSONObject response = new JSONObject("{\n" +
      "    \"total\": 25,\n" +
      "    \"hits\": [\n" +
      "      {\n" +
      "        \"completedRequests\": {\n" +
      "          \"websocket\": 148,\n" +
      "          \"rest\": 24,\n" +
      "          \"mq\": 78\n" +
      "        },\n" +
      "        \"failedRequests\": {\n" +
      "          \"websocket\": 3\n" +
      "        },\n" +
      "        \"ongoingRequests\": {\n" +
      "          \"mq\": 8,\n" +
      "          \"rest\": 2\n" +
      "        },\n" +
      "        \"connections\": {\n" +
      "          \"websocket\": 13\n" +
      "        },\n" +
      "        \"timestamp\": \"2016-01-13T13:46:19.917Z\"\n" +
      "      }\n" +
      "    ]\n" +
      "  }\n");
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", response));
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.getAllStatistics(new ResponseListener<JSONObject[]>() {
      @Override
      public void onSuccess(JSONObject[] result) {
        try {
          for (int i = 0; i < result.length; i++) {
            for (Iterator ite = result[i].keys(); ite.hasNext(); ) {
              String key = (String) ite.next();
              assertEquals(result[i].get(key), response.getJSONArray("hits").getJSONObject(i).get(key));
            }
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onError(JSONObject error) {

      }
    });
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "server");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "getAllStats");
  }

  @Test
  public void testGetAllStatsError() throws JSONException {
    kuzzle = spy(kuzzle);

    final JSONObject responseError = new JSONObject();
    responseError.put("error", "rorre");
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(responseError);
        return null;
      }
    }).when(kuzzle).query(eq(QueryArgsHelper.makeQueryArgs("server", "getAllStats")), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.getAllStatistics(new ResponseListener<JSONObject[]>() {
      @Override
      public void onSuccess(JSONObject[] object) {
      }

      @Override
      public void onError(JSONObject error) {
        try {
          System.out.println(error.toString());
          assertEquals(error.get("error"), "rorre");
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }
    });
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "server");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "getAllStats");
  }
}
