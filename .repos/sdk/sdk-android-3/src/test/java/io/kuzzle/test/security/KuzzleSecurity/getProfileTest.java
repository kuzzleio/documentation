package io.kuzzle.test.security.KuzzleSecurity;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.security.Security;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class getProfileTest {
  private Kuzzle kuzzle;
  private Security kuzzleSecurity;
  private ResponseListener listener;

  @Before
  public void setUp() {
    kuzzle = mock(Kuzzle.class);
    kuzzleSecurity = new Security(kuzzle);
    listener = mock(ResponseListener.class);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testGetProfileNoID() throws JSONException {
    kuzzleSecurity.fetchProfile(null, new Options(), listener);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetProfileNoListener() throws JSONException {
    kuzzleSecurity.fetchProfile("foo", null);
  }

  @Test(expected = RuntimeException.class)
  public void testGetProfileBadResponse() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject();

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzleSecurity.fetchProfile("foobar", listener);
  }

  @Test
  public void testGetProfileGoodFullResponse() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject("{\"result\":{\"_id\":\"foobar\",\"_source\":{\"policies\":[{\"roleId\":\"baz\",\"restrictedTo\":[{\"index\":\"qux\"}],\"allowInternalIndex\":true}]},\"_meta\":{}}}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));

        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzleSecurity.fetchProfile("foobar", listener);
  }

  @Test
  public void testGetProfileGoodMinimalResponse() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject("{\"result\":{\"_id\":\"foobar\",\"_source\":{\"policies\":[{\"roleId\":\"baz\"}]},\"_meta\":{}}}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));

        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzleSecurity.fetchProfile("foobar", listener);
  }

  @Test
  public void testGetProfileGoodWithRestrictedToResponse() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject("{\"result\":{\"_id\":\"foobar\",\"_source\":{\"policies\":[{\"roleId\":\"baz\"}],\"restrictedTo\":[{\"index\":\"qux\"}]},\"_meta\":{}}}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));

        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzleSecurity.fetchProfile("foobar", listener);
  }

  @Test
  public void testGetProfileGoodWithAllowInternalIndexResponse() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject("{\"result\":{\"_id\":\"foobar\",\"_source\":{\"policies\":[{\"roleId\":\"baz\"}],\"allowInternalIndex\":true},\"_meta\":{}}}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));

        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzleSecurity.fetchProfile("foobar", listener);
  }

}
