package io.kuzzle.test.security.KuzzleSecurity;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.enums.Policies;
import io.kuzzle.sdk.security.Security;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class isActionAllowedTest {
  private Security kuzzleSecurity;
  private JSONObject[] policies;

  private JSONObject addProperties(final String ctrl, final String action, final String idx, final String collection, final String value) throws JSONException {
    return new JSONObject()
        .put("controller", ctrl)
        .put("action", action)
        .put("index", idx)
        .put("collection", collection)
        .put("value", value);
  }

  @Before
  public void setUp() throws JSONException {
    Kuzzle kuzzle = mock(Kuzzle.class);
    kuzzleSecurity = new Security(kuzzle);
    policies = new JSONObject[]{
      addProperties("document", "get", "*", "*", Policies.allowed.toString()),
      addProperties("document", "count", "*", "*", Policies.allowed.toString()),
      addProperties("document", "search", "*", "*", Policies.allowed.toString()),
      addProperties("document", "*", "index1", "collection1", Policies.allowed.toString()),
      addProperties("document", "*", "index1", "collection2", Policies.allowed.toString()),
      addProperties("document", "update", "*", "*", Policies.allowed.toString()),
      addProperties("document", "create", "*", "*", Policies.allowed.toString()),
      addProperties("document", "createOrReplace", "*", "*", Policies.allowed.toString()),
      addProperties("document", "delete", "*", "*", Policies.conditional.toString()),
      addProperties("realtime", "publish", "index2", "*", Policies.allowed.toString()),
      addProperties("security", "searchUsers", "*", "*", Policies.allowed.toString()),
      addProperties("security", "updateUser", "*", "*", Policies.conditional.toString())
    };
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullPolicies() {
    kuzzleSecurity.isActionAllowed(null, "ctrl", "action");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullController() {
    kuzzleSecurity.isActionAllowed(policies, null, "action");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAction() {
    kuzzleSecurity.isActionAllowed(policies, "ctrl", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyController() {
    kuzzleSecurity.isActionAllowed(policies, "", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyAction() {
    kuzzleSecurity.isActionAllowed(policies, "ctrl", "");
  }

  @Test
  public void testControllerActionAllowed() {
    assertEquals(Policies.allowed, kuzzleSecurity.isActionAllowed(policies, "document", "get"));
  }

  @Test
  public void testControllerActionIndexAllowed() {
    assertEquals(Policies.allowed, kuzzleSecurity.isActionAllowed(policies, "document", "count", "myIndex"));
  }

  @Test
  public void testControllerActionIndexCollectionAllowed() {
    assertEquals(Policies.allowed, kuzzleSecurity.isActionAllowed(policies, "document", "search", "index1", "collection1"));
  }

  @Test
  public void testControllerActionIndexCollection2Allowed() {
    assertEquals(Policies.allowed, kuzzleSecurity.isActionAllowed(policies, "document", "search", "index1", "collection2"));
  }

  @Test
  public void testControllerActionDenied() {
    assertEquals(Policies.denied, kuzzleSecurity.isActionAllowed(policies, "document", "replace"));
  }

  @Test
  public void testControllerActionIndexDenied() {
    assertEquals(Policies.denied, kuzzleSecurity.isActionAllowed(policies, "index", "list", "index2"));
  }

  @Test
  public void testControllerActionConditional() {
    assertEquals(Policies.conditional, kuzzleSecurity.isActionAllowed(policies, "security", "updateUser"));
  }

  @Test
  public void testControllerActionIndexCollectionConditional() {
    assertEquals(Policies.conditional, kuzzleSecurity.isActionAllowed(policies, "document", "delete", "index2", "collection1"));
  }

  @Test(expected = RuntimeException.class)
  public void testJsonException() throws JSONException {
    JSONObject spy = spy(policies[0]);
    doThrow(JSONException.class).when(spy.getString(any(String.class)));
    kuzzleSecurity.isActionAllowed(policies, "document", "delete", "index1", "collection1");
  }

}
