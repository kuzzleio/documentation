package io.kuzzle.test.security;

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
import io.kuzzle.sdk.security.Profile;
import io.kuzzle.sdk.security.Security;
import io.kuzzle.sdk.security.User;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class KuzzleUserTest {
  private Kuzzle kuzzle;
  private User stubUser;

  @Before
  public void setUp() throws JSONException {
    kuzzle = mock(Kuzzle.class);
    kuzzle.security = new Security(kuzzle);
    stubUser = new User(kuzzle, "foo", null, null);
  }

  @Test
  public void testKuzzleUserConstructorNoContent() throws JSONException {
    User user = new User(kuzzle, "foo", null, null);
    assertEquals(user.id, "foo");
    assertEquals(user.getProfileIds().length, 0);
    assertThat(user.content, instanceOf(JSONObject.class));
  }

  @Test
  public void testKuzzleUserConstructorWithEmptyProfile() throws JSONException {
    JSONObject stubProfile = new JSONObject(
      "{" +
        "\"profileIds\": [\"bar\"]," +
        "\"someuseless\": \"field\"" +
      "}"
    );
    User user = new User(kuzzle, "foo", stubProfile, null);
    assertEquals(user.id, "foo");
    assertEquals(user.getProfileIds()[0], "bar");
    assertThat(user.content, instanceOf(JSONObject.class));
    assertEquals(user.content.getString("someuseless"), "field");
  }

  @Test
  public void testKuzzleUserConstructorProfileWithContent() throws JSONException {
    JSONObject stubProfile = new JSONObject("{\"profileIds\": [\"bar\"]}");
    User user = new User(kuzzle, "foo", stubProfile, null);
    assertEquals(user.id, "foo");
    assertThat(user.getProfileIds(), instanceOf(String[].class));
    assertEquals(user.getProfileIds()[0], "bar");
    assertThat(user.content, instanceOf(JSONObject.class));
  }

  @Test
  public void testKuzzleUserConstructorMeta() throws JSONException {
    JSONObject meta = new JSONObject()
      .put("createdAt", "0123456789")
      .put("author", "-1");
    User user = new User(kuzzle, "foo", null, meta);
    assertEquals(user.id, "foo");
    assertEquals(user.getMeta().length(), 2);
    assertEquals(user.getMeta().getString("createdAt"), "0123456789");
    assertEquals(user.getMeta().getString("author"), "-1");
    assertThat(user.meta, instanceOf(JSONObject.class));
  }

  @Test
  public void testSetProfileWithKuzzleProfile() throws JSONException {
    String[] ids = new String[1];
    ids[0] = "foo";
    stubUser.setProfiles(ids);
    assertEquals(stubUser.getProfileIds()[0], "foo");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetProfileNullID() throws JSONException {
    String[] ids = null;
    stubUser.setProfiles(ids);
    doThrow(IllegalArgumentException.class).when(stubUser).setProfiles(any(String[].class));
  }

  @Test
  public void testReplaceNoListener() throws JSONException {
    stubUser.replace();
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "replaceUser");
  }

  @Test
  public void testReplace() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject());
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    stubUser.replace(new ResponseListener<User>() {
      @Override
      public void onSuccess(User response) {
        assertEquals(response, stubUser);
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
    stubUser.replace(mock(Options.class));

    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "replaceUser");
  }

  @Test
  public void testCreateNoListener() throws JSONException {
    stubUser.create();
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "createUser");
  }

  @Test
  public void testCreate() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject());
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    stubUser.create(new ResponseListener<User>() {
      @Override
      public void onSuccess(User response) {
        assertEquals(response, stubUser);
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
    stubUser.create(mock(Options.class));

    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "createUser");
  }

  @Test
  public void testSaveRestrictedNoListener() throws JSONException {
    stubUser.saveRestricted();
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "createRestrictedUser");
  }

  @Test
  public void testSaveRestrictedNoListenerAndOptions() throws JSONException {
    Options options = new Options();
    stubUser.saveRestricted(options);
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "createRestrictedUser");
  }

  @Test
  public void testSaveRestricted() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject());
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    stubUser.saveRestricted(new ResponseListener<User>() {
      @Override
      public void onSuccess(User response) {
        assertEquals(response, stubUser);
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
    stubUser.saveRestricted(mock(Options.class));

    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "createRestrictedUser");
  }

  @Test
  public void testSerializeNoProfile() throws JSONException {
    stubUser.content.put("foo", "bar");
    JSONObject serialized = stubUser.serialize();
    assertEquals(serialized.getString("_id"), stubUser.id);
    assertEquals(serialized.getJSONObject("body").toString(), stubUser.content.toString());
    assertEquals(serialized.has("profile"), false);
  }

  @Test
  public void testSerializeWithProfile() throws JSONException {
    stubUser.content.put("foo", "bar");
    stubUser.setProfiles(new String[]{"profile"});
    JSONObject serialized = stubUser.serialize();
    assertEquals(serialized.getString("_id"), stubUser.id);
    assertEquals(serialized.getJSONObject("body").getString("foo"), "bar");
    assertEquals(serialized.getJSONObject("body").getJSONArray("profileIds").getString(0), stubUser.getProfileIds()[0]);
  }

  @Test
  public void testGetProfileIds() throws JSONException {
    JSONObject stubProfile = new JSONObject(
            "{\"profileIds\": [\"bar\"]}"
    );
    User user = new User(kuzzle, "foo", stubProfile, null);
    assertEquals(user.getProfileIds()[0], "bar");
  }

  @Test
  public void testAddProfile() throws JSONException {
    JSONObject stubProfile = new JSONObject(
            "{\"profileIds\": [\"bar\"]}"
    );
    User user = new User(kuzzle, "foo", stubProfile, null);
    user.addProfile("new profile");
    assertEquals(user.getProfileIds()[1], "new profile");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullProfile() throws JSONException {
    JSONObject stubProfile = new JSONObject(
            "{\"profileIds\": [\"bar\"]}"
    );
    User user = new User(kuzzle, "foo", stubProfile, null);
    user.addProfile(null);
    doThrow(IllegalArgumentException.class).when(user).addProfile(eq((String)null));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetProfilesException() throws JSONException {
    User user = new User(kuzzle, "foo", new JSONObject(), null);
    user.getProfiles(null);
  }

  @Test
  public void testGetProfilesEmpty() throws JSONException {
    User user = new User(kuzzle, "foo", new JSONObject(), null);

    user.getProfiles(new ResponseListener<Profile[]>() {
      @Override
      public void onSuccess(Profile[] response) {
        assertEquals(response.length, 0);
      }

      @Override
      public void onError(JSONObject error) {
        fail("onError callback should not have been invoked");
      }
    });
  }

  @Test
  public void testGetProfiles() throws JSONException {
    JSONArray profiles = new JSONArray().put("foo").put("bar").put("baz");
    User user = new User(kuzzle, "foo", new JSONObject().put("profileIds", profiles), null);

    Answer mockAnswer = new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation
          .getArguments()[3])
          .onSuccess(new JSONObject()
            .put("result", new JSONObject()
              .put("_id", "foobar")
              .put("_source", new JSONObject()
                .put("policies", new JSONArray())
              )
              .put("_meta", new JSONObject())
            )
          );
        return null;
      }
    };

    doAnswer(mockAnswer)
      .when(kuzzle)
      .query(
        any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class),
        any(JSONObject.class),
        any(Options.class),
        any(OnQueryDoneListener.class)
      );

    user.getProfiles(new ResponseListener<Profile[]>() {
      @Override
      public void onSuccess(Profile[] response) {
        assertEquals(response.length, 3);
      }

      @Override
      public void onError(JSONObject error) {
        fail("onError should not have been invoked");
      }
    });

    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(3)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
  }

  @Test
  public void testGetProfilesError() throws JSONException {
    JSONArray profiles = new JSONArray().put("foo").put("bar").put("baz");
    User user = new User(kuzzle, "foo", new JSONObject().put("profileIds", profiles), null);
    final boolean[] invoked = {false};

    Answer mockAnswer = new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    };

    doAnswer(mockAnswer)
      .when(kuzzle)
      .query(
        any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class),
        any(JSONObject.class),
        any(Options.class),
        any(OnQueryDoneListener.class)
      );

    user.getProfiles(new ResponseListener<Profile[]>() {
      @Override
      public void onSuccess(Profile[] response) {
        fail("onSuccess should not have been invoked");
      }

      @Override
      public void onError(JSONObject error) {
        if (invoked[0] == true) {
          fail("onError invoked more than once");
          return;
        }

        invoked[0] = true;
      }
    });

    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(3)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
  }
}
