package io.kuzzle.test.security.KuzzleSecurity;

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
import io.kuzzle.sdk.responses.SecurityDocumentList;
import io.kuzzle.sdk.security.Security;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class searchUsersTest {
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
  public void testSearchUsersNoFilters() throws JSONException {
    kuzzleSecurity.searchUsers(null, null, listener);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSearchUsersNoListener() throws JSONException {
    kuzzleSecurity.searchUsers(new JSONObject(), null);
  }

  @Test
  public void testSearchUsersValid() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject(
          "{" +
            "\"result\": {" +
              "\"hits\": [" +
                "{" +
                  "\"_id\": \"foobar\"," +
                  "\"_source\": {" +
                    "\"_id\": \"foobar\"," +
                    "\"indexes\": {}" +
                  "}," +
                  "\"_meta\": {}" +
                "}" +
              "]," +
              "\"total\": 1" +
            "}" +
          "}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzleSecurity.searchUsers(new JSONObject(), null, new ResponseListener<SecurityDocumentList>() {
      @Override
      public void onSuccess(SecurityDocumentList response) {
        assertEquals(response.getTotal(), 1);
        assertEquals(response.getDocuments().get(0).id, "foobar");
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

    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "searchUsers");
  }

  @Test(expected = RuntimeException.class)
  public void testSearchUsersBadResponse() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject response = new JSONObject();

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    kuzzleSecurity.searchUsers(new JSONObject(), null, listener);
  }
}
