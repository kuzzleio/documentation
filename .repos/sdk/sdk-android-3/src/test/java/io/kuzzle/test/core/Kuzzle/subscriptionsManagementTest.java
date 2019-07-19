package io.kuzzle.test.core.Kuzzle;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.core.Room;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class subscriptionsManagementTest {
  private KuzzleExtend kuzzle;
  private ResponseListener listener;

  @Before
  public void setUp() throws URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    options.setDefaultIndex("testIndex");

    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setSocket(mock(WebSocketClient.class));

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
  public void testDeleteSubscription() throws JSONException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Map<String, ConcurrentHashMap<String, Room>> subscriptions = kuzzle.getSubscriptions();

    subscriptions.put("foo", new ConcurrentHashMap<String, Room>());
    subscriptions.get("foo").put("bar", mock(Room.class));
    subscriptions.get("foo").put("baz", mock(Room.class));
    subscriptions.get("foo").put("qux", mock(Room.class));

    kuzzle.deleteSubscription("foobar", "whatever");

    // there is always a "pending" room ID used to store pending subscriptions
    assertEquals(subscriptions.keySet().size(), 2);
    assertEquals(subscriptions.get("pending").size(), 0);
    assertEquals(subscriptions.get("foo").size(), 3);

    kuzzle.deleteSubscription("foo", "baz");

    assertEquals(subscriptions.keySet().size(), 2);
    assertEquals(subscriptions.get("pending").size(), 0);
    assertEquals(subscriptions.get("foo").size(), 2);

    kuzzle.deleteSubscription("foo", "qux");

    assertEquals(subscriptions.keySet().size(), 2);
    assertEquals(subscriptions.get("pending").size(), 0);
    assertEquals(subscriptions.get("foo").size(), 1);

    kuzzle.deleteSubscription("foo", "bar");

    assertEquals(subscriptions.keySet().size(), 1);
    assertEquals(subscriptions.get("pending").size(), 0);
    assertEquals(subscriptions.containsKey("foo"), false);
  }
}
