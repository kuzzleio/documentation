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
import io.kuzzle.sdk.security.AbstractSecurityDocument;
import io.kuzzle.sdk.security.Role;
import io.kuzzle.sdk.security.Security;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AbstractKuzzleSecurityDocumentTest {
  private Kuzzle kuzzle;
  private Role stubRole;
  private ResponseListener listener;

  @Before
  public void setUp() throws JSONException {
    kuzzle = mock(Kuzzle.class);
    kuzzle.security = new Security(kuzzle);
    listener = mock(ResponseListener.class);
    stubRole = new Role(kuzzle, "foo", new JSONObject("{\"foo\":\"bar\"}"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetContentNoContent() throws JSONException {
    stubRole.setContent(null);
  }

  @Test
  public void testSetContent() throws JSONException {
    JSONObject content = new JSONObject().put("foo", "bar");
    assertEquals(stubRole.setContent(content), stubRole);
    assertThat(stubRole.content, not(equalTo(content)));
    assertEquals(stubRole.content.toString(), content.toString());
  }

  @Test
  public void testSerialize() throws JSONException {
    JSONObject
      serialized,
      content = new JSONObject().put("bar", "qux");

    stubRole.setContent(content);

    serialized = stubRole.serialize();

    assertEquals(serialized.getString("_id"), stubRole.id);
    assertEquals(serialized.getJSONObject("body").toString(), content.toString());
  }

  @Test
  public void testDeleteNoListener() throws JSONException {
    stubRole.delete();
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "deleteRole");
  }

  @Test
  public void testDelete() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject content = new JSONObject("{\"result\": { \"_id\": \"foo\", \"_source\": {} }}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(content);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    stubRole.delete(new ResponseListener<String>() {
      @Override
      public void onSuccess(String response) {
        assertEquals(response, "foo");
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
    stubRole.delete();
    stubRole.delete(mock(Options.class));

    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    verify(kuzzle, times(2)).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class));

    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "deleteRole");
  }

  @Test(expected = RuntimeException.class)
  public void testDeleteInvalidJSON() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject content = new JSONObject();
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(content);
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    stubRole.delete(listener);
  }

  @Test
  public void testUpdate() throws JSONException {
    JSONObject content = new JSONObject();

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject content = new JSONObject("{\"result\": { \"_id\": \"foo\", \"_source\": {} }}");

        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(content);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    stubRole.update(content, new ResponseListener<AbstractSecurityDocument>() {
      @Override
      public void onSuccess(AbstractSecurityDocument response) {
        assertEquals(response.getId(), "foo");
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
    stubRole.update(content);
    stubRole.update(content, mock(Options.class));
    verify(kuzzle, times(2)).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class));
    verify(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
  }

  @Test(expected = RuntimeException.class)
  public void testUpdateInvalidJSON() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        JSONObject content = new JSONObject();
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(content);
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    stubRole.update(new JSONObject(), listener);
  }

  @Test
  public void testGetContent() throws JSONException {
    assertTrue(stubRole.getContent().getString("foo").equals("bar"));
  }
}
