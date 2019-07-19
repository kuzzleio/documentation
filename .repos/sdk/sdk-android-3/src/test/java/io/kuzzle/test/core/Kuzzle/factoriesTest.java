package io.kuzzle.test.core.Kuzzle;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class factoriesTest {
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
  public void testDataCollectionFactory() {
    assertEquals(kuzzle.collection("test").getCollection(), "test");
    assertEquals(kuzzle.collection("test2").getCollection(), "test2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDefaultIndex() {
    kuzzle.setSuperDefaultIndex(null);
    kuzzle.collection("foo");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalIndex() {
    kuzzle.setSuperDefaultIndex(null);
    kuzzle.collection("collection", null);
  }
}
