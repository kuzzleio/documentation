package io.kuzzle.test.core.Kuzzle;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Event;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class setJwtTokenTest {
  private KuzzleExtend kuzzle;
  private WebSocketClient s;

  @Before
  public void setUp() throws URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    options.setDefaultIndex("testIndex");

    s = mock(WebSocketClient.class);
    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setSocket(s);

    kuzzle = spy(kuzzle);
    doNothing().when(kuzzle).renewSubscriptions();
    doNothing().when(kuzzle).emitEvent(any(Event.class), any(JSONObject.class));
  }

  @Test
  public void shouldHandleStringToken() throws JSONException {
    kuzzle.setJwtToken("foobar");
    assertEquals("foobar", kuzzle.getJwtToken());

    ArgumentCaptor loginResult = ArgumentCaptor.forClass(JSONObject.class);
    verify(kuzzle).emitEvent(eq(Event.loginAttempt), (JSONObject)loginResult.capture());
    assertTrue(((JSONObject) loginResult.getValue()).getBoolean("success"));

    verify(kuzzle).renewSubscriptions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowWithNullKuzzleResponse() throws JSONException {
    kuzzle.setJwtToken((JSONObject) null);
  }

  @Test
  public void shouldHandleValidKuzzleResponse() throws JSONException {
    JSONObject validResponse = new JSONObject()
      .put("result", new JSONObject()
        .put("jwt", "foobar")
      );

    kuzzle.setJwtToken(validResponse);
    assertEquals("foobar", kuzzle.getJwtToken());

    ArgumentCaptor loginResult = ArgumentCaptor.forClass(JSONObject.class);
    verify(kuzzle).emitEvent(eq(Event.loginAttempt), (JSONObject)loginResult.capture());
    assertTrue(((JSONObject) loginResult.getValue()).getBoolean("success"));

    verify(kuzzle).renewSubscriptions();
  }

  @Test
  public void shouldHandleInvalidResponse() throws JSONException {
    kuzzle.setJwtToken(new JSONObject());
    assertNull(kuzzle.getJwtToken());

    ArgumentCaptor loginResult = ArgumentCaptor.forClass(JSONObject.class);
    verify(kuzzle).emitEvent(eq(Event.loginAttempt), (JSONObject)loginResult.capture());
    assertFalse(((JSONObject) loginResult.getValue()).getBoolean("success"));

    verify(kuzzle, never()).renewSubscriptions();
  }
}
