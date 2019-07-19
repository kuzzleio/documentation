package io.kuzzle.test.testUtils;

import android.support.annotation.NonNull;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.core.Room;
import io.kuzzle.sdk.enums.Event;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.sdk.util.EventList;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.mockito.Mockito.spy;

public class KuzzleExtend extends Kuzzle {
  protected WebSocketClient savedSocket = null;

  public ResponseListener loginCallback;

  public class KuzzleWebViewClient extends Kuzzle.KuzzleWebViewClient {
    public boolean shouldOverrideUrlLoading(WebView view, final String url) {
      return super.shouldOverrideUrlLoading(view, url);
    }
  }

  public KuzzleExtend.KuzzleWebViewClient getKuzzleWebViewClient() {
    return new KuzzleWebViewClient();
  }

  public KuzzleExtend(@NonNull final String host, final Options options, final ResponseListener<Void> connectionCallback) throws URISyntaxException {
    super(host, options, connectionCallback);
  }

  public void setState(States newState) {
    this.state = newState;
  }

  public void setSocket(WebSocketClient s) {
    this.socket = this.savedSocket = s;
  }

  public void setListener(ResponseListener listener) {
    this.connectionCallback = listener;
  }


  public Kuzzle deleteSubscription(final String roomId, final String id) {
    return super.deleteSubscription(roomId, id);
  }


  protected WebSocketClient createSocket() throws URISyntaxException {
    return this.savedSocket != null ? this.savedSocket : super.createSocket();
  }

  /**
   * * Returns all registered listeners on a given event
   *
   * @param event
   */
  public EventList getEventListeners(Event event) {
    return this.eventListeners.get(event);
  }

  /**
   * Get the subscription object from a Kuzzle instance
   *
   * @return
   */
  public Map<String, ConcurrentHashMap<String, Room>> getSubscriptions() {
    return this.subscriptions;
  }

  /**
   * Gets the internal socket instance from the kuzzle object
   * @return
   */
  public WebSocketClient getSocket() {
    return this.socket;
  }

  public void isValid() {
    super.isValid();
  }

  public void emitRequest(final JSONObject request, final OnQueryDoneListener listener) throws JSONException {
    super.emitRequest(request, listener);
  }

  public Kuzzle deletePendingSubscription(final String id) {
    return super.deletePendingSubscription(id);
  }

  public Map<String, Date> getRequestHistory() {
    return super.getRequestHistory();
  }

  public Map<String, Room> getPendingSubscriptions() {
    return super.getPendingSubscriptions();
  }

  public boolean isValidState() {
    return super.isValidState();
  }

  public ResponseListener<Void> spyAndGetConnectionCallback() {
    super.connectionCallback = spy(super.connectionCallback);
    return super.connectionCallback;
  }

  public void setSuperDefaultIndex(final String index) {
    super.defaultIndex = index;
  }

  public void emitEvent(final Event event, final Object... args) {
    super.emitEvent(event, args);
  }

  public void renewSubscriptions() {
    super.renewSubscriptions();
  }

  public void setJwtTokenWithoutSubscribe(final String token) {
    super.jwtToken = token;
  }

  public Kuzzle removeRoom(String channel) {
    super.removeRoom(channel);

    return this;
  }

}
