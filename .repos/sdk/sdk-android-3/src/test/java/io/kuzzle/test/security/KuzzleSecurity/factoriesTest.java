package io.kuzzle.test.security.KuzzleSecurity;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.security.Profile;
import io.kuzzle.sdk.security.Role;
import io.kuzzle.sdk.security.Security;
import io.kuzzle.sdk.security.User;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class factoriesTest {
  private Kuzzle kuzzle;
  private Security kuzzleSecurity;
  private ResponseListener listener;

  @Before
  public void setUp() {
    kuzzle = mock(Kuzzle.class);
    kuzzleSecurity = new Security(kuzzle);
    listener = mock(ResponseListener.class);
  }

  @Test
  public void testRoleFactory() throws JSONException {
    assertThat(kuzzleSecurity.role("id"), instanceOf(Role.class));
    assertThat(kuzzleSecurity.role("id", new JSONObject()), instanceOf(Role.class));
  }

  @Test
  public void testProfileFactory() throws JSONException {
    assertThat(kuzzleSecurity.profile("id"), instanceOf(Profile.class));
    assertThat(kuzzleSecurity.profile("id", new JSONObject()), instanceOf(Profile.class));
  }

  @Test
  public void testUserFactory() throws JSONException {
    assertThat(kuzzleSecurity.user("id"), instanceOf(User.class));
    assertThat(kuzzleSecurity.user("id", new JSONObject()), instanceOf(User.class));
  }
}
