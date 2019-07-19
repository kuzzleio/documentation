package io.kuzzle.test.security;

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

import static org.junit.Assert.assertFalse;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class KuzzleProfileTest {
  private Kuzzle kuzzle;
  private Profile stubProfile;

  @Before
  public void setUp() throws JSONException {
    kuzzle = mock(Kuzzle.class);
    kuzzle.security = new Security(kuzzle);
    stubProfile = new Profile(kuzzle, "foo", null, null);
  }

  @Test
  public void testConstructorNoContent() throws JSONException {
    Profile profile = new Profile(kuzzle, "foo", null, null);
    assertEquals(profile.id, "foo");
    assertEquals(profile.getPolicies().length, 0);
    assertThat(profile.content, instanceOf(JSONObject.class));
    assertEquals(profile.content.length(), 0);
  }

  @Test
  public void testConstructorContentWithIDs() throws JSONException {
    JSONObject content = new JSONObject(
      "{" +
        "\"policies\": [{\"roleId\": \"foo\"}, {\"roleId\": \"bar\"}, {\"roleId\": \"baz\"}]" +
      "}"
    );
    Profile profile = new Profile(kuzzle, "foo", content, null);
    assertEquals(profile.id, "foo");
    assertEquals(profile.getPolicies().length, 3);
    assertEquals(profile.getPolicies()[2].getString("roleId"), "baz");
    assertThat(profile.content, instanceOf(JSONObject.class));
    assertEquals(profile.content.length(), 0);
  }

  @Test
  public void testConstructorContentWithRoles() throws JSONException {
    JSONObject content = new JSONObject(
      "{" +
        "\"policies\": [" +
          "{\"roleId\": \"foo\"}, " +
          "{\"roleId\": \"bar\"}, " +
          "{\"roleId\": \"baz\"}" +
        "]" +
      "}"
    );
    Profile profile = new Profile(kuzzle, "foo", content, null);
    assertEquals(profile.id, "foo");
    assertEquals(profile.getPolicies().length, 3);
    assertEquals(profile.getPolicies()[2].getString("roleId"), "baz");
    assertThat(profile.content, instanceOf(JSONObject.class));
    assertEquals(profile.content.length(), 0);
  }

  @Test
  public void testConstructorMeta() throws JSONException {
    JSONObject meta = new JSONObject()
      .put("createdAt", "0123456789")
      .put("author", "-1");
    Profile profile = new Profile(kuzzle, "foo", null, meta);
    assertEquals(profile.id, "foo");
    assertEquals(profile.getMeta().length(), 2);
    assertEquals(profile.getMeta().getString("createdAt"), "0123456789");
    assertEquals(profile.getMeta().getString("author"), "-1");
    assertThat(profile.meta, instanceOf(JSONObject.class));
  }

  @Test
  public void testAddPolicyObject() throws JSONException {
    stubProfile.addPolicy(new JSONObject().put("roleId", "some role"));
    assertEquals(stubProfile.getPolicies().length, 1);
    assertEquals(stubProfile.getPolicies()[0].getString("roleId"), "some role");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullPolicyObject() throws JSONException {
    stubProfile.addPolicy(new JSONObject().put("roleId", null));
    doThrow(IllegalArgumentException.class).when(stubProfile).addPolicy(any(JSONObject.class));
  }

  @Test
  public void testAddPolicyID() throws JSONException {
    stubProfile.addPolicy("another role");
    assertEquals(stubProfile.getPolicies().length, 1);
    assertEquals(stubProfile.getPolicies()[0].getString("roleId"), "another role");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveNoRole() throws JSONException {
    stubProfile.save();
  }

  @Test
  public void testSaveNoListener() throws JSONException {
    stubProfile.addPolicy("baz");
    stubProfile.save();
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "createOrReplaceProfile");
  }

  @Test
  public void testSave() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject());
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject().put("error", "stub"));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

    stubProfile.addPolicy("baz");
    stubProfile.save(new ResponseListener<Profile>() {
      @Override
      public void onSuccess(Profile response) {
        assertEquals(response, stubProfile);
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
    stubProfile.save(mock(Options.class));

    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "security");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "createOrReplaceProfile");
  }

  @Test
  public void testSetPoliciesObjectList() throws JSONException {
    JSONObject[] policies = new JSONObject[]{
      new JSONObject().put("roleId", "bar"),
      new JSONObject().put("roleId", "baz"),
      new JSONObject().put("roleId", "qux")
    };

    stubProfile.addPolicy("foo");
    stubProfile.setPolicies(policies);
    assertEquals(stubProfile.getPolicies().length, 3);
    assertEquals(stubProfile.getPolicies()[0].getString("roleId"), "bar");
    assertEquals(stubProfile.getPolicies()[1].getString("roleId"), "baz");
    assertEquals(stubProfile.getPolicies()[2].getString("roleId"), "qux");
  }

  @Test
  public void testSetRolesIDs() throws JSONException {
    String[] policies = {"bar", "baz", "qux"};

    stubProfile.addPolicy("foo");
    assertEquals(stubProfile.getPolicies().length, 1);

    stubProfile.setPolicies(policies);
    assertEquals(stubProfile.getPolicies().length, 3);
    assertEquals(stubProfile.getPolicies()[0].getString("roleId"), "bar");
    assertEquals(stubProfile.getPolicies()[1].getString("roleId"), "baz");
    assertEquals(stubProfile.getPolicies()[2].getString("roleId"), "qux");

    stubProfile.addPolicy("foo");
    assertEquals(stubProfile.getPolicies().length, 4);
    assertEquals(stubProfile.getPolicies()[3].getString("roleId"), "foo");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBadPolicies() throws JSONException {
    stubProfile.setPolicies(new JSONObject[]{new JSONObject().put("bad", "policy")});

    doThrow(IllegalArgumentException.class).when(stubProfile).setPolicies(any(JSONObject[].class));
  }

  @Test
  public void testSerializeWithNoPolicies() throws JSONException {
    assertFalse(stubProfile.serialize().getJSONObject("body").has("policies"));
  }
}
