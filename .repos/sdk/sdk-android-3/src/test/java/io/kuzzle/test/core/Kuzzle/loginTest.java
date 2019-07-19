package io.kuzzle.test.core.Kuzzle;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Event;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.EventListener;
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

public class loginTest {
  private KuzzleExtend kuzzle;
  private WebSocketClient s;
  private ResponseListener listener;

  @Before
  public void setUp() throws URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    options.setDefaultIndex("testIndex");

    s = mock(WebSocketClient.class);
    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setSocket(s);

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
    JSONObject stubCredentials = new JSONObject();
    kuzzle = spy(kuzzle);
    listener = spy(listener);
    kuzzle.login("foo", stubCredentials);
    kuzzle.login("foo", stubCredentials, 42);
    kuzzle.login("foo", stubCredentials, listener);
    kuzzle.login("foo");
    kuzzle.login("foo", 42);
    kuzzle.login("foo", 42, listener);
    kuzzle.login("foo", listener);
    verify(kuzzle, times(7)).login(any(String.class), any(JSONObject.class), any(int.class), any(ResponseListener.class));
  }

  @Test
  public void testLogin() throws JSONException {
    kuzzle = spy(kuzzle);

    ResponseListener listenerSpy = spy(listener);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("_type", "type")));
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.login("local", new JSONObject().put("username", "username").put("password", "password"));
    kuzzle.login("local", new JSONObject().put("username", "username").put("password", "password"), 42);
    kuzzle.login("local", new JSONObject().put("username", "username").put("password", "password"), 42, listenerSpy);
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(3)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "auth");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "login");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoStrategy() {
    kuzzle.login(null, new JSONObject());
  }

  @Test
  public void testLoginAttemptEvent() throws JSONException {
    kuzzle = spy(kuzzle);
    kuzzle.addListener(Event.loginAttempt, mock(EventListener.class));
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("jwt", "token")));
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.login("local", new JSONObject());
    verify(kuzzle, times(2)).emitEvent(any(Event.class), any(Object.class));
  }

  @Test(expected = RuntimeException.class)
  public void testLoginQueryException() throws JSONException {
    kuzzle = spy(kuzzle);
    doThrow(JSONException.class).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.login("local", new JSONObject());
  }

  @Test(expected = RuntimeException.class)
  public void testLoginOnSuccessException() throws JSONException {
    kuzzle = spy(kuzzle);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("jwt", "token")));
        return null;
      }
    }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    doThrow(JSONException.class).when(kuzzle).setJwtToken(any(String.class));
    kuzzle.login("local", new JSONObject());
  }

  @Test(expected = RuntimeException.class)
  public void testLoginOnErrorException() throws JSONException {
    kuzzle = spy(kuzzle);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    doThrow(JSONException.class).when(kuzzle).emitEvent(any(Event.class), any(Object.class));
    kuzzle.login("local", new JSONObject());
  }

  @Test(expected = RuntimeException.class)
  public void testSetJwtTokenException() {
    kuzzle = spy(kuzzle);
    doThrow(JSONException.class).when(kuzzle).emitEvent(any(Event.class), any(Object.class));
    kuzzle.setJwtToken("foo");
  }
}
