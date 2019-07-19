package io.kuzzle.test.core.Kuzzle;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Event;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.EventListener;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.util.EventList;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class eventSystemTest {
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
  public void testAddListener() {
    assertEquals(kuzzle.getEventListeners(Event.connected), null);
    kuzzle.addListener(Event.connected, mock(EventListener.class));
    assertThat(kuzzle.getEventListeners(Event.connected), instanceOf(EventList.class));
  }

  @Test(expected = NullPointerException.class)
  public void testRemoveAllListeners() {
    kuzzle.addListener(Event.connected, mock(EventListener.class));
    kuzzle.addListener(Event.connected, mock(EventListener.class));
    kuzzle.addListener(Event.disconnected, mock(EventListener.class));
    kuzzle.addListener(Event.disconnected, mock(EventListener.class));
    assertEquals(kuzzle.getEventListeners(Event.connected).size(), 2);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).size(), 2);
    kuzzle.removeAllListeners();
    assertEquals(kuzzle.getEventListeners(Event.connected).size(), 0);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).size(), 0);
  }

  @Test
  public void testRemoveAllListenersType() {
    kuzzle.addListener(Event.connected, mock(EventListener.class));
    kuzzle.addListener(Event.connected, mock(EventListener.class));
    kuzzle.addListener(Event.disconnected, mock(EventListener.class));
    kuzzle.addListener(Event.disconnected, mock(EventListener.class));
    assertEquals(kuzzle.getEventListeners(Event.connected).size(), 2);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).size(), 2);
    kuzzle.removeAllListeners(Event.connected);
    assertEquals(kuzzle.getEventListeners(Event.connected).size(), 0);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).size(), 2);
  }

  @Test
  public void testRemoveListener() {
    EventListener listener1 = mock(EventListener.class);
    EventListener listener2 = mock(EventListener.class);
    EventListener listener3 = mock(EventListener.class);
    EventListener listener4 = mock(EventListener.class);
    kuzzle.addListener(Event.disconnected, listener1);
    kuzzle.addListener(Event.connected, listener2);
    kuzzle.addListener(Event.disconnected, listener3);
    kuzzle.addListener(Event.connected, listener4);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).get(listener1).getType(), Event.disconnected);
    assertEquals(kuzzle.getEventListeners(Event.connected).get(listener2).getType(), Event.connected);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).get(listener3).getType(), Event.disconnected);
    assertEquals(kuzzle.getEventListeners(Event.connected).get(listener4).getType(), Event.connected);
    kuzzle.removeListener(Event.connected, listener2);
    assertEquals(kuzzle.getEventListeners(Event.connected).size(), 1);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).size(), 2);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).get(listener1).getType(), Event.disconnected);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).get(listener2), null);
    assertEquals(kuzzle.getEventListeners(Event.disconnected).get(listener3).getType(), Event.disconnected);
    assertEquals(kuzzle.getEventListeners(Event.connected).get(listener4).getType(), Event.connected);
  }
}
