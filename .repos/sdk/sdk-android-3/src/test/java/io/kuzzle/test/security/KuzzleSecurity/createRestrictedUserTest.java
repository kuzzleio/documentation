package io.kuzzle.test.security.KuzzleSecurity;

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
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.security.Security;
import io.kuzzle.sdk.security.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class createRestrictedUserTest {
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
  public void testCreateRestrictedUserNoID() throws JSONException {
    kuzzleSecurity.createRestrictedUser(null, new JSONObject());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRestrictedWithProfileIds() throws JSONException {
    kuzzleSecurity.createRestrictedUser("some_id", new JSONObject().put("profileIds", new JSONArray()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRestrictedUserNoContent() throws JSONException {
    kuzzleSecurity.createRestrictedUser("foo", null);
  }

  @Test
  public void testCreateRestrictedUserNoListener() throws JSONException {
    kuzzleSecurity.createRestrictedUser("foo", new JSONObject(), new Options());
    ArgumentCaptor argument = ArgumentCaptor.forClass(Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).action, "createRestrictedUser");
  }

  @Test
  public void testCreateRestrictedUserReplaceIfExists() throws JSONException {
    Options options = new Options().setReplaceIfExist(true);

    kuzzleSecurity.createRestrictedUser("foo", new JSONObject(), options);
    ArgumentCaptor argument = ArgumentCaptor.forClass(Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).action, "createRestrictedUser");
  }

  @Test
  public void testCreateRestrictedUserValidResponse() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject(
          "{" +
            "\"result\": {" +
              "\"_id\": \"foobar\"," +
              "\"_source\": {" +
                "\"indexes\": {}" +
              "}," +
              "\"_meta\": {}" +
            "}" +
          "}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));
        return null;
      }
    }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzleSecurity.createRestrictedUser("foobar", new JSONObject(), new ResponseListener<User>() {
      @Override
      public void onSuccess(User response) {
        assertEquals(response.id, "foobar");
      }

      @Override
      public void onError(JSONObject error) {
        try {
          assertEquals(error.getString("error"), "stub");
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }
    });

    ArgumentCaptor argument = ArgumentCaptor.forClass(Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((Kuzzle.QueryArgs) argument.getValue()).action, "createRestrictedUser");
  }

  @Test(expected = RuntimeException.class)
  public void testCreateRestrictedUserBadResponse() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject();

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        return null;
      }
    }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzleSecurity.createRestrictedUser("foobar", new JSONObject(), listener);
  }
}
