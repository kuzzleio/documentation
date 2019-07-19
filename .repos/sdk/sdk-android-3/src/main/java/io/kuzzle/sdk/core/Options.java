package io.kuzzle.sdk.core;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.kuzzle.sdk.enums.CollectionType;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.responses.SearchResult;

public class Options {
  // Default values
  private boolean autoQueue = false;
  private boolean autoReconnect = true;
  private boolean autoReplay = false;
  private boolean autoResubscribe = true;
  private JSONObject headers = new JSONObject();
  private JSONObject _volatile = new JSONObject();
  private int queueMaxSize = 500;
  private int queueTTL = 120000;
  private long reconnectionDelay = 1000;
  private String ifExist = "error";
  private Mode connect = Mode.AUTO;
  private Mode offlineMode = Mode.MANUAL;
  private int replayInterval = 10;
  private boolean queuable = true;
  private String defaultIndex = null;
  private boolean replaceIfExist = false;
  private String refresh = null;
  private Long from = null;
  private Long size = null;
  private Integer port = 7512;
  private String scroll = null;

  private boolean ssl = false;
  private SearchResult previous = null;
  private String scrollId = null;
  private int retryOnConflict = 0;

  // MemoryStorage specific options
  private Long start = null;
  private Long end = null;
  private String unit = null;
  private boolean withcoord = false;
  private boolean withdist = false;
  private Long count = null;
  private String sort = null;
  private String match = null;
  private Long ex = null;
  private boolean nx = false;
  private Long px = null;
  private boolean xx = false;
  private boolean alpha = false;
  private String by = null;
  private String direction = null;
  private String[] get = null;
  private Integer[] limit = null;
  private boolean ch = false;
  private boolean incr = false;
  private String aggregate = null;
  private Integer[] weights = null;

  // Used for getting collections
  private CollectionType collectionType = CollectionType.ALL;

  public Options() {
    // Default constructor
  }

  public Options(@NonNull Options originalOptions) throws JSONException {
    this.autoQueue = originalOptions.autoQueue;
    this.autoReconnect = originalOptions.autoReconnect;
    this.autoResubscribe = originalOptions.autoResubscribe;
    this.headers = new JSONObject(originalOptions.headers.toString());
    this._volatile = new JSONObject(originalOptions._volatile.toString());
    this.queueMaxSize = originalOptions.queueMaxSize;
    this.queueTTL = originalOptions.queueTTL;
    this.reconnectionDelay = originalOptions.reconnectionDelay;
    this.ifExist = originalOptions.ifExist;
    this.connect = originalOptions.connect;
    this.offlineMode = originalOptions.offlineMode;
    this.replayInterval = originalOptions.replayInterval;
    this.queuable = originalOptions.queuable;
    this.defaultIndex = originalOptions.defaultIndex;
    this.replaceIfExist = originalOptions.replaceIfExist;
    this.refresh = originalOptions.refresh;
    this.from = originalOptions.from;
    this.size = originalOptions.size;
    this.port = originalOptions.port;
    this.scroll = originalOptions.scroll;
    this.previous = originalOptions.previous;
    this.scrollId = originalOptions.scrollId;
  }

  /**
   * autoReconnect option getter
   *
   * @return isAutoReconnection option value
   */
  public boolean isAutoReconnect() {
    return autoReconnect;
  }

  /**
   * autoReconnect option setter
   *
   * @param autoReconnect New autoReconnect option value
   * @return this
   */
  public Options setAutoReconnect(boolean autoReconnect) {
    this.autoReconnect = autoReconnect;
    return this;
  }

  /**
   * headers getter
   *
   * @return headers option value
   */
  public JSONObject getHeaders() {
    return headers;
  }

  /**
   * headers setter
   *
   * @param headers New headers value
   * @return this
   */
  public Options setHeaders(JSONObject headers) {
    this.headers = headers;
    return this;
  }

  /**
   * exists option getter
   *
   * @return exists option value
   */
  public String getIfExist() {
    return ifExist;
  }

  /**
   * exists option setter
   *
   * @param value new exists option value
   * @return this
   */
  public Options setIfExist(String value) {
    if (value != "error" && value != "replace") {
      throw new IllegalArgumentException("Invalid value for option 'ifExists': " + value);
    }

    this.ifExist = value;
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
   * Sets volatile data.
   *
   * @param _volatile new volatile data value
   * @return this
   */
  public Options setVolatile(JSONObject _volatile) {
    this._volatile = _volatile;
    return this;
  }

  /**
   * Gets connect property value
   *
   * @return the connect property value
   */
  public Mode getConnect() {
    return connect;
  }

  /**
   * Sets connect property value
   *
   * @param connect New connect property value
   * @return this
   */
  public Options setConnect(Mode connect) {
    this.connect = connect;
    return this;
  }

  /**
   * reconnectionDelay property getter
   *
   * @return the reconnectionDelay property value
   */
  public long getReconnectionDelay() {
    return reconnectionDelay;
  }

  /**
   * reconnectionDelay property setter
   *
   * @param reconnectionDelay New reconnectionDelay property value
   * @return this
   */
  public Options setReconnectionDelay(long reconnectionDelay) {
    this.reconnectionDelay = reconnectionDelay;
    return this;
  }

  /**
   * offlineMode property getter
   *
   * @return the offlineMode option value
   */
  public Mode getOfflineMode() {
    return offlineMode;
  }

  /**
   * offlineMode property setter
   *
   * @param offlineMode New offlineMode value
   * @return this
   */
  public Options setOfflineMode(Mode offlineMode) {
    this.offlineMode = offlineMode;
    return this;
  }

  /**
   * queueTTL property getter
   *
   * @return queueTTL property value
   */
  public int getQueueTTL() {
    return queueTTL;
  }

  /**
   * queueTTL property setter
   *
   * @param queueTTL New queueTTL value
   * @return this
   */
  public Options setQueueTTL(int queueTTL) {
    this.queueTTL = queueTTL;
    return this;
  }

  /**
   * autoReplay property getter
   * 
   * @return autoReplay property value
   */
  public boolean isAutoReplay() {
    return autoReplay;
  }

  /**
   * autoReplay property setter
   *
   * @param autoReplay New autoReplay value
   * @return this
   */
  public Options setAutoReplay(boolean autoReplay) {
    this.autoReplay = autoReplay;
    return this;
  }

  /**
   * queuable property getter
   *
   * @return queuable property value
   */
  public boolean isQueuable() {
    return queuable;
  }

  /**
   * queuable property setter
   *
   * @param queuable New queuable value
   * @return this
   */
  public Options setQueuable(boolean queuable) {
    this.queuable = queuable;
    return this;
  }

  /**
   * queueMaxSize property getter
   *
   * @return queueMaxSize value
   */
  public int getQueueMaxSize() {
    return queueMaxSize;
  }

  /**
   * queueMaxSize property setter
   *
   * @param queueMaxSize New queueMaxSize value
   * @return this
   */
  public Options setQueueMaxSize(int queueMaxSize) {
    this.queueMaxSize = queueMaxSize;
    return this;
  }

  /**
   * replayInterval property getter
   *
   * @return replayInterval property value
   */
  public int getReplayInterval() {
    return replayInterval;
  }

  /**
   * replayInterval property setter
   *
   * @param replayInterval New replayInterval value
   * @return this
   */
  public Options setReplayInterval(int replayInterval) {
    this.replayInterval = replayInterval;
    return this;
  }

  /**
   * autoResubscribe property getter
   *
   * @return autoResubscribe property value
   */
  public boolean isAutoResubscribe() {
    return autoResubscribe;
  }

  /**
   * autoResubscribe property setter
   *
   * @param autoResubscribe New autoResubscribe value
   * @return this
   */
  public Options setAutoResubscribe(boolean autoResubscribe) {
    this.autoResubscribe = autoResubscribe;
    return this;
  }

  /**
   * collectionType property getter
   *
   * @return collectionType property value
   */
  public CollectionType getCollectionType() {
    return collectionType;
  }

  /**
   * collectionType property setter
   *
   * @param type New collectionType value
   * @return this
   */
  public Options setCollectionType(CollectionType type) {
    this.collectionType = type;
    return this;
  }

  /**
   * defaultIndex property setter
   *
   * @param index New defaultIndex value
   * @return this
   */
  public Options setDefaultIndex(final String index) {
    this.defaultIndex = index;
    return this;
  }

  /**
   * defaultIndex property getter
   *
   * @return defaultIndex property value
   */
  public String getDefaultIndex() {
    return this.defaultIndex;
  }

  /**
   * autoQueue property setter
   *
   * @param autoQueue New autoQueue value
   * @return this
   */
  public Options setAutoQueue(boolean autoQueue) {
    this.autoQueue = autoQueue;
    return this;
  }

  /**
   * autoQueue property getter
   *
   * @return autoQueue property value
   */
  public boolean isAutoQueue() {
    return this.autoQueue;
  }

  /**
   * replaceIfExist property setter
   *
   * @param replace New replaceIfExist value
   * @return this
   */
  public Options setReplaceIfExist(boolean replace) {
    this.replaceIfExist = replace;
    return this;
  }

  /**
   * replaceIfExist property getter
   *
   * @return replaceIfExist property value
   */
  public boolean isReplaceIfExist() {
    return this.replaceIfExist;
  }

  /**
   * from property getter
   * @return from property value
   */
  public Long getFrom() {
    return from;
  }

  /**
   * from property setter
   * @param  from New from value
   * @return this
   */
  public Options setFrom(Long from) {
    this.from = from;
    return this;
  }

  /**
   * size property getter
   * @return size property value
   */
  public Long getSize() {
    return size;
  }

  /**
   * size property setter
   * @param  size New size value
   * @return this
   */
  public Options setSize(Long size) {
    this.size = size;
    return this;
  }

  /**
   * port property setter
   * @param  port New port value
   * @return this
   */
  public Options setPort(Integer port) {
    this.port = port;
    return this;
  }

  /**
   * port property getter
   * @return port property value
   */
  public Integer getPort() {
    return this.port;
  }

  /**
   * ssl property getter
   * @return ssl property value
   */
  public boolean isSsl() {
    return ssl;
  }

  /**
   * ssl property setter
   * @param  ssl New port value
   * @return this
   */
  public void setSsl(boolean ssl) {
    this.ssl = ssl;
  }

  /**
   * refresh property getter
   * @return refresh property value
   */
  public String getRefresh() {
    return refresh;
  }

  /**
   * refresh property setter
   * @param  refresh New refresh property value
   * @return         [description]
   */
  public Options setRefresh(String refresh) {
    this.refresh = refresh;
    return this;
  }

  /**
   * scroll property getter
   * @return scroll property value
   */
  public String getScroll() {
    return scroll;
  }

  /**
   * scroll property setter
   * @param  scroll New scroll value
   * @return this
   */
  public Options setScroll(String scroll) {
    this.scroll = scroll;
    return this;
  }

  /**
   * previous property getter
   * @return previous property value
   */
  public SearchResult getPrevious() {
    return previous;
  }

  /**
   * previous property setter
   * @param  previous New previous value
   * @return this
   */
  public Options setPrevious(SearchResult previous) {
    this.previous = previous;
    return this;
  }

  /**
   * scrollId property getter
   * @return scrollId property value
   */
  public String getScrollId() {
    return scrollId;
  }

  /**
   * scrollId property setter
   * @param  scrollId New scrollId value
   * @return this
   */
  public Options setScrollId(String scrollId) {
    this.scrollId = scrollId;
    return this;
  }

  /**
   * retryOnConflict property getter
   * @return retryOnConflict property value
   */
  public int getRetryOnConflict() {
    return retryOnConflict;
  }

  /**
   * retryOnConflict property setter
   * @param  retryOnConflict New retryOnConflict value
   * @return this
   */
  public Options setRetryOnConflict(int retryOnConflict) {
    if (retryOnConflict < 0) {
      throw new IllegalArgumentException("Invalid value for the retryOnConflict option (positive or null integer allowed)");
    }

    this.retryOnConflict = retryOnConflict;
    return this;
  }

  /**
   * start property getter
   * @return start property value
   */
  public Long getStart() {
    return start;
  }

  /**
   * start property setter
   * @param  start New start value
   * @return this
   */
  public Options setStart(Long start) {
    this.start = start;
    return this;
  }

  /**
   * end property getter
   * @return end property value
   */
  public Long getEnd() {
    return end;
  }

  /**
   * end property setter
   * @param  end New end value
   * @return this
   */
  public Options setEnd(Long end) {
    this.end = end;
    return this;
  }

  /**
   * unit property getter
   * @return unit property value
   */
  public String getUnit() {
    return unit;
  }

  /**
   * unit property setter
   * @param  unit New unit value
   * @return this
   */
  public Options setUnit(String unit) {
    this.unit = unit;
    return this;
  }

  /**
   * withcoord property getter
   * @return withcoord property value
   */
  public boolean getWithcoord() {
    return withcoord;
  }

  /**
   * withcoord property setter
   * @param  withcoord New withcoord value
   * @return this
   */
  public Options setWithcoord(boolean withcoord) {
    this.withcoord = withcoord;
    return this;
  }

  /**
   * withdist property getter
   * @return withdist property value
   */
  public boolean getWithdist() {
    return withdist;
  }

  /**
   * withdist property setter
   * @param  withdist New withdist value
   * @return this
   */
  public Options setWithdist(boolean withdist) {
    this.withdist = withdist;
    return this;
  }

  /**
   * count property getter
   * @return count property value
   */
  public Long getCount() {
    return count;
  }

  /**
   * count property setter
   * @param  count New count value
   * @return this
   */
  public Options setCount(Long count) {
    this.count = count;
    return this;
  }

  /**
   * sort property getter
   * @return sort property value
   */
  public String getSort() {
    return sort;
  }

  /**
   * sort property setter
   * @param  sort New sort value
   * @return this
   */
  public Options setSort(String sort) {
    this.sort = sort;
    return this;
  }

  /**
   * match property getter
   * @return match property value
   */
  public String getMatch() {
    return match;
  }

  /**
   * match property setter
   * @param  match New match value
   * @return this
   */
  public Options setMatch(String match) {
    this.match = match;
    return this;
  }

  /**
   * ex property getter
   * @return ex property value
   */
  public Long getEx() {
    return ex;
  }

  /**
   * ex property setter
   * @param  ex New ex value
   * @return this
   */
  public Options setEx(Long ex) {
    this.ex = ex;
    return this;
  }

  /**
   * nx property getter
   * @return nx property value
   */
  public boolean getNx() {
    return nx;
  }

  /**
   * nx property setter
   * @param  nx New nx value
   * @return this
   */
  public Options setNx(boolean nx) {
    this.nx = nx;
    return this;
  }

  /**
   * px property getter
   * @return px property value
   */
  public Long getPx() {
    return px;
  }

  /**
   * px property setter
   * @param  px New px value
   * @return this
   */
  public Options setPx(Long px) {
    this.px = px;
    return this;
  }

  /**
   * xx property getter
   * @return xx property value
   */
  public boolean getXx() {
    return xx;
  }

  /**
   * xx property setter
   * @param  xx New xx value
   * @return this
   */
  public Options setXx(boolean xx) {
    this.xx = xx;
    return this;
  }

  /**
   * alpha property getter
   * @return alpha property value
   */
  public boolean getAlpha() {
    return alpha;
  }

  /**
   * alpha property setter
   * @param  alpha New alpha value
   * @return this
   */
  public Options setAlpha(boolean alpha) {
    this.alpha = alpha;
    return this;
  }

  /**
   * by property getter
   * @return by property value
   */
  public String getBy() {
    return by;
  }

  /**
   * by property setter
   * @param  by New by value
   * @return this
   */
  public Options setBy(String by) {
    this.by = by;
    return this;
  }

  /**
   * direction property getter
   * @return direction property value
   */
  public String getDirection() {
    return direction;
  }

  /**
   * direction property setter
   * @param  direction New direction value
   * @return this
   */
  public Options setDirection(String direction) {
    this.direction = direction;
    return this;
  }

  /**
   * get property getter
   * @return get property value
   */
  public String[] getGet() {
    return get;
  }

  /**
   * get property setter
   * @param  get New get value
   * @return this
   */
  public Options setGet(String[] get) {
    this.get = get;
    return this;
  }

  /**
   * limit property getter
   * @return limit property value
   */
  public Integer[] getLimit() {
    return limit;
  }

  /**
   * limit property setter
   * @param  limit New limit value
   * @return this
   */
  public Options setLimit(Integer[] limit) {
    this.limit = limit;
    return this;
  }

  /**
   * ch property getter
   * @return ch property value
   */
  public boolean getCh() {
    return ch;
  }

  /**
   * ch property setter
   * @param  ch New ch value
   * @return this
   */
  public Options setCh(boolean ch) {
    this.ch = ch;
    return this;
  }

  /**
   * incr property getter
   * @return incr property value
   */
  public boolean getIncr() {
    return incr;
  }

  /**
   * incr property setter
   * @param  incr New incr value
   * @return this
   */
  public Options setIncr(boolean incr) {
    this.incr = incr;
    return this;
  }

  /**
   * aggregate property getter
   * @return aggregate property value
   */
  public String getAggregate() {
    return aggregate;
  }

  /**
   * aggregate property setter
   * @param  aggregate New aggregate value
   * @return this
   */
  public Options setAggregate(String aggregate) {
    this.aggregate = aggregate;
    return this;
  }

  /**
   * weights property getter
   * @return weights property value
   */
  public Integer[] getWeights() {
    return weights;
  }

  /**
   * weights property setter
   * @param  weights New weights value
   * @return this
   */
  public Options setWeights(Integer[] weights) {
    this.weights = weights;
    return this;
  }
}
