package io.kuzzle.test.listeners;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Event;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.sdk.util.QueryObject;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class KuzzleListenerTest {
  private Kuzzle kuzzle;
  private KuzzleExtend kuzzleExtend;
  private Kuzzle kuzzleSpy;
  private WebSocketClient s = mock(WebSocketClient.class);
  private io.kuzzle.sdk.util.Event event;
  private io.kuzzle.sdk.util.Event eventSpy;

  @Before
  public void setUp() throws URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", options, null);
    extended.setSocket(s);
    kuzzle = extended;
    kuzzleExtend = extended;
  }

  private void mockAnswer(Event e) {
    event = new io.kuzzle.sdk.util.Event(e) {
      @Override
      public void trigger(Object... args) {

      }
    };
    eventSpy = spy(event);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        eventSpy.trigger();
        return s;
      }
    }).when(s).connect();
  }

  @Test
  public void testCONNECTEDevent() throws URISyntaxException {
    mockAnswer(Event.connected);
    kuzzle.addListener(Event.connected, event);
    kuzzle.connect();
    verify(eventSpy, times(1)).trigger();
  }

  @Test
  public void testERRORevent() throws URISyntaxException {
    mockAnswer(Event.error);
    kuzzle.addListener(Event.error, event);
    kuzzle.connect();
    verify(eventSpy, times(1)).trigger();
  }

  @Test
  public void testDISCONNECTevent() throws URISyntaxException {
    mockAnswer(Event.disconnected);
    kuzzle.addListener(Event.disconnected, event);
    kuzzle.connect();
    verify(eventSpy, times(1)).trigger();
  }

  @Test
  public void testRECONNECTevent() throws URISyntaxException {
    mockAnswer(Event.reconnected);
    kuzzle.addListener(Event.reconnected, event);
    kuzzle.connect();
    verify(eventSpy, times(1)).trigger();
  }

  @Test
  public void testOfflineQueuePush() throws JSONException {
    event = new io.kuzzle.sdk.util.Event(Event.offlineQueuePush) {
      @Override
      public void trigger(Object... args) {

      }
    };
    eventSpy = spy(event);
    kuzzleExtend.addListener(Event.offlineQueuePush, eventSpy);

    Options opts = new Options().setQueuable(true);
    kuzzleExtend.setState(States.OFFLINE);
    kuzzleExtend.startQueuing();

    Kuzzle.QueryArgs args = new Kuzzle.QueryArgs();
    args.controller = "foo";
    args.action = "bar";
    kuzzleExtend.query(args, new JSONObject(), opts, mock(OnQueryDoneListener.class));
    ArgumentCaptor argument = ArgumentCaptor.forClass(QueryObject.class);
    verify(eventSpy).trigger(argument.capture());
    assertEquals(((QueryObject) argument.getValue()).getQuery().getString("action"), "bar");
  }

  @Test
  public void testOfflineQueuePop() throws JSONException, URISyntaxException {
    event = new io.kuzzle.sdk.util.Event(Event.offlineQueuePop) {
      @Override
      public void trigger(Object... args) {

      }
    };
    eventSpy = spy(event);
    kuzzleExtend.setAutoReplay(true);
    kuzzleExtend.addListener(Event.offlineQueuePop, eventSpy);
    mockAnswer(Event.reconnected);

    Options opts = new Options().setQueuable(true);
    kuzzleExtend.setState(States.OFFLINE);
    kuzzleExtend.startQueuing();

    Kuzzle.QueryArgs args = new Kuzzle.QueryArgs();
    args.controller = "foo";
    args.action = "bar";
    kuzzleExtend.query(args, new JSONObject(), opts, mock(OnQueryDoneListener.class));

    kuzzleExtend.connect();
    verify(eventSpy).trigger();
  }
}
