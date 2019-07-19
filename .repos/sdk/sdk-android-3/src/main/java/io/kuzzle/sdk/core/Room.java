package io.kuzzle.sdk.core;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.kuzzle.sdk.enums.Event;
import io.kuzzle.sdk.enums.Scope;
import io.kuzzle.sdk.enums.State;
import io.kuzzle.sdk.enums.Users;
import io.kuzzle.sdk.listeners.EventListener;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.SubscribeListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.responses.NotificationResponse;
import io.kuzzle.sdk.state.States;

public class Room {

  private String id = UUID.randomUUID().toString();
  protected String collection;
  protected Collection dataCollection;
  protected JSONObject filters = new JSONObject();
  protected JSONObject headers;
  protected JSONObject _volatile;
  protected boolean subscribeToSelf;
  protected String roomId;
  protected Kuzzle kuzzle;
  protected String channel;
  protected Scope scope;
  protected State state;
  protected Users users;
  protected ResponseListener<NotificationResponse> listener;

  // Used to avoid subscription renewals to trigger multiple times because of
  // multiple but similar events
  private long lastRenewal = 0;
  private long renewalDelay = 500;

  // Used to delay method calls when subscription is in progress
  protected boolean subscribing = false;
  private ArrayList<Runnable> queue = new ArrayList<>();
  private SubscribeListener doneListener;

  /**
   * Constructor
   *
   * @param kuzzleDataCollection Data collection to link
   */
  public Room(@NonNull final Collection kuzzleDataCollection) {
    this(kuzzleDataCollection, null);
  }

  /**
   * This object is the result of a subscription request, allowing to manipulate the subscription itself.
   * In Kuzzle, you don't exactly subscribe to a room or a topic but, instead, you subscribe to documents.
   * What it means is that, to subscribe, you provide to Kuzzle a set of matching filters.
   * Once you have subscribed, if a pub/sub message is published matching your filters, or if a matching stored
   * document change (because it is created, updated or deleted), then you'll receive a notification about it.
   *
   * @param kuzzleDataCollection Data collection to link
   * @param options              Subscription options
   */
  public Room(@NonNull final Collection kuzzleDataCollection, final RoomOptions options) {
    RoomOptions opts = options != null ? options : new RoomOptions();

    if (kuzzleDataCollection == null) {
      throw new IllegalArgumentException("Room: missing dataCollection");
    }
    kuzzleDataCollection.getKuzzle().isValid();

    this.dataCollection = kuzzleDataCollection;
    this.kuzzle = kuzzleDataCollection.getKuzzle();
    this.collection = kuzzleDataCollection.getCollection();

    try {
      this.headers = new JSONObject(kuzzleDataCollection.getHeaders().toString());
    }
    catch (JSONException e) {
      throw new RuntimeException(e);
    }

    this.subscribeToSelf = opts.isSubscribeToSelf();
    this._volatile = opts.getVolatile();
    this.scope = opts.getScope();
    this.state = opts.getState();
    this.users = opts.getUsers();
  }

  /**
   * Returns the number of other subscriptions on that room.
   *
   * @param listener Response callback listener
   */
  public void count(@NonNull final ResponseListener<Integer> listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Room.count: a callback listener is required");
    }

    // Delays this call until after the subscription is finished
    if (!this.isReady()) {
      this.queue.add(new Runnable() {
        @Override
        public void run() {
          Room.this.count(listener);
        }
      });
      return;
    }

    if (this.roomId == null) {
      throw new IllegalStateException("Room.count: cannot count subscriptions on an inactive room");
    }

    try {
      JSONObject data = new JSONObject().put("body", new JSONObject().put("roomId", this.roomId));
      this.kuzzle.addHeaders(data, this.headers);

      this.kuzzle.query(this.dataCollection.makeQueryArgs("realtime", "count"), data, new OnQueryDoneListener() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result").getInt("count"));
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
   * Call after renew.
   *
   * @param args the args
   */
  protected void callAfterRenew(final Object args) {
    if (args == null) {
      throw new IllegalArgumentException("Room.renew: response required");
    }

    try {
      String requestId = ((JSONObject) args).has("requestId") ? ((JSONObject) args).getString("requestId") : null;

      if (((JSONObject) args).getString("type").equals("TokenExpired")) {
        Room.this.kuzzle.jwtToken = null;
        Room.this.kuzzle.emitEvent(Event.tokenExpired);
      }

      if (requestId != null && Room.this.kuzzle.getRequestHistory().containsKey(requestId)) {
        if (Room.this.subscribeToSelf) {
          listener.onSuccess(new NotificationResponse(kuzzle, (JSONObject) args));
        }
        Room.this.kuzzle.getRequestHistory().remove(requestId);
      } else {
        listener.onSuccess(new NotificationResponse(kuzzle, (JSONObject) args));
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@link #renew(JSONObject, ResponseListener, SubscribeListener)}
   */
  public Room renew(@NonNull final ResponseListener<NotificationResponse> listener) {
    return this.renew(null, listener, null);
  }

  /**
   * {@link #renew(JSONObject, ResponseListener, SubscribeListener)}
   */
  public Room renew(@NonNull final ResponseListener<NotificationResponse> listener, final SubscribeListener subscribeResponseListener) {
    return this.renew(null, listener, subscribeResponseListener);
  }

  /**
   * Renew the subscription. Force a resubscription using the same filters 
   * if no new ones are provided.
   * Unsubscribes first if this Room was already listening to events.
   *
   * @param filters  Subscription filters, using Kuzzle DSL
   * @param listener Response callback listener
   * @return this
   */
  public Room renew(final JSONObject filters, @NonNull final ResponseListener<NotificationResponse> listener, final SubscribeListener subscribeResponseListener) {
    long now = System.currentTimeMillis();

    if (listener == null) {
      throw new IllegalArgumentException("Room.renew: a callback listener is required");
    }

    // Skip subscription renewal if another one was performed just a moment before
    if (this.lastRenewal > 0 && (now - this.lastRenewal) <= this.renewalDelay) {
      return this;
    }

    if (filters != null) {
      this.filters = filters;
    }

    /*
      If not yet connected, registers itself into the subscriptions list and wait for the
      main Kuzzle object to renew subscriptions once online
     */
    if (this.kuzzle.state != States.CONNECTED) {
      this.listener = listener;
      this.doneListener = subscribeResponseListener;
      this.kuzzle.addPendingSubscription(this.id, this);
      return this;
    }

    if (this.subscribing) {
      this.queue.add(new Runnable() {
        @Override
        public void run() {
          Room.this.renew(filters, listener, subscribeResponseListener);
        }
      });

      return this;
    }

    this.unsubscribe();
    this.roomId = null;
    this.subscribing = true;
    this.listener = listener;
    this.doneListener = subscribeResponseListener;
    this.kuzzle.addPendingSubscription(this.id, this);

    try {
      final Options options = new Options();
      final JSONObject
              subscribeQuery = new JSONObject()
              .put("body", this.filters)
              .put("scope", this.scope.toString().toLowerCase())
              .put("state", this.state.toString().toLowerCase())
              .put("users", this.users.toString().toLowerCase());

      options.setVolatile(this._volatile);
      this.kuzzle.addHeaders(subscribeQuery, this.headers);

      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Room.this.kuzzle.query(Room.this.dataCollection.makeQueryArgs("realtime", "subscribe"), subscribeQuery, options, new OnQueryDoneListener() {
              @Override
              public void onSuccess(JSONObject args) {
                try {
                  Room.this.kuzzle.deletePendingSubscription(Room.this.id);
                  Room.this.subscribing = false;
                  Room.this.lastRenewal = System.currentTimeMillis();

                  JSONObject result = args.getJSONObject("result");
                  Room.this.channel = result.getString("channel");
                  Room.this.roomId = result.getString("roomId");
                  if (subscribeResponseListener != null) {
                    subscribeResponseListener.done(null, Room.this);
                  }
                } catch (JSONException e) {
                  throw new RuntimeException(e);
                }

                Room.this.kuzzle.addSubscription(Room.this.roomId, Room.this.id, Room.this);

                Room.this.kuzzle.addRoom(Room.this.channel,  new EventListener() {
                  @Override
                  public void trigger(final Object... args) {
                    callAfterRenew(args[0]);
                  }
                });

                Room.this.dequeue();
              }

              @Override
              public void onError(JSONObject arg) {
                Room.this.subscribing = false;
                Room.this.queue.clear();
                if (subscribeResponseListener != null) {
                  subscribeResponseListener.done(arg, null);
                }
              }
            });
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }
      }).start();

    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  /**
   * Unsubscribes from Kuzzle.
   * Stop listening immediately. If there is no listener left on that room,
   * sends an unsubscribe request to Kuzzle, once
   * pending subscriptions reaches 0, and only if there is still no listener on that room.
   * We wait for pending subscriptions to finish to avoid unsubscribing while
   * another subscription on that room is
   *
   * @return this
   */
  public Room unsubscribe() {
    if (!this.isReady()) {
      this.queue.add(new Runnable() {
        @Override
        public void run() {
          Room.this.unsubscribe();
        }
      });
      return this;
    }

    if (this.roomId == null) {
      return this;
    }

    try {
      final JSONObject data = new JSONObject().put("body", new JSONObject().put("roomId", this.roomId));
      this.kuzzle.addHeaders(data, this.headers);

      this.kuzzle.removeRoom(Room.this.channel);
      this.kuzzle.deleteSubscription(this.roomId, this.id);

      if (this.kuzzle.getSubscriptions(this.roomId) == null) {
        final String roomId = this.roomId;
        if (this.kuzzle.getPendingSubscriptions().isEmpty()) {
          this.kuzzle.query(this.dataCollection.makeQueryArgs("realtime", "unsubscribe"), data);
        } else {
          final Timer timer = new Timer(UUID.randomUUID().toString());
          unsubscribeTask(timer, roomId, data).run();
        }
      }

      this.roomId = null;
    }
    catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  /**
   * Unsubscribe task timer task.
   *
   * @param timer  the timer
   * @param roomId the room id
   * @param data   the data
   * @return the timer task
   */
  protected TimerTask unsubscribeTask(final Timer timer, final String roomId, final JSONObject data) {
    return new TimerTask() {
      @Override
      public void run() {
        try {
          if (Room.this.kuzzle.getPendingSubscriptions().isEmpty()) {
            if (Room.this.kuzzle.getSubscriptions(roomId) == null) {
              Room.this.kuzzle.query(Room.this.dataCollection.makeQueryArgs("realtime", "unsubscribe"), data);
            }
          } else {
            timer.schedule(unsubscribeTask(timer, roomId, data), 100);
          }
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }

  /**
   * Linked data collection name getter
   *
   * @return linked data collection name
   */
  public String getCollection() {
    return collection;
  }

  /**
   * Subscription filters getter
   *
   * @return subscription filters
   */
  public JSONObject getFilters() {
    return filters;
  }

  /**
   * Subscription filters setter.
   * renew must be called for this to take effect
   *
   * @param filters New subscription filters
   * @return this
   */
  public Room setFilters(final JSONObject filters) {
    this.filters = filters;
    return this;
  }

  /**
   * headers property getters
   *
   * @return headers value
   */
  public JSONObject getHeaders() {
    return this.headers;
  }

  /**
   * {@link #setHeaders(JSONObject, boolean)}
   */
  public Room setHeaders(final JSONObject content) {
    return this.setHeaders(content, false);
  }

  /**
   * Subscription headers setter
   *
   * @param content - new headers content
   * @param replace - default: false = append the content, true = replace
   * @return this
   */
  public Room setHeaders(final JSONObject content, final boolean replace) {
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
   * Gets volatile data.
   *
   * @return the volatile property
   */
  public JSONObject getVolatile() {
    return _volatile;
  }

  /**
   * Sets volatile metadata.
   *
   * @param _volatile New volatile data value
   * @return this
   */
  public Room setVolatile(final JSONObject _volatile) {
    this._volatile = _volatile;
    return this;
  }

  /**
   * subscribeToSelf property getter
   *
   * @return subscribeToSelf property value
   */
  public boolean isSubscribeToSelf() {
    return subscribeToSelf;
  }

  /**
   * subscribeToSelf property setter
   *
   * @param subscribeToSelf New subscribeToSelf value
   * @return this
   */
  public Room setSubscribeToSelf(final boolean subscribeToSelf) {
    this.subscribeToSelf = subscribeToSelf;
    return this;
  }

  /**
   * roomId property getter
   *
   * @return roomId property value
   */
  public String getRoomId() {
    return this.roomId;
  }

  /**
   * listener property getter
   *
   * @return listener property value
   */
  public ResponseListener<NotificationResponse> getListener() {
    return this.listener;
  }

  /**
   * subscribeListener property getter
   * @return subscribeListener property value
   */
  public SubscribeListener getSubscribeListener() {
    return doneListener;
  }

  /**
   * Runs all queued methods called while subscription was in progress
   */
  protected void dequeue() {
    if (this.queue.size() > 0) {
      ExecutorService threadPool = Executors.newSingleThreadExecutor();

      for(Runnable r: this.queue) {
        threadPool.execute(r);
      }

      threadPool.shutdown();

      try {
        threadPool.awaitTermination(1, TimeUnit.SECONDS);
      }
      catch (InterruptedException e) {
        // do nothing
      }
      finally {
        this.queue.clear();
      }
    }
  }

  private boolean isReady() {
    return this.kuzzle.state == States.CONNECTED && !this.subscribing;
  }
}
