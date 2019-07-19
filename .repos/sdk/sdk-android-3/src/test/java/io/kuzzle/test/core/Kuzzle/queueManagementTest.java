package io.kuzzle.test.core.Kuzzle;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;
import java.util.Date;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.sdk.util.QueryObject;
import io.kuzzle.test.testUtils.KuzzleExtend;
import io.kuzzle.test.testUtils.QueryArgsHelper;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class queueManagementTest {
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
  public void testFlushQueue() throws URISyntaxException, JSONException {
    Options options = new Options();
    options.setQueueTTL(1);
    options.setAutoReplay(true);
    options.setConnect(Mode.MANUAL);
    options.setAutoReconnect(true);
    options.setOfflineMode(Mode.AUTO);

    kuzzle = new KuzzleExtend("localhost", options, null);
    QueryObject o = new QueryObject();
    o.setTimestamp(new Date());
    o.setAction("test");
    o.setQuery(new JSONObject("{\"controller\":\"test\",\"volatile\":{},\"requestId\":\"a476ae61-497e-4338-b4dd-751ac22c6b61\",\"action\":\"test\",\"collection\":\"test\"}"));
    kuzzle.getOfflineQueue().add(o);
    o.setQuery(new JSONObject("{\"controller\":\"test2\",\"volatile\":{},\"requestId\":\"a476ae61-497e-4338-b4dd-751ac22c6b61\",\"action\":\"test2\",\"collection\":\"test2\"}"));
    kuzzle.getOfflineQueue().add(o);
    assertEquals(kuzzle.getOfflineQueue().size(), 2);
    kuzzle.flushQueue();
    assertEquals(kuzzle.getOfflineQueue().size(), 0);
  }

  @Test
  public void testManualQueuing() throws URISyntaxException, JSONException {
    Options options = new Options();
    options.setAutoReconnect(true);
    options.setAutoQueue(false);
    options.setDefaultIndex("testIndex");
    options.setQueueTTL(10000);
    options.setReplayInterval(1);
    options.setConnect(Mode.MANUAL);
    options.setQueuable(false);
    KuzzleExtend extended = new KuzzleExtend("localhost", options, null);
    extended.setSocket(s);
    extended.setState(States.CONNECTED);

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        s.onCloseReceived();
        return s;
      }
    }).when(s).close();
    extended.connect();
    extended.startQueuing();
    JSONObject query = new JSONObject();
    query.put("requestId", "42");
    extended.query(QueryArgsHelper.makeQueryArgs("test", "test"), query, null, null);
    assertEquals(extended.getOfflineQueue().size(), 1);
    extended.flushQueue();
    extended.stopQueuing();
    assertEquals(extended.getOfflineQueue().size(), 0);
    extended.query(QueryArgsHelper.makeQueryArgs("test", "test"), query, options, null);
    assertEquals(extended.getOfflineQueue().size(), 0);
  }

  @Test
  public void testQueuable() throws URISyntaxException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        s.onCloseReceived();
        return s;
      }
    }).when(s).close();
    Options options = new Options();
    options.setAutoReconnect(false);
    options.setDefaultIndex("testIndex");
    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.connect();
    kuzzle.listCollections(mock(ResponseListener.class));
    assertEquals(kuzzle.getOfflineQueue().size(), 1);
    kuzzle.flushQueue();
    options.setQueuable(false);
    kuzzle.listCollections(options, mock(ResponseListener.class));
    assertEquals(kuzzle.getOfflineQueue().size(), 0);
  }

  @Test
  public void testQueueMaxSize() throws URISyntaxException, JSONException {
    Options options = new Options();
    options.setAutoReconnect(true);
    options.setAutoQueue(true);
    options.setQueueTTL(1000);
    options.setQueueMaxSize(1);
    options.setAutoReplay(true);
    options.setConnect(Mode.MANUAL);
    options.setOfflineMode(Mode.AUTO);
    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setSocket(s);

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        s.onCloseReceived();
        return s;
      }
    }).when(s).close();
    kuzzle.connect();
    kuzzle.query(QueryArgsHelper.makeQueryArgs("test", "test"), new JSONObject(), mock(OnQueryDoneListener.class));
    kuzzle.query(QueryArgsHelper.makeQueryArgs("test2", "test2"), new JSONObject());
    kuzzle.query(QueryArgsHelper.makeQueryArgs("test3", "test3"), new JSONObject());
    assertEquals(kuzzle.getOfflineQueue().size(), 1);
    assertEquals(kuzzle.getOfflineQueue().peek().getQuery().getString("controller"), "test3");
    verify(s, never()).send(any(String.class));
  }


  @Test
  public void testDequeue() throws URISyntaxException, JSONException {
    Options options = new Options();
    options.setAutoReconnect(true);
    options.setQueueTTL(10000);
    options.setAutoReplay(true);
    options.setReplayInterval(1);
    options.setConnect(Mode.MANUAL);
    options.setOfflineMode(Mode.AUTO);
    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setSocket(s);

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        s.onCloseReceived();
        return s;
      }
    }).when(s).close();
    kuzzle.connect();

    QueryObject o = new QueryObject();
    o.setTimestamp(new Date());
    o.setAction("test");

    JSONObject query = new JSONObject("{\"controller\":\"test3\",\"volatile\":{},\"requestId\":\"a476ae61-497e-4338-b4dd-751ac22c6b61\",\"action\":\"test3\",\"collection\":\"test3\"}");
    o.setQuery(query);
    kuzzle.getOfflineQueue().add(o);

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        s.onOpen();
        return s;
      }
    }).when(s).connect();
    kuzzle.connect();
    kuzzle.setAutoReplay(false);
    kuzzle.replayQueue();
    verify(s, atLeastOnce()).send(eq(query.toString()));
  }
}
