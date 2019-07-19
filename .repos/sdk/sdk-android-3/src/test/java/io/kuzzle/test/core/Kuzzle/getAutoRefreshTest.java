package io.kuzzle.test.core.Kuzzle;

import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import io.kuzzle.sdk.core.Kuzzle;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class getAutoRefreshTest {
  private KuzzleExtend kuzzle;
  private ResponseListener listener;


  @Before
  public void setup() throws URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    options.setDefaultIndex("testIndex");

    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setSocket(mock(WebSocketClient.class));

    listener = mock(ResponseListener.class);
  }

  @Test
  public void testSignatureVariants() {
    Options options = mock(Options.class);

    kuzzle = spy(kuzzle);

    kuzzle.getAutoRefresh(listener);
    kuzzle.getAutoRefresh(options, listener);
    kuzzle.getAutoRefresh("foo", listener);

    verify(kuzzle, times(3)).getAutoRefresh(any(String.class), any(Options.class), any(ResponseListener.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetAutoRefreshWithIllegalListener() {
    kuzzle.getAutoRefresh("index", null, null);
  }

  @Test(expected = RuntimeException.class)
  public void testGetAutoRefreshException() throws JSONException {
    kuzzle = spy(kuzzle);
    doThrow(JSONException.class).when(listener).onSuccess(any(JSONArray.class));
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", true));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.getAutoRefresh(listener);
  }

  @Test(expected = RuntimeException.class)
  public void testGetAutoRefreshQueryException() throws JSONException {
    kuzzle = spy(kuzzle);
    doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.getAutoRefresh(listener);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetAutoRefreshIllegalDefaultIndex() {
    kuzzle = spy(kuzzle);
    kuzzle.setSuperDefaultIndex(null);
    kuzzle.getAutoRefresh(null, mock(Options.class), listener);
  }

  @Test
  public void testGetAutoRefresh() throws URISyntaxException, JSONException {
    kuzzle = spy(kuzzle);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", true));
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzle.getAutoRefresh(listener);
    kuzzle.getAutoRefresh(mock(Options.class), listener);

    ArgumentCaptor argument = ArgumentCaptor.forClass(Kuzzle.QueryArgs.class);
    verify(kuzzle, times(2)).query((Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).controller, "index");
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).action, "getAutoRefresh");
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).index, kuzzle.getDefaultIndex());
  }

}
