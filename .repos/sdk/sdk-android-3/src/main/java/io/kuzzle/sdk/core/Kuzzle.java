package io.kuzzle.sdk.core;

import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import io.kuzzle.sdk.enums.Event;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.EventListener;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.responses.TokenValidity;
import io.kuzzle.sdk.security.Security;
import io.kuzzle.sdk.security.User;
import io.kuzzle.sdk.state.KuzzleQueue;
import io.kuzzle.sdk.state.States;
import io.kuzzle.sdk.util.EventList;
import io.kuzzle.sdk.util.OfflineQueueLoader;
import io.kuzzle.sdk.util.QueryObject;
import io.kuzzle.sdk.util.QueueFilter;
import io.kuzzle.sdk_android.BuildConfig;
import tech.gusavila92.websocketclient.WebSocketClient;

/**
 * The type Kuzzle.
 */
public class Kuzzle {
  private final int MAX_EMIT_TIMEOUT = 10;
  private final int EVENT_TIMEOUT = 200;

  protected ConcurrentHashMap<Event, EventList> eventListeners = new ConcurrentHashMap<>();

  protected WebSocketClient socket;
  protected ConcurrentHashMap<String, OnQueryDoneListener> currentQueries = new ConcurrentHashMap<>();
  protected ConcurrentHashMap<String, EventListener> roomList = new ConcurrentHashMap<>();

  protected Map<String, Map<String, Collection>> collections = new ConcurrentHashMap<>();
  protected boolean autoReconnect = true;
  protected JSONObject headers = new JSONObject();
  protected JSONObject _volatile;
  protected String host;
  protected Integer port;
  protected boolean isSsl;
  protected ResponseListener<Void> connectionCallback;
  protected States state = States.INITIALIZING;
  protected long reconnectionDelay;
  protected boolean autoResubscribe;
  protected boolean autoQueue;
  protected boolean autoReplay;

  protected QueueFilter queueFilter = new QueueFilter() {
    @Override
    public boolean filter(JSONObject object) {
      return true;
    }
  };

  protected long replayInterval;
  protected boolean queuing = false;
  protected String defaultIndex;
  protected ConcurrentHashMap<String, Date> requestHistory = new ConcurrentHashMap<>();
  protected KuzzleQueue<QueryObject> offlineQueue = new KuzzleQueue<>();
  protected int queueTTL;
  protected int queueMaxSize;
  protected String jwtToken = null;

  /*
   This property contains the centralized subscription list in the following format:
    roomId:
      kuzzleRoomID_1: kuzzleRoomInstance_1,
      kuzzleRoomID_2: kuzzleRoomInstance_2,
      ...
    pending: // pending subscriptions
      kuzzleRoomID_x: kuzzleRoomInstance_x,
      ...

   This was made to allow multiple subscriptions on the same set of filters,
   something that Kuzzle does not permit.
   This structure also allows renewing subscriptions after a connection loss
   */
  protected ConcurrentHashMap<String, ConcurrentHashMap<String, Room>> subscriptions = new ConcurrentHashMap<>();

  private OfflineQueueLoader offlineQueueLoader;

  /**
   * Security static class
   */
  public Security security;

  private ResponseListener<JSONObject> loginCallback;

  public MemoryStorage memoryStorage;

  public static class QueryArgs {
    public String controller;
    public String action;
    public String index;
    public String collection;
  }

  /**
   * Emit an event to all registered listeners
   * An event cannot be emitted multiple times before a timeout has been reached.
   *
   * @param event - Event name to emit
   * @param args - Event payload
   */
  protected void emitEvent(Event event, Object... args) {
    long now = System.currentTimeMillis();

    if (this.eventListeners.containsKey(event)) {
      EventList l = this.eventListeners.get(event);

      if (l.lastEmitted < now - this.EVENT_TIMEOUT) {
        for (io.kuzzle.sdk.util.Event e : l.values()) {
          e.trigger(args);
        }

        l.lastEmitted = now;
      }
    }
  }

  /**
   * Connection status getter
   * @return Connection status
   */
  public States getState() {
    return state;
  }

  /**
   * Constructor
   *
   * @param host - Target host name or IP address
   * @param options - Request options
   * @param connectionCallback - On success callback listener
   * @throws URISyntaxException
   */
  public Kuzzle(@NonNull final String host, final Options options, final ResponseListener<Void> connectionCallback) throws URISyntaxException {
    if (host == null || host.isEmpty()) {
      throw new IllegalArgumentException("Host name/address can't be empty");
    }

    this.host = host;

    Options opt = (options != null ? options : new Options());

    this.autoQueue = opt.isAutoQueue();
    this.autoReconnect = opt.isAutoReconnect();
    this.autoReplay = opt.isAutoReplay();
    this.autoResubscribe = opt.isAutoResubscribe();
    this.defaultIndex = opt.getDefaultIndex();
    this.headers = opt.getHeaders();
    this._volatile = opt.getVolatile();
    this.port = opt.getPort();
    this.isSsl = opt.isSsl();
    this.queueMaxSize = opt.getQueueMaxSize();
    this.queueTTL = opt.getQueueTTL();
    this.reconnectionDelay = opt.getReconnectionDelay();
    this.replayInterval = opt.getReplayInterval();

    this.connectionCallback = connectionCallback;

    if (opt.getOfflineMode() == Mode.AUTO) {
      this.autoReconnect = this.autoQueue = this.autoReplay = this.autoResubscribe = true;
    }
    if (opt.getConnect() == Mode.AUTO) {
      connect();
    } else {
      this.state = States.READY;
    }

    this.security = new Security(this);
    this.memoryStorage = new MemoryStorage(this);
    this.subscriptions.put("pending", new ConcurrentHashMap<String, Room>());
  }

  /**
   * Constructor
   *
   * @param host - Target Kuzzle host name or IP address
   * @throws URISyntaxException
   */
  public Kuzzle(@NonNull final String host) throws URISyntaxException {
    this(host, null, null);
  }

  /**
   * Constructor
   *
   * @param host - Target Kuzzle host name or IP address
   * @param cb - On success connection callback listener
   * @throws URISyntaxException
   */
  public Kuzzle(@NonNull final String host, final ResponseListener<Void> cb) throws URISyntaxException {
    this(host, null, cb);
  }

  /**
   * Constructor
   *
   * @param host - Target Kuzzle host name or IP address
   * @param options - Request options
   * @throws URISyntaxException
   */
  public Kuzzle(@NonNull final String host, Options options) throws URISyntaxException {
    this(host, options, null);
  }

  /**
   * Adds a listener to a Kuzzle global event. When an event is triggered,
   * listeners are called in the order of their insertion.
   *
   * @param kuzzleEvent - Name of the global event to subscribe to
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle addListener(final Event kuzzleEvent, final EventListener listener) {
    this.isValid();

    io.kuzzle.sdk.util.Event e = new io.kuzzle.sdk.util.Event(kuzzleEvent) {
      @Override
      public void trigger(Object... args) {
        listener.trigger(args);
      }
    };

    if (!eventListeners.containsKey(kuzzleEvent)) {
      eventListeners.put(kuzzleEvent, new EventList());
    }

    eventListeners.get(kuzzleEvent).put(listener, e);
    return this;
  }

  /**
   * Check an authentication token validity
   *
   * @param token - Token to check (JWT)
   * @param listener - Response callback listener
   */
  public void checkToken(@NonNull final String token, @NonNull final ResponseListener<TokenValidity> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.checkToken: listener required");
    }

    if (token == null || token.isEmpty()) {
      throw new IllegalArgumentException("Kuzzle.checkToken: token required");
    }

    try {
      QueryArgs args = new QueryArgs();
      args.controller = "auth";
      args.action = "checkToken";
      JSONObject request = new JSONObject();
      request.put("body", new JSONObject().put("token", token));
      this.query(args, request, new Options().setQueuable(false), new OnQueryDoneListener() {

        @Override
        public void onSuccess(JSONObject response) {
          try {
            TokenValidity validity = new TokenValidity();
            JSONObject result = response.getJSONObject("result");
            validity.setValid(result.getBoolean("valid"));
            if (validity.isValid()) {
              validity.setExpiresAt(new Date(result.getLong("expiresAt")));
            } else {
              validity.setState(result.getString("state"));
            }
            listener.onSuccess(validity);
          } catch (JSONException e) {
            throw new RuntimeException();
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Connects to a Kuzzle instance using the provided host and port.
   *
   * @return this
   * @throws URISyntaxException
   */
  public Kuzzle connect() throws URISyntaxException {
    if (!this.isValidState()) {
      if (connectionCallback != null) {
        connectionCallback.onSuccess(null);
        return this;
      }
    }

    if (this.socket != null) {
      this.disconnect();
    }

    this.socket = createSocket();

    Kuzzle.this.state = States.CONNECTING;

    if (socket != null) {
      socket.connect();
    }

    return this;
  }

  /**
   * Collection object factory. Default index must be set.
   *
   * @param collection - Data collection name
   * @return Instantiated Collection object
   */
  public Collection collection(@NonNull final String collection) {
    this.isValid();
    if (this.defaultIndex == null) {
      throw new IllegalArgumentException("Collection: unable to create a new data collection object: no index specified");
    }

    return this.collection(collection, this.defaultIndex);
  }

  /**
   * Collection object factory
   *
   * @param collection - Data collection name
   * @param index - Parent data index name
   * @return Instantiated Collection object
   */
  public Collection collection(@NonNull final String collection, @NonNull final String index) {
    this.isValid();
    if (index == null && this.defaultIndex == null) {
      throw new IllegalArgumentException("Collection: unable to create a new data collection object: no index specified");
    }

    if (!this.collections.containsKey(collection)) {
      Map<String, Collection> col = new ConcurrentHashMap<>();
      col.put(collection, new Collection(this, collection, index));
      this.collections.put(index, col);
    }
    return this.collections.get(index).get(collection);
  }

  /**
   * {@link #createIndex(String, Options, ResponseListener)}
   */
  public Kuzzle createIndex(@NonNull final String index, final ResponseListener<JSONObject> cb) {
    return createIndex(index, null, cb);
  }

  /**
   * {@link #createIndex(String, Options, ResponseListener)}
   */
  public Kuzzle createIndex(@NonNull final String index) {
    return createIndex(index, null, null);
  }

  /**
   * Create a new data index
   *
   * @param index - index name to create
   * @param options - Request options
   * @param cb - Response callback listener
   * @return this
   */
  public Kuzzle createIndex(@NonNull final String index, final Options options, final ResponseListener<JSONObject> cb) {
    if (index == null && defaultIndex == null) {
      throw new IllegalArgumentException("Collection.createIndex: index required");
    }

    QueryArgs args = new QueryArgs();
    args.controller = "index";
    args.action = "create";

    JSONObject request = new JSONObject();
    try {
      request.put("index", index == null ? defaultIndex : index);
      this.query(args, request, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            cb.onSuccess(response.getJSONObject("result"));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          cb.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * Empties the offline queue without replaying it.
   *
   * @return this
   */
  public Kuzzle flushQueue() {
    this.getOfflineQueue().clear();
    return this;
  }

  /**
   * {@link #getAllStatistics(Options, ResponseListener)}
   */
  public void getAllStatistics(@NonNull final ResponseListener<JSONObject[]> listener) {
    this.getAllStatistics(null, listener);
  }

  /**
   * Get all Kuzzle usage statistics frames
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void getAllStatistics(final Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.getAllStatistics: listener required");
    }

    this.isValid();
    try {
      QueryArgs args = new QueryArgs();
      args.controller = "server";
      args.action = "getAllStats";
      this.query(args, null, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject object) {
          try {
            JSONArray hits = object.getJSONObject("result").getJSONArray("hits");
            JSONObject[] frames = new JSONObject[hits.length()];

            for(int i = 0; i < hits.length(); i++) {
              frames[i] = hits.getJSONObject(i);
            }

            listener.onSuccess(frames);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #getStatistics(Options, ResponseListener)}
   */
  public void getStatistics(@NonNull final ResponseListener<JSONObject[]> listener) {
    this.getStatistics(null, listener);
  }

  /**
   * Get Kuzzle usage statistics
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void getStatistics(final Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.getStatistics: listener required");
    }
    this.isValid();
    JSONObject body = new JSONObject();
    JSONObject data = new JSONObject();

    try {
      body.put("body", data);
      QueryArgs args = new QueryArgs();
      args.controller = "server";
      args.action = "getLastStats";
      this.query(args, body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(new JSONObject[]{response.getJSONObject("result")});
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #getStatistics(long, Options, ResponseListener)}
   */
  public void getStatistics(long timestamp, @NonNull final ResponseListener<JSONObject[]> listener) {
    this.getStatistics(timestamp, null, listener);
  }

  /**
   * Get Kuzzle usage statistics starting from a provided timestamp
   *
   * @param timestamp - Statistic starting time to retrieve
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void getStatistics(long timestamp, final Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.getStatistics: listener required");
    }

    this.isValid();
    JSONObject body = new JSONObject();
    JSONObject data = new JSONObject();

    try {
      data.put("since", timestamp);
      body.put("body", data);
      QueryArgs args = new QueryArgs();
      args.controller = "server";
      args.action = "getStats";
      this.query(args, body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONArray hits = response.getJSONObject("result").getJSONArray("hits");
            JSONObject[] stats = new JSONObject[hits.length()];

            for (int i = 0; i < hits.length(); i++) {
              stats[i] = hits.getJSONObject(i);
            }

            listener.onSuccess(stats);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #getServerInfo(Options, ResponseListener)}
   */
  public void getServerInfo(@NonNull final ResponseListener<JSONObject> listener) {
    this.getServerInfo(null, listener);
  }

  /**
   * Gets server info.
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void getServerInfo(final Options options, @NonNull final ResponseListener<JSONObject> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.getServerInfo: listener required");
    }
    QueryArgs args = new QueryArgs();
    args.controller = "server";
    args.action = "info";
    try {
      this.query(args, null, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result").getJSONObject("serverInfo"));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #listCollections(Options, ResponseListener)}
   */
  public void listCollections(@NonNull final ResponseListener<JSONObject[]> listener) {
    this.listCollections(null, null, listener);
  }

  /**
   * {@link #listCollections(String, Options, ResponseListener)}
   */
  public void listCollections(String index, @NonNull final ResponseListener<JSONObject[]> listener) {
    this.listCollections(index, null, listener);
  }

  /**
   * List data collections on the default data index
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void listCollections(Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    this.listCollections(null, options, listener);
  }

  /**
   * List data collections
   *
   * @param index - Parent data index name
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void listCollections(String index, Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    if (index == null) {
      if (this.defaultIndex == null) {
        throw new IllegalArgumentException("Kuzzle.listCollections: index required");
      } else {
        index = this.defaultIndex;
      }
    }
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.listCollections: listener required");
    }
    try {
      QueryArgs args = new QueryArgs();
      args.controller = "collection";
      args.action = "list";
      args.index = index;
      JSONObject query = new JSONObject();
      if (options == null) {
        options = new Options();
      }
      JSONObject body = new JSONObject().put("type", options.getCollectionType());
      query.put("body", body);
      this.query(args, query, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject collections) {
          try {
            JSONArray result = collections.getJSONObject("result").getJSONArray("collections");
            JSONObject[] cols = new JSONObject[result.length()];

            for (int i = 0; i < result.length(); i++) {
              cols[i] = result.getJSONObject(i);
            }

            listener.onSuccess(cols);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #listIndexes(Options, ResponseListener)}
   */
  public void listIndexes(@NonNull final ResponseListener<String[]> listener) {
    this.listIndexes(null, listener);
  }

  /**
   * List data indexes
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void listIndexes(final Options options, @NonNull final ResponseListener<String[]> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.listIndexes: listener required");
    }
    QueryArgs args = new QueryArgs();
    args.controller = "index";
    args.action = "list";
    try {
      this.query(args, null, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONArray array = response.getJSONObject("result").getJSONArray("indexes");
            int length = array.length();
            String[] indexes = new String[length];
            for (int i = 0; i < length; i++) {
              indexes[i] = array.getString(i);
            }
            listener.onSuccess(indexes);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #login(String, JSONObject, int, ResponseListener)}
   */
  public void login(@NonNull final String strategy) {
    this.login(strategy, null, -1, null);
  }

  /**
   * {@link #login(String, JSONObject, int, ResponseListener)}
   */
  public void login(@NonNull final String strategy, final JSONObject credentials) {
    this.login(strategy, credentials, -1, null);
  }

  /**
   * {@link #login(String, JSONObject, int, ResponseListener)}
   */
  public void login(@NonNull final String strategy, final int expiresIn) {
    this.login(strategy, null, expiresIn, null);
  }

  /**
   * {@link #login(String, JSONObject, int, ResponseListener)}
   */
  public void login(@NonNull final String strategy, final JSONObject credentials, final int expiresIn) {
    this.login(strategy, credentials, expiresIn, null);
  }

  /**
   * {@link #login(String, JSONObject, int, ResponseListener)}
   */
  public void login(@NonNull final String strategy, final JSONObject credentials, final ResponseListener<JSONObject> listener) {
    this.login(strategy, credentials, -1, listener);
  }

  /**
   * {@link #login(String, JSONObject, int, ResponseListener)}
   */
  public void login(@NonNull final String strategy, final ResponseListener<JSONObject> listener) {
    this.login(strategy, null, -1, listener);
  }

  /**
   * {@link #login(String, JSONObject, int, ResponseListener)}
   */
  public void login(@NonNull final String strategy, final int expiresIn, final ResponseListener<JSONObject> listener) {
    this.login(strategy, null, expiresIn, listener);
  }

  /**
   * Log- Strategy name to use for the authentication
   *
   * @param strategy - Strategy name to use for the authentication
   * @param credentials - Login credentials
   * @param expiresIn - Token expiration delay
   * @param listener - Response callback listener
   */
  public void login(@NonNull final String strategy, final JSONObject credentials, int expiresIn, final ResponseListener<JSONObject> listener) {
    if (strategy == null) {
      throw new IllegalArgumentException("Kuzzle.login: cannot authenticate to Kuzzle without an authentication strategy");
    }

    this.loginCallback = listener;

    try {
      Options options = new Options();
      JSONObject query = new JSONObject();
      JSONObject body = new JSONObject();
      if (credentials != null) {
        body = credentials;
      }

      if (expiresIn >= 0) {
        query.put("expiresIn", expiresIn);
      }

      query.put("strategy", strategy);

      query.put("body", body);
      QueryArgs args = new QueryArgs();
      args.controller = "auth";
      args.action = "login";
      options.setQueuable(false);

      this.query(args, query, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject object) {
          try {
            JSONObject result = object.getJSONObject("result");

            if (result.has("jwt")) {
              Kuzzle.this.setJwtToken(result.getString("jwt"));
            }

            if (listener != null) {
              listener.onSuccess(result);
            }
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          try {
            emitEvent(Event.loginAttempt, new JSONObject()
                    .put("success", false)
                    .put("error", error));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * WebViewClient to forward kuzzle's jwt token after an OAuth authentication
   */
  protected class KuzzleWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, final String url) {
      if (url.contains("code=")) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              HttpURLConnection conn = (HttpURLConnection) URI.create(url).toURL().openConnection();
              conn.setRequestMethod("GET");
              conn.setUseCaches(false);

              BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
              StringBuilder sb = new StringBuilder();
              String line;
              while ((line = br.readLine()) != null) {
                sb.append(line);
              }
              br.close();

              JSONObject response = new JSONObject(sb.toString());
              if (response.isNull("error")) {
                Kuzzle.this.setJwtToken(response);

                if (loginCallback != null) {
                  loginCallback.onSuccess(response.getJSONObject("result"));
                }
              } else {
                emitEvent(Event.loginAttempt, new JSONObject()
                        .put("success", false)
                        .put("error", response.getJSONObject("error")));
                if (loginCallback != null) {
                  loginCallback.onError(response.getJSONObject("error"));
                }
              }
            } catch (JSONException | IOException e) {
              e.printStackTrace();
            }
          }
        }).start();
      } else {
        view.loadUrl(url);
      }
      return true;
    }
  }

  /**
   * Gets kuzzle web view client.
   *
   * @return the kuzzle web view client
   */
  public KuzzleWebViewClient getKuzzleWebViewClient() {
    return new KuzzleWebViewClient();
  }

  /**
   * Disconnect from Kuzzle and invalidate this instance.
   * Does not fire a disconnected event.
   */
  public void disconnect() {
    if (this.socket != null) {
      this.socket.close();
    }

    this.socket = null;
    this.collections.clear();
    this.state = States.DISCONNECTED;
  }

  /**
   * {@link #logout(ResponseListener)}
   */
  public Kuzzle logout() {
    return this.logout(null);
  }

  /**
   * Logout method
   *
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle logout(final ResponseListener<Void> listener) {
    Options options = new Options();

    options.setQueuable(false);

    try {
      QueryArgs args = new QueryArgs();
      args.controller = "auth";
      args.action = "logout";

      this.query(args, new JSONObject(), options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject object) {
          if (listener != null) {
            listener.onSuccess(null);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    Kuzzle.this.jwtToken = null;
    return this;
  }

  /**
   * {@link #now(Options, ResponseListener)}
   */
  public void now(@NonNull final ResponseListener<Date> listener) {
    this.now(null, listener);
  }

  /**
   * Returns the current Kuzzle UTC timestamp
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void now(final Options options, @NonNull final ResponseListener<Date> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.now: listener required");
    }
    this.isValid();
    try {
      QueryArgs args = new QueryArgs();
      args.controller = "server";
      args.action = "now";
      this.query(args, null, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(new Date(response.getJSONObject("result").getLong("now")));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #query(QueryArgs, JSONObject, Options, OnQueryDoneListener)}
   */
  public Kuzzle query(final QueryArgs queryArgs, final JSONObject query) throws JSONException {
    return this.query(queryArgs, query, null, null);
  }

  /**
   * {@link #query(QueryArgs, JSONObject, Options, OnQueryDoneListener)}
   */
  public Kuzzle query(final QueryArgs queryArgs, final JSONObject query, final Options options) throws JSONException {
    return this.query(queryArgs, query, options, null);
  }

  /**
   * {@link #query(QueryArgs, JSONObject, Options, OnQueryDoneListener)}
   */
  public Kuzzle query(final QueryArgs queryArgs, final JSONObject query, final OnQueryDoneListener listener) throws JSONException {
    return this.query(queryArgs, query, null, listener);
  }

  /**
   * This is a low-level method, exposed to allow advanced SDK users to bypass high-level methods.
   * Base method used to send queries to Kuzzle
   *
   * @param queryArgs - API route description
   * @param query - Query content
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   * @throws JSONException
   */
  public Kuzzle query(final QueryArgs queryArgs, final JSONObject query, final Options options, final OnQueryDoneListener listener) throws JSONException {
    this.isValid();
    JSONObject object = query != null ? query : new JSONObject();

    if (object.isNull("requestId")) {
      object.put("requestId", UUID.randomUUID().toString());
    }

    object
            .put("action", queryArgs.action)
            .put("controller", queryArgs.controller);

    // Global volatile data
    JSONObject _volatile = new JSONObject();
    for (Iterator ite = this._volatile.keys(); ite.hasNext(); ) {
      String key = (String) ite.next();
      _volatile.put(key, this._volatile.get(key));
    }

    // Volatile data for this query
    if (options != null) {
      if (!options.isQueuable() && this.state != States.CONNECTED) {
        discardRequest(listener, object);
        return this;
      }

      if (options.getRefresh() != null) {
        object.put("refresh", options.getRefresh());
      }

      if (options.getVolatile() != null) {
        for (Iterator iterator = options.getVolatile().keys(); iterator.hasNext(); ) {
          String key = (String) iterator.next();
          _volatile.put(key, options.getVolatile().get(key));
        }
      }

      if (options.getFrom() != null) {
        object.put("from", options.getFrom());
      }

      if (options.getSize() != null) {
        object.put("size", options.getSize());
      }

      if (options.getScroll() != null) {
        object.put("scroll", options.getScroll());
      }

      if (options.getScrollId() != null) {
        object.put("scrollId", options.getScrollId());
      }
    }

    _volatile.put("sdkVersion", this.getSdkVersion());
    object.put("volatile", _volatile);

    if (queryArgs.collection != null) {
      object.put("collection", queryArgs.collection);
    }

    if (queryArgs.index != null) {
      object.put("index", queryArgs.index);
    }

    this.addHeaders(object, this.headers);

    /*
     * Do not add the token for the checkToken route, to avoid getting a token error when
     * a developer simply wish to verify his token
     */
    if (this.jwtToken != null && !(queryArgs.controller.equals("auth") && queryArgs.action.equals("checkToken"))) {
      object.put("jwt", this.jwtToken);
    }

    if (this.state == States.CONNECTED || (options != null && !options.isQueuable())) {
      emitRequest(object, listener == null ? null : new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          listener.onSuccess(response);
        }

        @Override
        public void onError(JSONObject error) {
          if (error != null) {
            listener.onError(error);
          }
        }
      });
    } else if (this.queuing || (options != null && options.isQueuable()) || this.state == States.INITIALIZING || this.state == States.CONNECTING) {
      cleanQueue();

      if (queueFilter.filter(object)) {
        QueryObject o = new QueryObject();
        o.setTimestamp(new Date());
        o.setCb(listener);
        o.setQuery(object);
        this.offlineQueue.addToQueue(o);
        Kuzzle.this.emitEvent(Event.offlineQueuePush, o);
      }
    } else {
      discardRequest(listener, object);
    }

    return this;
  }

  /**
   * Removes all listeners, either from all events
   *
   * @return this
   */
  public Kuzzle removeAllListeners() {
    this.eventListeners.clear();
    return this;
  }

  /**
   * Remove all listeners kuzzle from the provided event name
   *
   * @param event - Event name
   * @return this
   */
  public Kuzzle removeAllListeners(Event event) {
    if (eventListeners.containsKey(event)) {
      eventListeners.get(event).clear();
    }

    return this;
  }

  /**
   * Removes a listener from an event.
   *
   * @param event - Event name
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle removeListener(Event event, EventListener listener) {
    if (eventListeners.containsKey(event)) {
      eventListeners.get(event).remove(listener);
    }

    return this;
  }

  /**
   * Renew all registered subscriptions. Usually called after:
   * - a connection, if subscriptions occurred before
   * - a reconnection
   * - after a successful login attempt, to subscribe with the new credentials
   */
  protected void renewSubscriptions() {
    for (Map<String, Room> roomSubscriptions : subscriptions.values()) {
      for (Room room : roomSubscriptions.values()) {
        room.renew(room.getListener(), room.getSubscribeListener());
      }
    }
  }

  /**
   * Replays the requests queued during offline mode.
   * Works only if the SDK is not in a disconnected state, and if the autoReplay option is set to false.
   *
   * @return this
   */
  public Kuzzle replayQueue() {
    if (this.state != States.OFFLINE && !this.autoReplay) {
      this.cleanQueue();
      this.dequeue();
    }
    return this;
  }

  /**
   * {@link #setHeaders(JSONObject, boolean)}
   */
  public Kuzzle setHeaders(final JSONObject content) throws JSONException {
    return this.setHeaders(content, false);
  }

  /**
   * Helper function allowing to set headers while chaining calls.
   * If the replace argument is set to true, replace the current headers with the provided content.
   * Otherwise, it appends the content to the current headers, only replacing already existing values
   *
   * @param content - New headers content
   * @param replace - false = append to existing headers (default) - true = replace
   * @return this
   */
  public Kuzzle setHeaders(final JSONObject content, boolean replace) {
    if (this.headers == null) {
      this.headers = new JSONObject();
    }
    if (replace) {
      this.headers = content;
    } else {
      if (content != null) {
        try {
          for (Iterator ite = content.keys(); ite.hasNext(); ) {
            String key = (String) ite.next();
            this.headers.put(key, content.get(key));
          }
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return this;
  }

  /**
   * Starts the requests queuing. Works only during offline mode, and if the autoQueue option is set to false.
   *
   * @return this
   */
  public Kuzzle startQueuing() {
    if (this.state == States.OFFLINE && !this.autoQueue) {
      this.queuing = true;
    }
    return this;
  }

  /**
   * Stops the requests queuing. Works only during offline mode, and if the autoQueue option is set to false.
   *
   * @return this
   */
  public Kuzzle stopQueuing() {
    if (this.state == States.OFFLINE && !this.autoQueue) {
      this.queuing = false;
    }
    return this;
  }

  /**
   * Is valid sate boolean.
   *
   * @return current state validity
   */
  protected boolean isValidState() {
    switch (this.state) {
      case INITIALIZING:
      case READY:
      case DISCONNECTED:
      case ERROR:
      case OFFLINE:
        return true;
    }
    return false;
  }

  /**
   * Handles network reconnection
   */
  private void reconnect() {
    if (this.autoResubscribe) {
      this.renewSubscriptions();
    }

    if (this.autoReplay) {
      this.cleanQueue();
      this.dequeue();
    }

    this.emitEvent(Event.reconnected);
  }

  /**
   * Create a new connection socket
   * @return created socket
   * @throws URISyntaxException
   */
  protected WebSocketClient createSocket() throws URISyntaxException {
    URI uri = null;
    try {
      uri = new URI((this.isSsl ? "wss" : "ws")+"://"+this.host+":"+this.port+"/");
    }
    catch (URISyntaxException e) {
      e.printStackTrace();
    }
    socket = new WebSocketClient(uri) {
      @Override
      public void onOpen() {
        if (Kuzzle.this.state == States.OFFLINE) { // Reconnect
          Kuzzle.this.state = States.CONNECTED;

          if (Kuzzle.this.jwtToken != null) {
            Kuzzle.this.checkToken(jwtToken, new ResponseListener<TokenValidity>() {
              @Override
              public void onSuccess(TokenValidity response) {
                if (!response.isValid()) {
                  Kuzzle.this.jwtToken = null;
                  Kuzzle.this.emitEvent(Event.tokenExpired);
                }

                Kuzzle.this.reconnect();
              }

              @Override
              public void onError(JSONObject error) {
                Kuzzle.this.jwtToken = null;
                Kuzzle.this.emitEvent(Event.tokenExpired);
                Kuzzle.this.reconnect();
              }
            });
          } else {
            Kuzzle.this.reconnect();
          }
        } else {
          Kuzzle.this.state = States.CONNECTED;

          Kuzzle.this.renewSubscriptions();
          Kuzzle.this.dequeue();
          Kuzzle.this.emitEvent(Event.connected);

          if (Kuzzle.this.connectionCallback != null) {
            Kuzzle.this.connectionCallback.onSuccess(null);
          }
        }
      }

      @Override
      public void onTextReceived(String message) {
        try {
          JSONObject json = new JSONObject(message);
          OnQueryDoneListener listener = null;
          if (json.has("requestId")) {
            listener = currentQueries.get(json.getString("requestId"));
          } else {
            listener = currentQueries.get(json.getString("room"));
          }

          if (listener != null) {
            // checking token expiration
            if (!json.isNull("error") && json.getJSONObject("error").getString("message").equals("Token expired") && !json.getString("action").equals("logout")) {
              emitEvent(Event.tokenExpired, listener);
            }

            if (!json.isNull("error")) {
              listener.onError(json.getJSONObject("error"));
            } else {
              listener.onSuccess(json);
            }
            currentQueries.remove(json.getString("requestId"));
          }

          EventListener l  = roomList.get(json.getString("room"));
          if (l != null) {
            l.trigger(new JSONObject(message));
          }
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onBinaryReceived(byte[] data) {

      }

      @Override
      public void onPingReceived(byte[] data) {

      }

      @Override
      public void onPongReceived(byte[] data) {

      }

      @Override
      public void onException(Exception e) {
        Kuzzle.this.state = States.ERROR;
        Kuzzle.this.emitEvent(Event.error, e.getMessage());

        if (connectionCallback != null) {
          JSONObject error = new JSONObject();
          try {
            error.put("message", e.getMessage());
          } catch (JSONException ee) {
            throw new RuntimeException(ee);
          }
          connectionCallback.onError(error);
        }
      }

      @Override
      public void onCloseReceived() {
        Kuzzle.this.state = States.OFFLINE;
        if (!Kuzzle.this.autoReconnect) {
          Kuzzle.this.disconnect();
        }
        if (Kuzzle.this.autoQueue) {
          Kuzzle.this.queuing = true;
        }
        currentQueries.clear();

        Kuzzle.this.emitEvent(Event.disconnected);
      }
    };

    if (this.autoReconnect) {
      socket.enableAutomaticReconnection(this.reconnectionDelay);
    }

    return socket;
  }

  /**
   * Emit request.
   *
   * @param request - Request to emit
   * @param listener - Response callback listener
   * @throws JSONException
   */
  protected void emitRequest(final JSONObject request, final OnQueryDoneListener listener) throws JSONException {
    Date now = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(now);
    c.add(Calendar.SECOND, -MAX_EMIT_TIMEOUT);

    if (listener != null) {
      currentQueries.put(request.get("requestId").toString(), listener);
    }

    socket.send(request.toString());

    // Track requests made to allow Room.subscribeToSelf to work
    this.requestHistory.put(request.getString("requestId"), new Date());

    // Clean history from requests made more than 10s ago
    Iterator ite = requestHistory.keySet().iterator();

    while (ite.hasNext()) {
      if (this.requestHistory.get(ite.next()).before(c.getTime())) {
        ite.remove();
      }
    }
  }

  /**
   * Helper function ensuring that this Kuzzle object is still valid before performing a query
   */
  protected void isValid() {
    if (this.state == States.DISCONNECTED) {
      throw new IllegalStateException("This Kuzzle object has been invalidated. Did you try to access it after a disconnect call?");
    }
  }

  /**
   * Helper function copying headers to the query data
   *
   * @param query - Query to update
   * @param headers - Headers to set
   */
  public void addHeaders(JSONObject query, final JSONObject headers) {
    try {
      for (Iterator iterator = headers.keys(); iterator.hasNext(); ) {
        String key = (String) iterator.next();
        if (query.isNull(key)) {
          query.put(key, headers.get(key));
        }
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * autoReconnect option getter
   *
   * @return autoReconnect option value
   */
  public boolean isAutoReconnect() {
    return autoReconnect;
  }

  /**
   * autoResubscribe option getter
   *
   * @return autoResubscribe option value
   */
  public boolean isAutoResubscribe() {
    return this.autoResubscribe;
  }

  /**
   * autoResubscribe option setter
   *
   * @param resubscribe - autoResubscribe new value
   * @return this
   */
  public Kuzzle setAutoResubscribe(boolean resubscribe) {
    this.autoResubscribe = resubscribe;
    return this;
  }

  /**
   * Global headers getter
   *
   * @return global headers
   */
  public JSONObject getHeaders() {
    return this.headers;
  }

  /**
   * Returns the current SDK version.
   *
   * @return Current SDK version
   */
  public String getSdkVersion() {
    return BuildConfig.VERSION_NAME;
  }

  /**
   * Add pending subscription kuzzle.
   *
   * @param id - Room instance unique ID
   * @param room - Associated Room instance
   * @return this
   */
  protected Kuzzle addPendingSubscription(final String id, final Room room) {
    this.subscriptions.get("pending").put(id, room);
    return this;
  }

  /**
   * Delete a pending subscription
   *
   * @param id - Unique ID of the Room instance to delete
   * @return this
   */
  protected Kuzzle deletePendingSubscription(final String id) {
    ConcurrentHashMap<String, Room> pending = this.subscriptions.get("pending");

    if (pending != null) {
      pending.remove(id);
    }

    return this;
  }

  /**
   * Add a new subscription
   *
   * @param roomId - Subscription identifier
   * @param id - Room instance unique ID
   * @param kuzzleRoom - Associated Room instance
   * @return this
   */
  protected Kuzzle addSubscription(final String roomId, final String id, final Room kuzzleRoom) {
    ConcurrentHashMap<String, Room> room = this.subscriptions.get(roomId);

    if (room == null) {
      room = new ConcurrentHashMap<>();
      this.subscriptions.put(id, room);
    }

    room.put(id, kuzzleRoom);

    return this;
  }

  protected Kuzzle addRoom(final String channel, EventListener listener) {
    if (channel != null) {
      roomList.put(channel, listener);
    }

    return this;
  }

  protected Kuzzle removeRoom(final String channel) {
    if (channel != null) {
      roomList.remove(channel);
    }

    return this;
  }

  /**
   * Connection socket getter
   *
   * @return Connection socket
   */
  protected WebSocketClient getSocket() {
    return socket;
  }

  /**
   * Connection socket setter
   *
   * @param socket - New connection socket
   */
  protected void setSocket(WebSocketClient socket) {
    this.socket = socket;
  }

  /**
   * Add a query to the offline queue
   *
   * @param query - Query to queue
   * @return this
   */
  public Kuzzle setOfflineQueue(final QueryObject query) {
    this.offlineQueue.addToQueue(query);
    return this;
  }

  /**
   * Offline queue getter
   *
   * @return offline queue
   */
  public Queue<QueryObject> getOfflineQueue() {
    return this.offlineQueue.getQueue();
  }


  /**
   * Sets offline queue filter.
   *
   * @param queueFilter - Offline queue global filter
   * @return this
   */
  public Kuzzle setQueueFilter(QueueFilter queueFilter) {
    this.queueFilter = queueFilter;
    return this;
  }

  /**
   * Offline queue filter getter
   *
   * @return Offline queue filter
   */
  public QueueFilter getQueueFilter() {
    return this.queueFilter;
  }

  /**
   * autoReplay option getter
   *
   * @return autoReplay option value
   */
  public boolean isAutoReplay() {
    return autoReplay;
  }


  /**
   * Kuzzle server port getter
   *
   * @return Kuzzle server port
   */
  public int getPort() {
    return port;
  }

  /**
   * Kuzzle server port setter
   *
   * @param port - New Kuzzle server port
   * @return this
   */
  public Kuzzle setPort(int port) {
    this.port = port;
    return this;
  }

  /**
   * Kuzzle server host name getter
   *
   * @return Kuzzle server host name
   */
  public String getHost() {
    return this.host;
  }

  /**
   * Kuzzle server host name setter
   *
   * @param host - New host name
   * @return this
   */
  public Kuzzle setHost(String host) {
    this.host = host;
    return this;
  }

  /**
   * autoReplay option setter
   *
   * @param autoReplay - New autoReplay option value
   * @return this
   */
  public Kuzzle setAutoReplay(boolean autoReplay) {
    this.autoReplay = autoReplay;
    return this;
  }

  /**
   * queueMaxSize option setter
   *
   * @param newMaxSize - New queueMaxSize value
   * @return this
   */
  public Kuzzle setQueueMaxSize(int newMaxSize) {
    this.queueMaxSize = Math.max(0, newMaxSize);
    return this;
  }

  /**
   * queueMaxSize option getter
   *
   * @return queueMaxSize option value
   */
  public int getQueueMaxSize() {
    return this.queueMaxSize;
  }

  /**
   * autoQueue option getter
   *
   * @return autoQueue option value
   */
  public boolean isAutoQueue() {
    return autoQueue;
  }

  /**
   * autoQueue option setter
   *
   * @param autoQueue - New autoQueue option value
   * @return this
   */
  public Kuzzle setAutoQueue(final boolean autoQueue) {
    this.autoQueue = autoQueue;
    return this;
  }

  /**
   * Clean up the queue, ensuring the queryTTL and queryMaxSize properties are respected
   */
  private void cleanQueue() {
    Date now = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(now);
    cal.add(Calendar.MILLISECOND, -queueTTL);

    if (this.queueTTL > 0) {
      QueryObject o;
      while ((o = (QueryObject) offlineQueue.getQueue().peek()) != null) {
        if (o.getTimestamp().before(cal.getTime())) {
          offlineQueue.getQueue().poll();
        } else {
          break;
        }
      }
    }

    int size = this.offlineQueue.getQueue().size();
    if (this.queueMaxSize > 0 && size > this.queueMaxSize) {
      int i = 0;
      while (offlineQueue.getQueue().peek() != null && (size - this.queueMaxSize) >= i) {
        this.offlineQueue.getQueue().poll();
        i++;
      }
    }
  }

  private void mergeOfflineQueueWithLoader() {
    KuzzleQueue<QueryObject> additionalOfflineQueue = this.offlineQueueLoader.load();
    try {
      for (QueryObject additionalQuery : additionalOfflineQueue) {
        for (QueryObject offlineQuery : this.offlineQueue) {
          if (additionalQuery.getQuery() != null && additionalQuery.getQuery().has("requestId") && additionalQuery.getQuery().has("action") && additionalQuery.getQuery().has("controller")) {
            if (!offlineQuery.getQuery().getString("requestId").equals(additionalQuery.getQuery().getString("requestId"))) {
              this.offlineQueue.addToQueue(additionalOfflineQueue.dequeue());
            } else {
              additionalOfflineQueue.dequeue();
            }
          } else {
            throw new IllegalArgumentException("Invalid offline queue request. One or more missing properties: requestId, action, controller.");
          }
        }
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Play all queued requests, in order.
   */
  private void dequeue() {
    if (offlineQueueLoader != null) {
      this.mergeOfflineQueueWithLoader();
    }
    if (this.offlineQueue.getQueue().size() > 0) {
      try {
        QueryObject query = (QueryObject) this.offlineQueue.getQueue().poll();
        this.emitRequest(query.getQuery(), query.getCb());
        this.emitEvent(Event.offlineQueuePop, query);
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }
      Timer timer = new Timer(UUID.randomUUID().toString());
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          dequeue();
        }
      }, Math.max(0, this.replayInterval));
    } else {
      this.queuing = false;
    }
  }

  /**
   * Delete a suscription
   *
   * @param roomId - Subscription unique ID
   * @param id  - Room instance unique ID
   * @return this
   */
  protected Kuzzle deleteSubscription(final String roomId, final String id) {
    if (this.subscriptions.containsKey(roomId)) {
      this.subscriptions.get(roomId).remove(id);

      if (this.subscriptions.get(roomId).isEmpty()) {
        this.subscriptions.remove(roomId);
      }
    }

    return this;
  }

  /**
   * Gets subscriptions.
   *
   * @param roomId - Subscription unique ID
   * @return matching registered subscriptions
   */
  protected Map<String, Room> getSubscriptions(String roomId) {
    if (roomId != null && this.subscriptions.containsKey(roomId)) {
      return this.subscriptions.get(roomId);
    }

    return null;
  }

  /**
   * Getter for the pendingSubscriptions private property
   *
   * @return pendingSubscriptions property value
   */
  protected Map<String, Room> getPendingSubscriptions() {
    return this.subscriptions.get("pending");
  }

  /**
   * requestHistory getter
   *
   * @return Request history
   */
  protected Map<String, Date> getRequestHistory() {
    return requestHistory;
  }

  /**
   * Default index getter
   *
   * @return Default index value
   */
  public String getDefaultIndex() {
    return this.defaultIndex;
  }

  /**
   * Default index setter
   *
   * @param index - New default index name
   * @return this
   */
  public Kuzzle setDefaultIndex(@NonNull final String index) {
    if (index == null || index.isEmpty()) {
      throw new IllegalArgumentException("Kuzzle.setDefaultIndex: index required");
    }
    this.defaultIndex = index;
    return this;
  }

  /**
   * Set a new JWT and trigger the 'loginAttempt' event.
   *
   * @param jwt - New authentication JSON Web Token
   * @return this
   */
  public Kuzzle setJwtToken(final String jwt) {
    this.jwtToken = jwt;
    try {
      this.renewSubscriptions();
      this.emitEvent(Event.loginAttempt, new JSONObject().put("success", true));
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  /**
   * Unset the authentication token and cancel all subscriptions
   *
   * @return this
   */
  public Kuzzle unsetJwtToken() {
    this.jwtToken = null;

    for (Map<String, Room> roomSubscriptions : subscriptions.values()) {
      for (Room room : roomSubscriptions.values()) {
        room.unsubscribe();
      }
    }
    return this;
  }

  /**
   * Sets the authentication token from a Kuzzle response object
   *
   * @param response - A Kuzzle API response
   * @return this
   * @throws JSONException
   */
  public Kuzzle setJwtToken(@NonNull final JSONObject response) throws JSONException {
    JSONObject result;

    if (response == null) {
      throw new IllegalArgumentException("Cannot set token from a null Kuzzle response");
    }

    if (response.has("result") && (result = response.getJSONObject("result")).has("jwt")) {
      this.jwtToken = result.getString("jwt");
      this.renewSubscriptions();
      this.emitEvent(Event.loginAttempt, new JSONObject().put("success", true));
    } else {
      this.emitEvent(Event.loginAttempt, new JSONObject()
              .put("success", false)
              .put("error", "Cannot find a valid JWT token in the following object: " + response.toString())
      );
    }

    return this;
  }

  /**
   * Authentication token getter
   *
   * @return Current JWT
   */
  public String getJwtToken() {
    return this.jwtToken;
  }

  /**
   * queueTTL option getter
   *
   * @param newTTL - New queueTTL value
   * @return this
   */
  public Kuzzle setQueueTTL(int newTTL) {
    this.queueTTL = Math.max(0, newTTL);
    return this;
  }

  /**
   * queueTTL option getter
   *
   * @return queue ttl value
   */
  public int getQueueTTL() {
    return this.queueTTL;
  }

  /**
   * Global volatile data setter
   *
   * @param _volatile - New global volatile data content
   * @return this
   */
  public Kuzzle setVolatile(JSONObject _volatile) {
    this._volatile = _volatile;
    return this;
  }

  /**
   * Global volatile data getter
   *
   * @return Global volatile data
   */
  public JSONObject getVolatile() {
    return this._volatile;
  }


  /**
   * replayInterval option setter
   *
   * @param interval - New replayInterval value
   * @return this
   */
  public Kuzzle setReplayInterval(long interval) {
    this.replayInterval = Math.max(0, interval);
    return this;
  }

  /**
   * replayInterval option getter
   *
   * @return replayInterval value
   */
  public long getReplayInterval() {
    return this.replayInterval;
  }

  /**
   * reconnectionDelay option getter
   *
   * @return reconnectionDelay value
   */
  public long getReconnectionDelay() {
    return this.reconnectionDelay;
  }

  /**
   * Offline queue loader setter
   *
   * @param offlineQueueLoader - Offline queue loader function
   */
  public void setOfflineQueueLoader(OfflineQueueLoader offlineQueueLoader) {
    this.offlineQueueLoader = offlineQueueLoader;
  }

  /**
   * {@link #updateSelf(JSONObject, Options, ResponseListener)}
   */
  public Kuzzle updateSelf(final JSONObject content) {
    return updateSelf(content, null, null);
  }

  /**
   * {@link #updateSelf(JSONObject, Options, ResponseListener)}
   */
  public Kuzzle updateSelf(final JSONObject content, final Options options) {
    return updateSelf(content, options, null);
  }

  /**
   * {@link #updateSelf(JSONObject, Options, ResponseListener)}
   */
  public Kuzzle updateSelf(final JSONObject content, final ResponseListener listener) {
    return updateSelf(content, null, listener);
  }

  /**
   * Update the currently authenticated user informations
   *
   * @param content - Current user infos to update
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle updateSelf(final JSONObject content, final Options options, final ResponseListener<JSONObject> listener) {
    QueryArgs args = new QueryArgs();
    args.controller = "auth";
    args.action = "updateSelf";

    try {
      JSONObject query = new JSONObject().put("body", content);
      this.query(args, query, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener != null) {
            listener.onSuccess(response);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * Retrieves current user information
   *
   * @param listener - Response callback listener
   */
  public void whoAmI(@NonNull final ResponseListener<User> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.whoAmI: listener required");
    }

    try {
      QueryArgs args = new QueryArgs();
      args.controller = "auth";
      args.action = "getCurrentUser";
      JSONObject request = new JSONObject();

      this.query(args, request, null, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONObject result = response.getJSONObject("result");
            listener.onSuccess(new User(Kuzzle.this, result.getString("_id"), result.getJSONObject("_source"), result.getJSONObject("_meta")));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #refreshIndex(Options, ResponseListener)}
   */
  public Kuzzle refreshIndex() {
    return this.refreshIndex(null, null, null);
  }

  /**
   * {@link #refreshIndex(String, Options, ResponseListener)}
   */
  public Kuzzle refreshIndex(String index) {
    return this.refreshIndex(index, null, null);
  }

  /**
   * {@link #refreshIndex(String, Options, ResponseListener)}
   */
  public Kuzzle refreshIndex(String index, final ResponseListener<JSONObject> listener) {
    return this.refreshIndex(index, null, listener);
  }

  /**
   * {@link #refreshIndex(String, Options, ResponseListener)}
   */
  public Kuzzle refreshIndex(String index, final Options options) {
    return this.refreshIndex(index, options, null);
  }

  /**
   * {@link #refreshIndex(Options, ResponseListener)}
   */
  public Kuzzle refreshIndex(final Options options) {
    return this.refreshIndex(null, options, null);
  }

  /**
   * Forces the default data index to refresh on each modification
   *
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle refreshIndex(final Options options, final ResponseListener<JSONObject> listener) {
    return this.refreshIndex(null, options, listener);
  }

  /**
   * {@link #refreshIndex(Options, ResponseListener)}
   */
  public Kuzzle refreshIndex(final ResponseListener<JSONObject> listener) {
    return this.refreshIndex(null, null, listener);
  }

  /**
   * Forces the provided data index to refresh on each modification
   *
   * @param index - Data index name
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle refreshIndex(String index, final Options options, final ResponseListener<JSONObject> listener) {
    if (index == null) {
      if (this.defaultIndex == null) {
        throw new IllegalArgumentException("Kuzzle.refreshIndex: index required");
      } else {
        index = this.defaultIndex;
      }
    }

    QueryArgs args = new QueryArgs();
    args.index = index;
    args.controller = "index";
    args.action = "refresh";
    JSONObject request = new JSONObject();

    try {
      this.query(args, request, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener == null) {
            return;
          }

          try {
            JSONObject result = response.getJSONObject("result");

            listener.onSuccess(result);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener == null) {
            return;
          }

          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * {@link #getAutoRefresh(Options, ResponseListener)}
   */
  public void getAutoRefresh(@NonNull final ResponseListener<Boolean> listener) {
    this.getAutoRefresh(null, null, listener);
  }

  /**
   * {@link #getAutoRefresh(String, Options, ResponseListener)}
   */
  public void getAutoRefresh(String index, @NonNull final ResponseListener<Boolean> listener) {
    this.getAutoRefresh(index, null, listener);
  }

  /**
   * Gets the autoRefresh value for the default data index 
   *
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void getAutoRefresh(Options options, @NonNull final ResponseListener<Boolean> listener) {
    this.getAutoRefresh(null, options, listener);
  }

  /**
   * Gets the autoRefresh value for the provided data index name
   *
   * @param index - Data index name
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void getAutoRefresh(String index, final Options options, @NonNull final ResponseListener<Boolean> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.getAutoRefresh: listener required");
    }

    if (index == null) {
      if (this.defaultIndex == null) {
        throw new IllegalArgumentException("Kuzzle.getAutoRefresh: index required");
      } else {
        index = this.defaultIndex;
      }
    }

    QueryArgs args = new QueryArgs();
    args.index = index;
    args.controller = "index";
    args.action = "getAutoRefresh";
    JSONObject request = new JSONObject();

    try {
      this.query(args, request, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            boolean result = response.getBoolean("result");
            listener.onSuccess(result);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #setAutoRefresh(boolean, Options, ResponseListener)}
   */
  public Kuzzle setAutoRefresh(final boolean autoRefresh) {
    return this.setAutoRefresh(null, autoRefresh, null, null);
  }

  /**
   * {@link #setAutoRefresh(boolean, Options, ResponseListener)}
   */
  public Kuzzle setAutoRefresh(final boolean autoRefresh, final ResponseListener<Boolean> listener) {
    return this.setAutoRefresh(null, autoRefresh, null, listener);
  }

  /**
   * {@link #setAutoRefresh(String, boolean, Options, ResponseListener)}
   */
  public Kuzzle setAutoRefresh(String index, final boolean autoRefresh) {
    return this.setAutoRefresh(index, autoRefresh, null, null);
  }

  /**
   * {@link #setAutoRefresh(boolean, Options, ResponseListener)}
   */
  public Kuzzle setAutoRefresh(final boolean autoRefresh, final Options options) {
    return this.setAutoRefresh(null, autoRefresh, options, null);
  }

  /**
   * {@link #setAutoRefresh(String, boolean, Options, ResponseListener)}
   */
  public Kuzzle setAutoRefresh(String index, final boolean autoRefresh, final Options options) {
    return this.setAutoRefresh(index, autoRefresh, options, null);
  }

  /**
   * {@link #setAutoRefresh(String, boolean, Options, ResponseListener)}
   */
  public Kuzzle setAutoRefresh(String index, final boolean autoRefresh, final ResponseListener<Boolean> listener) {
    return this.setAutoRefresh(index, autoRefresh, null, listener);
  }

  /**
   * autoRefresh status setter for the default data index 
   *
   * @param autoRefresh - New autoRefresh property value
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle setAutoRefresh(final boolean autoRefresh, final Options options, final ResponseListener<Boolean> listener) {
    return this.setAutoRefresh(null, autoRefresh, options, listener);
  }

  /**
   * autorefresh status setter for the provided data index name
   *
   * @param index - Data index name
   * @param autoRefresh - New autoRefresh property value
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle setAutoRefresh(String index, final boolean autoRefresh, final Options options, final ResponseListener<Boolean> listener) {
    if (index == null) {
      if (this.defaultIndex == null) {
        throw new IllegalArgumentException("Kuzzle.setAutoRefresh: index required");
      } else {
        index = this.defaultIndex;
      }
    }

    QueryArgs args = new QueryArgs();
    args.index = index;
    args.controller = "index";
    args.action = "setAutoRefresh";
    JSONObject request;

    try {
      request = new JSONObject().put("body", new JSONObject().put("autoRefresh", autoRefresh));
      this.query(args, request, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          if (listener == null) {
            return;
          }

          try {
            boolean result = response.getBoolean("result");
            listener.onSuccess(result);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * {@link #getMyRights(Options, ResponseListener)}
   */
  public Kuzzle getMyRights(@NonNull final ResponseListener<JSONObject[]> listener) {
    return getMyRights(null, listener);
  }

  /**
   * Gets the rights array for the currently logged user.
   *
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle getMyRights(final Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Security.getMyRights: listener is mandatory.");
    }
    try {
      Kuzzle.this.query(buildQueryArgs("auth", "getMyRights"), new JSONObject(), options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONArray hits = response.getJSONObject("result").getJSONArray("hits");
            JSONObject[] rights = new JSONObject[hits.length()];

            for (int i = 0; i < hits.length(); i++) {
              rights[i] = hits.getJSONObject(i);
            }

            listener.onSuccess(rights);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  protected io.kuzzle.sdk.core.Kuzzle.QueryArgs buildQueryArgs(final String controller, @NonNull String action) {
    io.kuzzle.sdk.core.Kuzzle.QueryArgs args = new io.kuzzle.sdk.core.Kuzzle.QueryArgs();
    args.action = action;
    args.controller = "security";
    if (controller != null) {
      args.controller = controller;
    }
    args.action = action;
    return args;
  }

  /**
   * Helper function meant to easily build the first Kuzzle.query() argument
   *
   * @param action - API controller action name
   * @return JSONObject - Kuzzle.query() 1st argument object
   * @throws JSONException
   */
  protected io.kuzzle.sdk.core.Kuzzle.QueryArgs buildQueryArgs(@NonNull final String action) throws JSONException {
    return buildQueryArgs(null, action);
  }

  /**
   * Invokes a query listener with a custom error message and status
   *
   * @param listener - Response callback listener
   * @param query    - discarded query
   * @throws  JSONException
   */
  protected void discardRequest(final OnQueryDoneListener listener, JSONObject query) throws JSONException {
    if (listener != null) {
      JSONObject err = new JSONObject()
              .put("status", 400)
              .put("message", "Unable to execute request: not connected to a Kuzzle server.\nDiscarded request: " + query.toString());

      listener.onError(err);
    }
  }

  /**
   * {@link #createMyCredentials(String, JSONObject, Options, ResponseListener)}
   */
  public Kuzzle createMyCredentials(@NonNull final String strategy, final JSONObject credentials) {
    return createMyCredentials(strategy, credentials, null, null);
  }

  /**
   * {@link #createMyCredentials(String, JSONObject, Options, ResponseListener)}
   */
  public Kuzzle createMyCredentials(@NonNull final String strategy, final JSONObject credentials, final Options options) {
    return createMyCredentials(strategy, credentials, options, null);
  }

  /**
   * {@link #createMyCredentials(String, JSONObject, Options, ResponseListener)}
   */
  public Kuzzle createMyCredentials(@NonNull final String strategy, final JSONObject credentials, final ResponseListener<JSONObject> listener) {
    return createMyCredentials(strategy, credentials, null, listener);
  }

  /**
   * Create credentials of the specified strategy for the current user.
   *
   * @param strategy - impacted strategy name
   * @param credentials - credentials to create
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle createMyCredentials(@NonNull final String strategy, final JSONObject credentials, final Options options, final ResponseListener<JSONObject> listener) {
    try {
      JSONObject body = new JSONObject()
              .put("strategy", strategy)
              .put("body", credentials);
      Kuzzle.this.query(buildQueryArgs("auth", "createMyCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            if (listener != null) {
              listener.onSuccess(response.getJSONObject("result"));
            }
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * {@link #deleteMyCredentials(String, Options, ResponseListener)}
   */
  public Kuzzle deleteMyCredentials(@NonNull final String strategy) {
    return deleteMyCredentials(strategy, null, null);
  }

  /**
   * {@link #deleteMyCredentials(String, Options, ResponseListener)}
   */
  public Kuzzle deleteMyCredentials(@NonNull final String strategy, final Options options) {
    return deleteMyCredentials(strategy, options, null);
  }

  /**
   * {@link #deleteMyCredentials(String, Options, ResponseListener)}
   */
  public Kuzzle deleteMyCredentials(@NonNull final String strategy, final ResponseListener<JSONObject> listener) {
    return deleteMyCredentials(strategy, null, listener);
  }

  /**
   * Delete credentials of the specified strategy for the current user.
   *
   * @param strategy- Name of the strategy to remove
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle deleteMyCredentials(@NonNull final String strategy, final Options options, final ResponseListener<JSONObject> listener) {
    try {
      JSONObject body = new JSONObject()
              .put("strategy", strategy);
      Kuzzle.this.query(buildQueryArgs("auth", "deleteMyCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            if (listener != null) {
              listener.onSuccess(response.getJSONObject("result"));
            }
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * {@link #getMyCredentials(String, Options, ResponseListener)}
   */
  public void getMyCredentials(@NonNull final String strategy, @NonNull final ResponseListener<JSONObject> listener) {
    getMyCredentials(strategy, null, listener);
  }

  /**
   * Get credential information of the specified strategy for the current user.
   *
   * @param strategy - Strategy name to get
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void getMyCredentials(@NonNull final String strategy, final Options options, @NonNull final ResponseListener<JSONObject> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.getMyCredentials: listener is mandatory");
    }
    try {
      JSONObject body = new JSONObject()
              .put("strategy", strategy);
      Kuzzle.this.query(buildQueryArgs("auth", "getMyCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result"));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #updateMyCredentials(String, JSONObject, Options, ResponseListener)}
   */
  public Kuzzle updateMyCredentials(@NonNull final String strategy, final JSONObject credentials) {
    return updateMyCredentials(strategy, credentials, null, null);
  }

  /**
   * {@link #updateMyCredentials(String, JSONObject, Options, ResponseListener)}
   */
  public Kuzzle updateMyCredentials(@NonNull final String strategy, final JSONObject credentials, final Options options) {
    return updateMyCredentials(strategy, credentials, options, null);
  }

  /**
   * {@link #updateMyCredentials(String, JSONObject, Options, ResponseListener)}
   */
  public Kuzzle updateMyCredentials(@NonNull final String strategy, final JSONObject credentials, final ResponseListener<JSONObject> listener) {
    return updateMyCredentials(strategy, credentials, null, listener);
  }

  /**
   * Update credentials of the specified strategy for the current user.
   *
   * @param strategy - Strategy name to update
   * @param credentials - Updated credentials content
   * @param options - Request options
   * @param listener - Response callback listener
   * @return this
   */
  public Kuzzle updateMyCredentials(@NonNull final String strategy, final JSONObject credentials, final Options options, final ResponseListener<JSONObject> listener) {
    try {
      JSONObject body = new JSONObject()
              .put("strategy", strategy)
              .put("body", credentials);
      Kuzzle.this.query(buildQueryArgs("auth", "updateMyCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            if (listener != null) {
              listener.onSuccess(response.getJSONObject("result"));
            }
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          if (listener != null) {
            listener.onError(error);
          }
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

  /**
   * {@link #validateMyCredentials(String, JSONObject, Options, ResponseListener)}
   */
  public void validateMyCredentials(@NonNull final String strategy, final JSONObject credentials, @NonNull final ResponseListener<Boolean> listener) {
    validateMyCredentials(strategy, credentials, null, listener);
  }

  /**
   * Validate credentials of the specified strategy for the current user.
   *
   * @param strategy - Strategy name to validate
   * @param credentials - Credentials content
   * @param options - Request options
   * @param listener - Response callback listener
   */
  public void validateMyCredentials(@NonNull final String strategy, final JSONObject credentials, final Options options, @NonNull final ResponseListener<Boolean> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Kuzzle.validateMyCredentials: listener is mandatory");
    }
    try {
      JSONObject body = new JSONObject()
              .put("strategy", strategy)
              .put("body", credentials);
      Kuzzle.this.query(buildQueryArgs("auth", "validateMyCredentials"), body, options, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getBoolean("result"));
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      });
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }
}
