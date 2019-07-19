package io.kuzzle.test.core.Kuzzle;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.core.Room;
import io.kuzzle.sdk.enums.Event;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.test.testUtils.KuzzleDataCollectionExtend;
import io.kuzzle.test.testUtils.KuzzleExtend;
import io.kuzzle.test.testUtils.RoomExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class unsetJwtTokenTest {
  private KuzzleExtend kuzzle;
  private WebSocketClient s;
  private ConcurrentHashMap<String, Room> chp = new ConcurrentHashMap<>();
  private Room room;

  @Before
  public void setUp() throws URISyntaxException {
    kuzzle = new KuzzleExtend("host", mock(Options.class), mock(ResponseListener.class));
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    options.setDefaultIndex("testIndex");

    s = mock(WebSocketClient.class);
    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.getSubscriptions().put("1", chp);
    kuzzle.setSocket(s);

    kuzzle = spy(kuzzle);
    doNothing().when(kuzzle).emitEvent(any(Event.class), any(JSONObject.class));
    room = spy(new RoomExtend(new KuzzleDataCollectionExtend(kuzzle, "index", "collection")));
    chp.put("2", room);
  }

  @Test
  public void shouldUnsetTokenAndUnsubscribeAllRoom() {
    kuzzle.setJwtTokenWithoutSubscribe("42");
    kuzzle.unsetJwtToken();
    assertEquals(null, kuzzle.getJwtToken());
    verify(room).unsubscribe();
  }
}
