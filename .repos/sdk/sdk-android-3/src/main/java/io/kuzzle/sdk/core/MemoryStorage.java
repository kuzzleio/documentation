package io.kuzzle.sdk.core;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.util.KuzzleJSONObject;

/**
 * Kuzzle's memory storage is a separate data store from the database layer.
 * It is internaly based on Redis. You can access most of Redis functions (all
 * lowercased), excepting:
 *   * all cluster based functions
 *   * all script based functions
 *   * all cursors functions
 *
 */
public class MemoryStorage {
  private Kuzzle  kuzzle;
  private Kuzzle.QueryArgs queryArgs = new Kuzzle.QueryArgs();

  public MemoryStorage(@NonNull final Kuzzle kuzzle) {
    this.kuzzle = kuzzle;
    queryArgs.controller = "ms";
  }

  protected void assignGeoradiusOptions(@NonNull JSONObject query, Options options) {
    if (options != null) {
      JSONArray opts = new JSONArray();

      if (options.getWithcoord()) {
        opts.put("withcoord");
      }

      if (options.getWithdist()) {
        opts.put("withdist");
      }

      if (options.getCount() != null) {
        opts.put("count").put(options.getCount());
      }

      if (options.getSort() != null) {
        opts.put(options.getSort());
      }

      if (opts.length() > 0) {
        try {
          query.put("options", opts);
        }
        catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  protected JSONObject[] mapGeoradiusResults(@NonNull JSONArray points) throws JSONException {
    JSONObject[] mapped = new JSONObject[points.length()];

    // Simple array of point names (no options provided)
    if (points.get(0) instanceof String) {
      for (int i = 0; i < points.length(); i++) {
        mapped[i] = new JSONObject().put("name", points.getString(i));
      }

      return mapped;
    }

    for (int i = 0; i < points.length(); i++) {
      JSONArray rawPoint = points.getJSONArray(i);
      JSONObject p = new JSONObject().put("name", rawPoint.getString(0));

      for (int j = 1; j < rawPoint.length(); j++) {
        // withcoord results are stored in an array...
        if (rawPoint.get(j) instanceof JSONArray) {
          JSONArray coords = rawPoint.getJSONArray(j);

          p
            .put("coordinates", new JSONArray()
              .put(Double.parseDouble(coords.getString(0)))
              .put(Double.parseDouble(coords.getString(1)))
            );
        }
        else {
          // ... while withdist results are not
          p.put("distance", Double.parseDouble(rawPoint.getString(j)));
        }
      }

      mapped[i] = p;
    }

    return mapped;
  }

  protected JSONObject[] mapZrangeResults(@NonNull JSONArray members) {
    ArrayList<JSONObject> mapped = new ArrayList<>(members.length() /2);

    try {
      for (int i = 0; i < members.length(); i += 2) {
        mapped.add(new JSONObject()
          .put("member", members.getString(i))
          .put("score", Double.parseDouble(members.getString(i+1)))
        );
      }
    }
    catch(JSONException e) {
      throw new RuntimeException(e);
    }

    return mapped.toArray(new JSONObject[0]);
  }

  protected void send(@NonNull String action, final KuzzleJSONObject query, Options options, final ResponseListener<JSONObject> listener) {
    queryArgs.action = action;

    try {
      if (listener != null) {
          kuzzle.query(queryArgs, query, options, new OnQueryDoneListener() {
            @Override
            public void onSuccess(JSONObject response) {
              listener.onSuccess(response);
            }

            @Override
            public void onError(JSONObject error) {
              listener.onError(error);
            }
          });
      }
      else {
        kuzzle.query(queryArgs, query, options);
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  protected ResponseListener<JSONObject> getCallbackLong(final ResponseListener<Long> listener) {
    return new ResponseListener<JSONObject>() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          listener.onSuccess(response.getLong("result"));
        }
        catch(JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    };
  }

  protected ResponseListener<JSONObject> getCallbackInt(final ResponseListener<Integer> listener) {
    return new ResponseListener<JSONObject>() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          listener.onSuccess(response.getInt("result"));
        }
        catch(JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    };
  }

  protected ResponseListener<JSONObject> getCallbackString(final ResponseListener<String> listener) {
    return new ResponseListener<JSONObject>() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          listener.onSuccess(response.getString("result"));
        }
        catch(JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    };
  }

  protected ResponseListener<JSONObject> getCallbackDouble(final ResponseListener<Double> listener) {
    return new ResponseListener<JSONObject>() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          listener.onSuccess(Double.parseDouble(response.getString("result")));
        }
        catch(JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    };
  }

  protected ResponseListener<JSONObject> getCallbackStringArray(final ResponseListener<String[]> listener) {
    return new ResponseListener<JSONObject>() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          JSONArray arr = response.getJSONArray("result");
          String[] result = new String[arr.length()];

          for (int i = 0; i < arr.length(); i++) {
            result[i] = arr.getString(i);
          }

          listener.onSuccess(result);
        }
        catch(JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    };
  }

  protected ResponseListener<JSONObject> getCallbackScanResult(final ResponseListener<JSONObject> listener) {
    return new ResponseListener<JSONObject>() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          JSONArray arr = response.getJSONArray("result");
          JSONObject result = new JSONObject();

          try {
            result
              .put("cursor", Integer.parseInt(arr.getString(0)))
              .put("values", arr.getJSONArray(1));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }

          listener.onSuccess(result);
        }
        catch(JSONException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onError(JSONObject error) {
        listener.onError(error);
      }
    };
  }

  /**
   * {@link #append(String, String, Options, ResponseListener)}
   */
  public MemoryStorage append(@NonNull String key, @NonNull final String value) {
    return append(key, value, null, null);
  }

  /**
   * {@link #append(String, String, Options, ResponseListener)}
   */
  public MemoryStorage append(@NonNull String key, @NonNull final String value, Options options) {
    return append(key, value, options, null);
  }

  /**
   * {@link #append(String, String, Options, ResponseListener)}
   */
  public MemoryStorage append(@NonNull String key, @NonNull final String value, final ResponseListener<Long> listener) {
    return append(key, value, null, listener);
  }

  /**
   * Append a value to a key
   * 
   * @param key  Key ID
   * @param  value  Value to append
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage append(@NonNull String key, @NonNull final String value, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
      );

    send("append", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #bitcount(String, Options, ResponseListener)}
   */
  public void bitcount(@NonNull String key, @NonNull final ResponseListener<Long> listener) {
    bitcount(key, null, listener);
  }

  /**
   * Counts the number of set bits (population counting)
   *
   * @param key       Key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void bitcount(@NonNull String key, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    if (options != null) {
      if (options.getStart() != null) {
        query.put("start", options.getStart());
      }

      if (options.getEnd() != null) {
        query.put("end", options.getEnd());
      }
    }

    send("bitcount", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #bitop(String, String, String[], Options, ResponseListener)}
   */
  public MemoryStorage bitop(@NonNull String key, @NonNull String operation, @NonNull final String[] keys) throws JSONException {
    return bitop(key, operation, keys, null, null);
  }

  /**
   * {@link #bitop(String, String, String[], Options, ResponseListener)}
   */
  public MemoryStorage bitop(@NonNull String key, @NonNull String operation, @NonNull final String[] keys, Options options) throws JSONException {
    return bitop(key, operation, keys, options, null);
  }

  /**
   * {@link #bitop(String, String, String[], Options, ResponseListener)}
   */
  public MemoryStorage bitop(@NonNull String key, @NonNull String operation, @NonNull final String[] keys, final ResponseListener<Long> listener) throws JSONException {
    return bitop(key, operation, keys, null, listener);
  }

  /**
   * Performs a bitwise operation between multiple keys (containing string values) and stores the result in the destination key.
   * @param  key            Destination Key ID
   * @param  operation      Bit operation name
   * @param  keys           Keys on which to perform the bitwise operation
   * @param options Request options
   * @param  listener  Response callback listener
   * @return this
   * @throws JSONException 
   */
  public MemoryStorage bitop(@NonNull String key, @NonNull String operation, @NonNull final String[] keys, Options options, final ResponseListener<Long> listener) throws JSONException {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("operation", operation)
        .put("keys", new JSONArray(Arrays.asList(keys)))
      );

    send("bitop", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #bitpos(String, int, Options, ResponseListener)}
   */
  public void bitpos(@NonNull String key, int bit, @NonNull final ResponseListener<Long> listener) {
    bitpos(key, bit, null, listener);
  }

  /**
   * Returns the position of the first bit set to 1 or 0 in a string, or in a substring
   * @param key       Key ID
   * @param bit       Bit to look for
   * @param options Request options
   * @param listener Response callback listener
   */
    public void bitpos(@NonNull String key, int bit, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("bit", bit);

    if (options != null) {
      if (options.getStart() != null) {
        query.put("start", options.getStart());
      }

      if (options.getEnd() != null) {
        query.put("end", options.getEnd());
      }
    }

    send("bitpos", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #dbsize(Options, ResponseListener)}
   */
  public void dbsize(@NonNull final ResponseListener<Long> listener) {
    dbsize(null, listener);
  }

  /**
   * Returns the number of keys in the application database.
   * @param options Request options
   * @param listener Response callback listener
   */
  public void dbsize(Options options, @NonNull final ResponseListener<Long> listener) {
    send("dbsize", new KuzzleJSONObject(), options, getCallbackLong(listener));
  }

  /**
   * {@link #decr(String, Options, ResponseListener)}
   */
  public MemoryStorage decr(@NonNull String key) {
    return decr(key, null, null);
  }

  /**
   * {@link #decr(String, Options, ResponseListener)}
   */
  public MemoryStorage decr(@NonNull String key, Options options) {
    return decr(key, options, null);
  }

  /**
   * {@link #decr(String, Options, ResponseListener)}
   */
  public MemoryStorage decr(@NonNull String key, final ResponseListener<Long> listener) {
    return decr(key, null, listener);
  }

  /**
   * Decrements the value of a key by 1
   * @param  key  Key ID
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage decr(@NonNull String key, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("decr", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #decrby(String, long, Options, ResponseListener)}
   */
  public MemoryStorage decrby(@NonNull String key, long value) {
    return decrby(key, value, null, null);
  }

  /**
   * {@link #decrby(String, long, Options, ResponseListener)}
   */
  public MemoryStorage decrby(@NonNull String key, long value, Options options) {
    return decrby(key, value, options, null);
  }

  /**
   * {@link #decrby(String, long, Options, ResponseListener)}
   */
  public MemoryStorage decrby(@NonNull String key, long value, final ResponseListener<Long> listener) {
    return decrby(key, value, null, listener);
  }

  /**
   * Decrements the value of a key by a given value
   * @param  key    Key ID
   * @param  value  Decrement value
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage decrby(@NonNull String key, long value, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
      );

    send("decrby", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #del(String[], Options, ResponseListener)}
   */
  public MemoryStorage del(@NonNull String[] keys) {
    return del(keys, null, null);
  }

  /**
   * {@link #del(String[], Options, ResponseListener)}
   */
  public MemoryStorage del(@NonNull String[] keys, Options options) {
    return del(keys, options, null);
  }

  /**
   * {@link #del(String[], Options, ResponseListener)}
   */
  public MemoryStorage del(@NonNull String[] keys, final ResponseListener<Long> listener) {
    return del(keys, null, listener);
  }

  /**
   * Delete keys
   * @param  keys  Key IDs to delete
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage del(@NonNull String[] keys, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("body", new KuzzleJSONObject().put("keys", new JSONArray(Arrays.asList(keys))));

    send("del", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #exists(String[], Options, ResponseListener)}
   */
  public void exists(@NonNull String[] keys, @NonNull final ResponseListener<Long> listener) {
    exists(keys, null, listener);
  }

  /**
   * Check if the specified keys exist
   * 
   * @param keys      Key IDs
   * @param options Request options
   * @param listener Response callback listener
   */
  public void exists(@NonNull String[] keys, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("keys", new JSONArray(Arrays.asList(keys)));

    send("exists", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #expire(String, long, Options, ResponseListener)}
   */
  public MemoryStorage expire(@NonNull String key, long seconds) {
    return expire(key, seconds, null, null);
  }

  /**
   * {@link #expire(String, long, Options, ResponseListener)}
   */
  public MemoryStorage expire(@NonNull String key, long seconds, Options options) {
    return expire(key, seconds, options, null);
  }

  /**
   * {@link #expire(String, long, Options, ResponseListener)}
   */
  public MemoryStorage expire(@NonNull String key, long seconds, final ResponseListener<Integer> listener) {
    return expire(key, seconds, null, listener);
  }

  /**
   * Set an expiration timeout on a key
   * @param  key      Key ID
   * @param  seconds  Timeout (in seconds)
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage expire(@NonNull String key, long seconds, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("seconds", seconds)
      );

    send("expire", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #expireat(String, long, Options, ResponseListener)}
   */
  public MemoryStorage expireat(@NonNull String key, long timestamp) {
    return expireat(key, timestamp, null, null);
  }

  /**
   * {@link #expireat(String, long, Options, ResponseListener)}
   */
  public MemoryStorage expireat(@NonNull String key, long timestamp, Options options) {
    return expireat(key, timestamp, options, null);
  }

  /**
   * {@link #expireat(String, long, Options, ResponseListener)}
   */
  public MemoryStorage expireat(@NonNull String key, long timestamp, final ResponseListener<Integer> listener) {
    return expireat(key, timestamp, null, listener);
  }

  /**
   * Set an expiration timestamp to a key
   * @param  key        Key ID
   * @param  timestamp  Expiration timestamp (Epoch Time)
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage expireat(@NonNull String key, long timestamp, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("timestamp", timestamp)
      );

    send("expireat", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #flushdb(Options, ResponseListener)}
   */
  public MemoryStorage flushdb() {
    return flushdb(null, null);
  }

  /**
   * {@link #flushdb(Options, ResponseListener)}
   */
  public MemoryStorage flushdb(final ResponseListener<String> listener) {
    return flushdb(null, listener);
  }


  /**
   * {@link #flushdb(Options, ResponseListener)}
   */
  public MemoryStorage flushdb(Options options) {
    return flushdb(options, null);
  }

  /**
   * Delete all keys from the database
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage flushdb(Options options, final ResponseListener<String> listener) {
    send("flushdb", new KuzzleJSONObject(), options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #geoadd(String, JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage geoadd(@NonNull String key, @NonNull JSONObject[] points) {
    return geoadd(key, points, null, null);
  }

  /**
   * {@link #geoadd(String, JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage geoadd(@NonNull String key, @NonNull JSONObject[] points, final ResponseListener<Long> listener) {
    return geoadd(key, points, null, listener);
  }

  /**
   * {@link #geoadd(String, JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage geoadd(@NonNull String key, @NonNull JSONObject[] points, Options options) {
    return geoadd(key, points, options, null);
  }

  /**
   * Add geospatial points to a key
   * @param  key     Key ID
   * @param  points  Geospatial points to add
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage geoadd(@NonNull String key, @NonNull JSONObject[] points, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("points", new JSONArray(Arrays.asList(points)))
      );

    send("geoadd", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #geodist(String, String, String, Options, ResponseListener)}
   */
  public void geodist(@NonNull String key, @NonNull String member1, @NonNull String member2, @NonNull final ResponseListener<Double> listener) {
    geodist(key, member1, member2, null, listener);
  }

  /**
   * Get the distance between two geospatial members of a key (see geoadd)
   * @param key       Key ID
   * @param member1   Geospatial point 1
   * @param member2   Geospatial point 2
   * @param options Request options
   * @param listener Response callback listener
   */
  public void geodist(@NonNull String key, @NonNull String member1, @NonNull String member2, Options options, @NonNull final ResponseListener<Double> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("member1", member1)
      .put("member2", member2);

    if (options != null) {
      if (options.getUnit() != null) {
        query.put("unit", options.getUnit());
      }
    }

    send("geodist", query, options, getCallbackDouble(listener));
  }

  /**
   * {@link #geohash(String, String[], Options, ResponseListener)}
   */
  public void geohash(@NonNull String key, @NonNull String[] members, @NonNull final ResponseListener<String[]> listener) {
    geohash(key, members, null, listener);
  }

  /**
   * Return the geohash values for the provided key's members
   * @param key       Key ID
   * @param members   List of geospatial members
   * @param options Request options
   * @param listener Response callback listener
   */
  public void geohash(@NonNull String key, @NonNull String[] members, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("members", new JSONArray(Arrays.asList(members)));

    send("geohash", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #geopos(String, String[], Options, ResponseListener)}
   */
  public void geopos(@NonNull String key, @NonNull String[] members, @NonNull final ResponseListener<Double[][]> listener) {
    geopos(key, members, null, listener);
  }

  /**
   * Return the longitude/latitude values for the provided key's members
   * @param key       Key ID
   * @param members   List of geospatial members
   * @param options Request options
   * @param listener Response callback listener
   */
  public void geopos(@NonNull String key, @NonNull String[] members, Options options, @NonNull final ResponseListener<Double[][]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("members", new JSONArray(Arrays.asList(members)));

    send(
      "geopos",
      query,
      options,
      new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            /*
             Converts the resulting array of arrays of strings,
             into an array of arrays of doubles
             */
            JSONArray raw = response.getJSONArray("result");
            Double[][] result = new Double[raw.length()][2];

            for (int i = 0; i < raw.length(); i++) {
              JSONArray rawPos = raw.getJSONArray(i);

              for (int j = 0; j < rawPos.length(); j++) {
                result[i][j] = Double.parseDouble(rawPos.getString(j));
              }
            }

            listener.onSuccess(result);
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      }
    );
  }

  /**
   * {@link #georadius(String, double, double, double, String, Options, ResponseListener)}
   */
  public void georadius(@NonNull String key, double lon, double lat, double distance, @NonNull String unit, @NonNull final ResponseListener<JSONObject[]> listener) {
    georadius(key, lon, lat, distance, unit, null, listener);
  }

  /**
   * Return the geospatial members of a key inside the provided radius
   * @param key       Key ID
   * @param lon       Longitude value for the radius center
   * @param lat       Latitude value for the radius center
   * @param distance  Radius value
   * @param unit      Radius distance unit
   * @param options Request options
   * @param listener Response callback listener
   */
  public void georadius(@NonNull String key, double lon, double lat, double distance, @NonNull String unit, Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("lon", lon)
      .put("lat", lat)
      .put("distance", distance)
      .put("unit", unit);

    assignGeoradiusOptions(query, options);

    send(
      "georadius",
      query,
      options,
      new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(mapGeoradiusResults(response.getJSONArray("result")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      }
    );
  }

  /**
   * {@link #georadiusbymember(String, String, double, String, Options, ResponseListener)}
   */
  public void georadiusbymember(@NonNull String key, @NonNull String member, double distance, @NonNull String unit, @NonNull  final ResponseListener<JSONObject[]> listener) {
    georadiusbymember(key, member, distance, unit, null, listener);
  }

  /**
   * Returns the members (added with geoadd) of a given key inside 
   * the provided geospatial radius, centered around one of a 
   * key's member.
   *   
   * @param key       Key ID
   * @param member    Geospatial member, center of the radius
   * @param distance  Radius value
   * @param unit      Radius distance unit
   * @param options Request options
   * @param listener Response callback listener
   */
  public void georadiusbymember(@NonNull String key, @NonNull String member, double distance, @NonNull String unit, Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("member", member)
      .put("distance", distance)
      .put("unit", unit);

    assignGeoradiusOptions(query, options);

    send(
      "georadiusbymember",
      query,
      options,
      new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(mapGeoradiusResults(response.getJSONArray("result")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      }
    );
  }

  /**
   * {@link #get(String, Options, ResponseListener)}
   */
  public void get(@NonNull String key, @NonNull final ResponseListener<String> listener) {
    get(key, null, listener);
  }


  /**
   * Get a key's value
   * @param key       Key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void get(@NonNull String key, Options options, @NonNull final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("get", query, options, getCallbackString(listener));
  }

  /**
   * {@link #getbit(String, long, Options, ResponseListener)}
   */
  public void getbit(@NonNull String key, long offset, @NonNull final ResponseListener<Integer> listener) {
    getbit(key, offset, null, listener);
  }

  /**
   * Returns the bit value at offset, in the string value stored in a key.
   * @param key       Key ID
   * @param offset    Offset value
   * @param options Request options
   * @param listener Response callback listener
   */
  public void getbit(@NonNull String key, long offset, Options options, @NonNull final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key).put("offset", offset);

    send("getbit", query, options, getCallbackInt(listener));
  }

  /**
   * {@link #getrange(String, long, long, Options, ResponseListener)}
   */
  public void getrange(@NonNull String key, long start, long end, @NonNull final ResponseListener<String> listener) {
    getrange(key, start, end, null, listener);
  }

  /**
   * Returns a substring of a key's value (index starts at position 0).
   * @param key       Key ID
   * @param start     Substring starting position
   * @param end       Substring ending position
   * @param options Request options
   * @param listener Response callback listener
   */
  public void getrange(@NonNull String key, long start, long end, Options options, @NonNull final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("start", start)
      .put("end", end);

    send("getrange", query, options, getCallbackString(listener));
  }

  /**
   * {@link #getset(String, String, Options, ResponseListener)}
   */
  public MemoryStorage getset(@NonNull String key, @NonNull String value) {
    return getset(key, value, null, null);
  }

  /**
   * {@link #getset(String, String, Options, ResponseListener)}
   */
  public MemoryStorage getset(@NonNull String key, @NonNull String value, final ResponseListener<String> listener) {
    return getset(key, value, null, listener);
  }

  /**
   * {@link #getset(String, String, Options, ResponseListener)}
   */
  public MemoryStorage getset(@NonNull String key, @NonNull String value, Options options) {
    return getset(key, value, options, null);
  }

  /**
   * Sets a new value for a key and returns its previous value.
   * @param  key    Key ID
   * @param  value  New value
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage getset(@NonNull String key, @NonNull String value, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
      );

    send("getset", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #hdel(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage hdel(@NonNull String key, @NonNull String[] fields) {
    return hdel(key, fields, null, null);
  }

  /**
   * {@link #hdel(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage hdel(@NonNull String key, @NonNull String[] fields, final ResponseListener<Long> listener) {
    return hdel(key, fields, null, listener);
  }

  /**
   * {@link #hdel(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage hdel(@NonNull String key, @NonNull String[] fields, Options options) {
    return hdel(key, fields, options, null);
  }

  /**
   * Remove fields from a hash
   * @param  key     Hash key ID
   * @param  fields  Fields to remove
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage hdel(@NonNull String key, @NonNull String[] fields, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("fields", new JSONArray(Arrays.asList(fields)))
      );

    send("hdel", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #hexists(String, String, Options, ResponseListener)}
   */
  public void hexists(@NonNull String key, @NonNull String field, @NonNull final ResponseListener<Integer> listener) {
    hexists(key, field, null, listener);
  }

  /**
   * Check if a field exists in a hash
   * @param key       Hash key ID
   * @param field     Field name
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hexists(@NonNull String key, @NonNull String field, Options options, @NonNull final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key).put("field", field);

    send("hexists", query, options, getCallbackInt(listener));
  }

  /**
   * {@link #hget(String, String, Options, ResponseListener)}
   */
  public void hget(@NonNull String key, @NonNull String field, @NonNull final ResponseListener<String> listener) {
    hget(key, field, null, listener);
  }

  /**
   * Return the field's value of a hash
   * @param key       Hash key ID
   * @param field     Field name
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hget(@NonNull String key, @NonNull String field, Options options, @NonNull final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key).put("field", field);

    send("hget", query, options, getCallbackString(listener));
  }

  /**
   * {@link #hgetall(String, Options, ResponseListener)}
   */
  public void hgetall(@NonNull String key, @NonNull final ResponseListener<JSONObject> listener) {
    hgetall(key, null, listener);
  }

  /**
   * Return all fields and values of a hash
   * @param key       Hash key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hgetall(@NonNull String key, Options options, @NonNull final ResponseListener<JSONObject> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send(
      "hgetall",
      query,
      options,
      new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(response.getJSONObject("result"));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      }
    );
  }

  /**
   * {@link #hincrby(String, String, long, Options, ResponseListener)}
   */
  public MemoryStorage hincrby(@NonNull String key, @NonNull String field, long value) {
    return hincrby(key, field, value, null, null);
  }

  /**
   * {@link #hincrby(String, String, long, Options, ResponseListener)}
   */
  public MemoryStorage hincrby(@NonNull String key, @NonNull String field, long value, final ResponseListener<Long> listener) {
    return hincrby(key, field, value, null, listener);
  }

  /**
   * {@link #hincrby(String, String, long, Options, ResponseListener)}
   */
  public MemoryStorage hincrby(@NonNull String key, @NonNull String field, long value, Options options) {
    return hincrby(key, field, value, options, null);
  }

  /**
   * Increments the number stored in a hash field by the provided integer value.
   * @param  key    Hash Key ID
   * @param  field  Field to increment
   * @param  value  Increment value
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage hincrby(@NonNull String key, @NonNull String field, long value, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("field", field)
        .put("value", value)
      );

    send("hincrby", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #hincrbyfloat(String, String, double, Options, ResponseListener)}
   */
  public MemoryStorage hincrbyfloat(@NonNull String key, @NonNull String field, double value) {
    return hincrbyfloat(key, field, value, null, null);
  }

  /**
   * {@link #hincrbyfloat(String, String, double, Options, ResponseListener)}
   */
  public MemoryStorage hincrbyfloat(@NonNull String key, @NonNull String field, double value, final ResponseListener<Double> listener) {
    return hincrbyfloat(key, field, value, null, listener);
  }

  /**
   * {@link #hincrbyfloat(String, String, double, Options, ResponseListener)}
   */
  public MemoryStorage hincrbyfloat(@NonNull String key, @NonNull String field, double value, Options options) {
    return hincrbyfloat(key, field, value, options, null);
  }

  /**
   * Increments the number stored in a hash field by the provided float value.
   * @param  key    Hash key ID
   * @param  field  Field to increment
   * @param  value  Increment value
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage hincrbyfloat(@NonNull String key, @NonNull String field, double value, Options options, final ResponseListener<Double> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("field", field)
        .put("value", value)
      );

    send("hincrbyfloat", query, options, listener != null ? getCallbackDouble(listener) : null);

    return this;
  }

  /**
   * {@link #hkeys(String, Options, ResponseListener)}
   */
  public void hkeys(@NonNull String key, @NonNull final ResponseListener<String[]> listener) {
    hkeys(key, null, listener);
  }

  /**
   * Return all the field names contained in a hash
   * @param key       Hash key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hkeys(@NonNull String key, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("hkeys", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #hlen(String, Options, ResponseListener)}
   */
  public void hlen(@NonNull String key, @NonNull final ResponseListener<Long> listener) {
    hlen(key, null, listener);
  }

  /**
   * Return the number of members of a hash
   * @param key       Hash key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hlen(@NonNull String key, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("hlen", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #hmget(String, String[], Options, ResponseListener)}
   */
  public void hmget(@NonNull String key, @NonNull String[] fields, @NonNull final ResponseListener<String[]> listener) {
    hmget(key, fields, null, listener);
  }

  /**
   * Returns the values of the specified hash’s fields.
   * @param key       Hash key ID
   * @param fields    Field names to return
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hmget(@NonNull String key, @NonNull String[] fields, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("fields", new JSONArray(Arrays.asList(fields)));

    send("hmget", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #hmset(String, JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage hmset(@NonNull String key, @NonNull JSONObject[] entries) {
    return hmset(key, entries, null, null);
  }

  /**
   * {@link #hmset(String, JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage hmset(@NonNull String key, @NonNull JSONObject[] entries, final ResponseListener<String> listener) {
    return hmset(key, entries, null, listener);
  }

  /**
   * {@link #hmset(String, JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage hmset(@NonNull String key, @NonNull JSONObject[] entries, Options options) {
    return hmset(key, entries, options, null);
  }

  /**
   * Sets multiple fields at once in a hash.
   * @param  key      Hash key ID
   * @param  entries  Name-Value pairs to set
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage hmset(@NonNull String key, @NonNull JSONObject[] entries, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("entries", new JSONArray(Arrays.asList(entries)))
      );

    send("hmset", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #hscan(String, long, Options, ResponseListener)}
   */
  public void hscan(@NonNull String key, long cursor, @NonNull final ResponseListener<JSONObject> listener) {
    hscan(key, cursor, null, listener);
  }

  /**
   * Identical to scan, except that hscan iterates the fields contained in a hash.
   * @param key       Hash key ID
   * @param cursor    Cursor position (0 to start a new scan)
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hscan(@NonNull String key, long cursor, Options options, @NonNull final ResponseListener<JSONObject> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("cursor", cursor);

    if (options != null) {
      if (options.getCount() != null) {
        query.put("count", options.getCount());
      }

      if (options.getMatch() != null) {
        query.put("match", options.getMatch());
      }
    }

    send("hscan", query, options, getCallbackScanResult(listener));
  }

  /**
   * {@link #hset(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage hset(@NonNull String key, @NonNull String field, @NonNull String value) {
    return hset(key, field, value, null, null);
  }

  /**
   * {@link #hset(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage hset(@NonNull String key, @NonNull String field, @NonNull String value, final ResponseListener<Integer> listener) {
    return hset(key, field, value, null, listener);
  }

  /**
   * {@link #hset(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage hset(@NonNull String key, @NonNull String field, @NonNull String value, Options options) {
    return hset(key, field, value, options, null);
  }

  /**
   * Sets a field and its value in a hash. 
   * If the key does not exist, a new key holding a hash is created. 
   * @param  key    Hash key ID
   * @param  field  Field name to set
   * @param  value  Value
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage hset(@NonNull String key, @NonNull String field, @NonNull String value, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("field", field)
        .put("value", value)
      );

    send("hset", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #hsetnx(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage hsetnx(@NonNull String key, @NonNull String field, @NonNull String value) {
    return hsetnx(key, field, value, null, null);
  }

  /**
   * {@link #hsetnx(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage hsetnx(@NonNull String key, @NonNull String field, @NonNull String value, final ResponseListener<Integer> listener) {
    return hsetnx(key, field, value, null, listener);
  }

  /**
   * {@link #hsetnx(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage hsetnx(@NonNull String key, @NonNull String field, @NonNull String value, Options options) {
    return hsetnx(key, field, value, options, null);
  }

  /**
   * Sets a field and its value in a hash, only if the field does not already exist.
   * @param  key    Hash key ID
   * @param  field  Field name to set
   * @param  value  Value
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage hsetnx(@NonNull String key, @NonNull String field, @NonNull String value, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("field", field)
        .put("value", value)
      );

    send("hsetnx", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #hstrlen(String, String, Options, ResponseListener)}
   */
  public void hstrlen(@NonNull String key, @NonNull String field, @NonNull final ResponseListener<Long> listener) {
    hstrlen(key, field, null, listener);
  }

  /**
   * Returns the string length of a field’s value in a hash.
   * @param key       Hash key ID
   * @param field     Field name
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hstrlen(@NonNull String key, @NonNull String field, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key).put("field", field);

    send("hstrlen", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #hvals(String, Options, ResponseListener)}
   */
  public void hvals(@NonNull String key, @NonNull final ResponseListener<String[]> listener) {
    hvals(key, null, listener);
  }

  /**
   * Returns all values contained in a hash.
   * @param key       Hash key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void hvals(@NonNull String key, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("hvals", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #incr(String, Options, ResponseListener)}
   */
  public MemoryStorage incr(@NonNull String key) {
    return incr(key, null, null);
  }

  /**
   * {@link #incr(String, Options, ResponseListener)}
   */
  public MemoryStorage incr(@NonNull String key, Options options) {
    return incr(key, options, null);
  }

  /**
   * {@link #incr(String, Options, ResponseListener)}
   */
  public MemoryStorage incr(@NonNull String key, final ResponseListener<Long> listener) {
    return incr(key, null, listener);
  }

  /**
   * Increments the number stored at key by 1. 
   * If the key does not exist, it is set to 0 before performing the operation.
   * @param  key  Key ID
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage incr(@NonNull String key, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("incr", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #incrby(String, long, Options, ResponseListener)}
   */
  public MemoryStorage incrby(@NonNull String key, long value) {
    return incrby(key, value, null, null);
  }

  /**
   * {@link #incrby(String, long, Options, ResponseListener)}
   */
  public MemoryStorage incrby(@NonNull String key, long value, Options options) {
    return incrby(key, value, options, null);
  }

  /**
   * {@link #incrby(String, long, Options, ResponseListener)}
   */
  public MemoryStorage incrby(@NonNull String key, long value, final ResponseListener<Long> listener) {
    return incrby(key, value, null, listener);
  }

  /**
   * Increments the number stored at key by the provided integer value. 
   * If the key does not exist, it is set to 0 before performing the operation.
   * @param  key    Key ID
   * @param  value  Increment value
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage incrby(@NonNull String key, long value, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
      );

    send("incrby", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #incrbyfloat(String, double, Options, ResponseListener)}
   */
  public MemoryStorage incrbyfloat(@NonNull String key, double value) {
    return incrbyfloat(key, value, null, null);
  }

  /**
   * {@link #incrbyfloat(String, double, Options, ResponseListener)}
   */
  public MemoryStorage incrbyfloat(@NonNull String key, double value, final ResponseListener<Double> listener) {
    return incrbyfloat(key, value, null, listener);
  }

  /**
   * {@link #incrbyfloat(String, double, Options, ResponseListener)}
   */
  public MemoryStorage incrbyfloat(@NonNull String key, double value, Options options) {
    return incrbyfloat(key, value, options, null);
  }

  /**
   * Increments the number stored at key by the provided float value. 
   * If the key does not exist, it is set to 0 before performing the operation.
   * @param  key    Key ID
   * @param  value  Increment value
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage incrbyfloat(@NonNull String key, double value, Options options, final ResponseListener<Double> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
      );

    send("incrbyfloat", query, options, listener != null ? getCallbackDouble(listener) : null);

    return this;
  }

  /**
   * {@link #keys(String, Options, ResponseListener)}
   */
  public void keys(@NonNull String pattern, @NonNull final ResponseListener<String[]> listener) {
    keys(pattern, null, listener);
  }

  /**
   * Returns all keys matching the provided pattern.
   * @param pattern   Match pattern
   * @param options Request options
   * @param listener Response callback listener
   */
  public void keys(@NonNull String pattern, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("pattern", pattern);

    send("keys", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #lindex(String, long, Options, ResponseListener)}
   */
  public void lindex(@NonNull String key, long index, @NonNull final ResponseListener<String> listener) {
    lindex(key, index, null, listener);
  }

  /**
   * Returns the element at the provided index in a list.
   * @param key       List ID
   * @param index     Index position
   * @param  options [description]
   * @param listener Response callback listener
   */
  public void lindex(@NonNull String key, long index, Options options, @NonNull final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key).put("index", index);

    send("lindex", query, options, getCallbackString(listener));
  }

  /**
   * {@link #linsert(String, String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage linsert(@NonNull String key, @NonNull String position, @NonNull String pivot, @NonNull String value) {
    return linsert(key, position, pivot, value, null, null);
  }

  /**
   * {@link #linsert(String, String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage linsert(@NonNull String key, @NonNull String position, @NonNull String pivot, @NonNull String value, final ResponseListener<Long> listener) {
    return linsert(key, position, pivot, value, null, listener);
  }

  /**
   * {@link #linsert(String, String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage linsert(@NonNull String key, @NonNull String position, @NonNull String pivot, @NonNull String value, Options options) {
    return linsert(key, position, pivot, value, options, null);
  }


  /**
   * Inserts a value in a list, either before or after the reference pivot value.
   * @param  key       List ID
   * @param  position  Either "after" or "before"
   * @param  pivot     Pivot value in the list
   * @param  value     Value to insert
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage linsert(@NonNull String key, @NonNull String position, @NonNull String pivot, @NonNull String value, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("position", position)
        .put("pivot", pivot)
        .put("value", value)
      );

    send("linsert", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #llen(String, Options, ResponseListener)}
   */
  public void llen(@NonNull String key, @NonNull final ResponseListener<Long> listener) {
    llen(key, null, listener);
  }

  /**
   * Counts the number of items in a list.
   * @param key       List ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void llen(@NonNull String key, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("llen", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #lpop(String, Options, ResponseListener)}
   */
  public MemoryStorage lpop(@NonNull String key) {
    return lpop(key, null, null);
  }

  /**
   * {@link #lpop(String, Options, ResponseListener)}
   */
  public MemoryStorage lpop(@NonNull String key, final ResponseListener<String> listener) {
    return lpop(key, null, listener);
  }

  /**
   * {@link #lpop(String, Options, ResponseListener)}
   */
  public MemoryStorage lpop(@NonNull String key, Options options) {
    return lpop(key, options, null);
  }

  /**
   * Removes and returns the first element of a list.
   * @param  key  List ID
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage lpop(@NonNull String key, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("lpop", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #lpush(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage lpush(@NonNull String key, @NonNull String[] values) {
    return lpush(key, values, null, null);
  }

  /**
   * {@link #lpush(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage lpush(@NonNull String key, @NonNull String[] values, final ResponseListener<Long> listener) {
    return lpush(key, values, null, listener);
  }

  /**
   * {@link #lpush(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage lpush(@NonNull String key, @NonNull String[] values, Options options) {
    return lpush(key, values, options, null);
  }

  /**
   * Prepends the specified values to a list. 
   * If the key does not exist, it is created holding 
   * an empty list before performing the operation.
   * @param  key     List ID
   * @param  values  Values to prepend
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage lpush(@NonNull String key, @NonNull String[] values, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("values", new JSONArray(Arrays.asList(values)))
      );

    send("lpush", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #lpushx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage lpushx(@NonNull String key, @NonNull final String value) {
    return lpushx(key, value, null, null);
  }

  /**
   * {@link #lpushx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage lpushx(@NonNull String key, @NonNull final String value, Options options) {
    return lpushx(key, value, options, null);
  }

  /**
   * {@link #lpushx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage lpushx(@NonNull String key, @NonNull final String value, final ResponseListener<Long> listener) {
    return lpushx(key, value, null, listener);
  }

  /**
   * Prepends the specified value to a list, 
   * only if the key already exists and if it holds a list.
   * @param  key    List ID
   * @param  value  Value to prepend
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage lpushx(@NonNull String key, @NonNull final String value, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
      );

    send("lpushx", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #lrange(String, long, long, Options, ResponseListener)}
   */
  public void lrange(@NonNull String key, long start, long stop, @NonNull final ResponseListener<String[]> listener) {
    lrange(key, start, stop, null, listener);
  }

  /**
   * Returns the list elements between the start and stop positions (inclusive).
   * @param key       List ID
   * @param start     Start position
   * @param stop      End position
   * @param options Request options
   * @param listener Response callback listener
   */
  public void lrange(@NonNull String key, long start, long stop, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("start", start)
      .put("stop", stop);

    send("lrange", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #lrem(String, long, String, Options, ResponseListener)}
   */
  public MemoryStorage lrem(@NonNull String key, long count, @NonNull String value) {
    return lrem(key, count, value, null, null);
  }

  /**
   * {@link #lrem(String, long, String, Options, ResponseListener)}
   */
  public MemoryStorage lrem(@NonNull String key, long count, @NonNull String value, final ResponseListener<Long> listener) {
    return lrem(key, count, value, null, listener);
  }

  /**
   * {@link #lrem(String, long, String, Options, ResponseListener)}
   */
  public MemoryStorage lrem(@NonNull String key, long count, @NonNull String value, Options options) {
    return lrem(key, count, value, options, null);
  }

  /**
   * Removes the first count occurences of elements equal to value from a list.
   * @param  key    List ID
   * @param  count  Number of elements to remove
   * @param  value  Value to remove
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage lrem(@NonNull String key, long count, @NonNull String value, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("count", count)
        .put("value", value)
      );

    send("lrem", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #lset(String, long, String, Options, ResponseListener)}
   */
  public MemoryStorage lset(@NonNull String key, long index, @NonNull String value) {
    return lset(key, index, value, null, null);
  }

  /**
   * {@link #lset(String, long, String, Options, ResponseListener)}
   */
  public MemoryStorage lset(@NonNull String key, long index, @NonNull String value, final ResponseListener<String> listener) {
    return lset(key, index, value, null, listener);
  }

  /**
   * {@link #lset(String, long, String, Options, ResponseListener)}
   */
  public MemoryStorage lset(@NonNull String key, long index, @NonNull String value, Options options) {
    return lset(key, index, value, options, null);
  }

  /**
   * Sets the list element at index with the provided value.
   * @param  key    List ID
   * @param  index  Index of the element to set
   * @param  value  Element new value
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage lset(@NonNull String key, long index, @NonNull String value, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("index", index)
        .put("value", value)
      );

    send("lset", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #ltrim(String, long, long, Options, ResponseListener)}
   */
  public MemoryStorage ltrim(@NonNull String key, long start, long stop) {
    return ltrim(key, start, stop, null, null);
  }

  /**
   * {@link #ltrim(String, long, long, Options, ResponseListener)}
   */
  public MemoryStorage ltrim(@NonNull String key, long start, long stop, final ResponseListener<String> listener) {
    return ltrim(key, start, stop, null, listener);
  }

  /**
   * {@link #ltrim(String, long, long, Options, ResponseListener)}
   */
  public MemoryStorage ltrim(@NonNull String key, long start, long stop, Options options) {
    return ltrim(key, start, stop, options, null);
  }

  /**
   * Trims an existing list so that it will 
   * contain only the specified range of elements specified.
   * @param  key    List ID
   * @param  start  Start position
   * @param  stop   End position
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage ltrim(@NonNull String key, long start, long stop, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("start", start)
        .put("stop", stop)
      );

    send("ltrim", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #mget(String[], Options, ResponseListener)}
   */
  public void mget(@NonNull String[] keys, @NonNull final ResponseListener<String[]> listener) {
    mget(keys, null, listener);
  }

  /**
   * Returns the values of the provided keys.
   * @param keys      List of key identifiers
   * @param options Request options
   * @param listener Response callback listener
   */
  public void mget(@NonNull String[] keys, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("keys", new JSONArray(Arrays.asList(keys)));

    send("mget", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #mset(JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage mset(@NonNull JSONObject[] entries) {
    return mset(entries, null, null);
  }

  /**
   * {@link #mset(JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage mset(@NonNull JSONObject[] entries, Options options) {
    return mset(entries, options, null);
  }

  /**
   * {@link #mset(JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage mset(@NonNull JSONObject[] entries, final ResponseListener<String> listener) {
    return mset(entries, null, listener);
  }

  /**
   * Sets the provided keys to their respective values. 
   * If a key does not exist, it is created. Otherwise, the key’s value is overwritten.
   * @param  entries  Key-Value pairs
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage mset(@NonNull JSONObject[] entries, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("body", new KuzzleJSONObject().put("entries", new JSONArray(Arrays.asList(entries))));

    send("mset", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #msetnx(JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage msetnx(@NonNull JSONObject[] entries) {
    return msetnx(entries, null, null);
  }

  /**
   * {@link #msetnx(JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage msetnx(@NonNull JSONObject[] entries, Options options) {
    return msetnx(entries, options, null);
  }

  /**
   * {@link #msetnx(JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage msetnx(@NonNull JSONObject[] entries, final ResponseListener<Integer> listener) {
    return msetnx(entries, null, listener);
  }

  /**
   * Sets the provided keys to their respective values, only if they do not exist. 
   * If a key exists, then the whole operation is aborted and no key is set
   * @param  entries  Key-Value pairs to set
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage msetnx(@NonNull JSONObject[] entries, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("body", new KuzzleJSONObject().put("entries", new JSONArray(Arrays.asList(entries))));

    send("msetnx", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #object(String, String, Options, ResponseListener)}
   */
  public void object(@NonNull String key, @NonNull String subcommand, @NonNull final ResponseListener<String> listener) {
    object(key, subcommand, null, listener);
  }

  /**
   * Inspects the low-level properties of a key.
   * @param key         Key ID
   * @param subcommand  Name of the low-level property to get
   * @param options Request options
   * @param listener Response callback listener
   */
  public void object(@NonNull String key, @NonNull String subcommand, Options options, @NonNull final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("subcommand", subcommand);

    send("object", query, options, getCallbackString(listener));
  }

  /**
   * {@link #persist(String, Options, ResponseListener)}
   */
  public MemoryStorage persist(@NonNull String key) {
    return persist(key, null, null);
  }

  /**
   * {@link #persist(String, Options, ResponseListener)}
   */
  public MemoryStorage persist(@NonNull String key, final ResponseListener<Integer> listener) {
    return persist(key, null, listener);
  }

  /**
   * {@link #persist(String, Options, ResponseListener)}
   */
  public MemoryStorage persist(@NonNull String key, Options options) {
    return persist(key, options, null);
  }

  /**
   * Removes the expiration delay or timestamp from a key, making it persistent.
   * @param  key  Key ID
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage persist(@NonNull String key, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("persist", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #pexpire(String, long, Options, ResponseListener)}
   */
  public MemoryStorage pexpire(@NonNull String key, long milliseconds) {
    return pexpire(key, milliseconds, null, null);
  }

  /**
   * {@link #pexpire(String, long, Options, ResponseListener)}
   */
  public MemoryStorage pexpire(@NonNull String key, long milliseconds, Options options) {
    return pexpire(key, milliseconds, options, null);
  }

  /**
   * {@link #pexpire(String, long, Options, ResponseListener)}
   */
  public MemoryStorage pexpire(@NonNull String key, long milliseconds, final ResponseListener<Integer> listener) {
    return pexpire(key, milliseconds, null, listener);
  }

  /**
   * Sets a timeout (in milliseconds) on a key. 
   * After the timeout has expired, the key will automatically be deleted.
   * @param  key           Key ID
   * @param  milliseconds  Expiration time in milliseconds
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage pexpire(@NonNull String key, long milliseconds, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("milliseconds", milliseconds)
      );

    send("pexpire", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #pexpireat(String, long, Options, ResponseListener)}
   */
  public MemoryStorage pexpireat(@NonNull String key, long timestamp) {
    return pexpireat(key, timestamp, null, null);
  }

  /**
   * {@link #pexpireat(String, long, Options, ResponseListener)}
   */
  public MemoryStorage pexpireat(@NonNull String key, long timestamp, Options options) {
    return pexpireat(key, timestamp, options, null);
  }

  /**
   * {@link #pexpireat(String, long, Options, ResponseListener)}
   */
  public MemoryStorage pexpireat(@NonNull String key, long timestamp, final ResponseListener<Integer> listener) {
    return pexpireat(key, timestamp, null, listener);
  }

  /**
   * Sets an expiration timestamp on a key. 
   * After the timestamp has been reached, the key will automatically be deleted.
   * @param  key        Key ID
   * @param  timestamp  Expiration timestamp (Epoch-time in milliseconds)
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage pexpireat(@NonNull String key, long timestamp, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("timestamp", timestamp)
      );

    send("pexpireat", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #pfadd(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage pfadd(@NonNull String key, @NonNull String[] elements) {
    return pfadd(key, elements, null, null);
  }

  /**
   * {@link #pfadd(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage pfadd(@NonNull String key, @NonNull String[] elements, final ResponseListener<Integer> listener) {
    return pfadd(key, elements, null, listener);
  }

  /**
   * {@link #pfadd(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage pfadd(@NonNull String key, @NonNull String[] elements, Options options) {
    return pfadd(key, elements, options, null);
  }

  /**
   * Adds elements to an HyperLogLog data structure.
   * @param  key       HyperLogLog ID
   * @param  elements  Array of elements to add
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage pfadd(@NonNull String key, @NonNull String[] elements, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("elements", new JSONArray(Arrays.asList(elements)))
      );

    send("pfadd", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #pfcount(String[], Options, ResponseListener)}
   */
  public void pfcount(@NonNull String[] keys, @NonNull final ResponseListener<Long> listener) {
    pfcount(keys, null, listener);
  }

  /**
   * Returns the probabilistic cardinality of a HyperLogLog data structure, 
   * or of the merged HyperLogLog structures if more than 1 is provided (see pfadd).
   * @param keys      HyperLogLog ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void pfcount(@NonNull String[] keys, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("keys", new JSONArray(Arrays.asList(keys)));

    send("pfcount", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #pfmerge(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage pfmerge(@NonNull String key, @NonNull String[] sources) {
    return pfmerge(key, sources, null, null);
  }

  /**
   * {@link #pfmerge(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage pfmerge(@NonNull String key, @NonNull String[] sources, final ResponseListener<String> listener) {
    return pfmerge(key, sources, null, listener);
  }

  /**
   * {@link #pfmerge(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage pfmerge(@NonNull String key, @NonNull String[] sources, Options options) {
    return pfmerge(key, sources, options, null);
  }

  /**
   * Merges multiple HyperLogLog data structures into an unique HyperLogLog structure 
   * stored at key, approximating the cardinality of the union of the source structures.
   * @param  key      Destination key ID
   * @param  sources  Array of HyperLogLog ID to merge
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage pfmerge(@NonNull String key, @NonNull String[] sources, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("sources", new JSONArray(Arrays.asList(sources)))
      );

    send("pfmerge", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #ping(Options, ResponseListener)}
   */
  public void ping(@NonNull final ResponseListener<String> listener) {
    ping(null, listener);
  }

  /**
   * Pings the memory storage database.
   * @param options Request options
   * @param listener Response callback listener
   */
  public void ping(Options options, @NonNull final ResponseListener<String> listener) {
    send("ping", new KuzzleJSONObject(), options, getCallbackString(listener));
  }

  /**
   * {@link #psetex(String, String, long, Options, ResponseListener)}
   */
  public MemoryStorage psetex(@NonNull String key, @NonNull String value, long milliseconds) {
    return psetex(key, value, milliseconds, null, null);
  }

  /**
   * {@link #psetex(String, String, long, Options, ResponseListener)}
   */
  public MemoryStorage psetex(@NonNull String key, @NonNull String value, long milliseconds, final ResponseListener<String> listener) {
    return psetex(key, value, milliseconds, null, listener);
  }

  /**
   * {@link #psetex(String, String, long, Options, ResponseListener)}
   */
  public MemoryStorage psetex(@NonNull String key, @NonNull String value, long milliseconds, Options options) {
    return psetex(key, value, milliseconds, options, null);
  }

  /**
   * Sets a key with the provided value, and an expiration delay 
   * expressed in milliseconds. If the key does not exist, it is created beforehand.
   * @param  key           Key ID
   * @param  value         Value to set
   * @param  milliseconds  Expiration delay in milliseconds
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage psetex(@NonNull String key, @NonNull String value, long milliseconds, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
        .put("milliseconds", milliseconds)
      );

    send("psetex", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #pttl(String, Options, ResponseListener)}
   */
  public void pttl(@NonNull String key, @NonNull final ResponseListener<Long> listener) {
    pttl(key, null, listener);
  }

  /**
   * Returns the remaining time to live of a key, in milliseconds.
   * @param key       Key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void pttl(@NonNull String key, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("pttl", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #randomkey(Options, ResponseListener)}
   */
  public void randomkey(@NonNull final ResponseListener<String> listener) {
    randomkey(null, listener);
  }

  /**
   * Returns a random key from the memory storage.
   * @param options Request options
   * @param listener Response callback listener
   */
  public void randomkey(Options options, @NonNull final ResponseListener<String> listener) {
    send("randomkey", new KuzzleJSONObject(), options, getCallbackString(listener));
  }

  /**
   * {@link #rename(String, String, Options, ResponseListener)}
   */
  public MemoryStorage rename(@NonNull String key, @NonNull String newkey) {
    return rename(key, newkey, null, null);
  }

  /**
   * {@link #rename(String, String, Options, ResponseListener)}
   */
  public MemoryStorage rename(@NonNull String key, @NonNull String newkey, final ResponseListener<String> listener) {
    return rename(key, newkey, null, listener);
  }

  /**
   * {@link #rename(String, String, Options, ResponseListener)}
   */
  public MemoryStorage rename(@NonNull String key, @NonNull String newkey, Options options) {
    return rename(key, newkey, options, null);
  }

  /**
   * Renames a key to newkey. If newkey already exists, it is overwritten.
   * @param  key     Key ID
   * @param  newkey  New key ID
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage rename(@NonNull String key, @NonNull String newkey, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("newkey", newkey)
      );

    send("rename", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #renamenx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage renamenx(@NonNull String key, @NonNull String newkey) {
    return renamenx(key, newkey, null, null);
  }

  /**
   * {@link #renamenx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage renamenx(@NonNull String key, @NonNull String newkey, final ResponseListener<Integer> listener) {
    return renamenx(key, newkey, null, listener);
  }

  /**
   * {@link #renamenx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage renamenx(@NonNull String key, @NonNull String newkey, Options options) {
    return renamenx(key, newkey, options, null);
  }

  /**
   * Renames a key to newkey, only if newkey does not already exist.
   * @param key      Key ID
   * @param newkey   New key ID
   * @param options  Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage renamenx(@NonNull String key, @NonNull String newkey, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("newkey", newkey)
      );

    send("renamenx", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #rpop(String, Options, ResponseListener)}
   */
  public MemoryStorage rpop(@NonNull String key) {
    return rpop(key, null, null);
  }

  /**
   * {@link #rpop(String, Options, ResponseListener)}
   */
  public MemoryStorage rpop(@NonNull String key, final ResponseListener<String> listener) {
    return rpop(key, null, listener);
  }

  /**
   * {@link #rpop(String, Options, ResponseListener)}
   */
  public MemoryStorage rpop(@NonNull String key, Options options) {
    return rpop(key, options, null);
  }

  /**
   * Removes and returns the last element of a list.
   * @param  key  Key ID
   * @param options Request options
   * @param listener Response callback listener
   * @return this
   */
  public MemoryStorage rpop(@NonNull String key, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("rpop", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #rpoplpush(String, String, Options, ResponseListener)}
   */
  public MemoryStorage rpoplpush(@NonNull String source, @NonNull String destination) {
    return rpoplpush(source, destination, null, null);
  }

  /**
   * {@link #rpoplpush(String, String, Options, ResponseListener)}
   */
  public MemoryStorage rpoplpush(@NonNull String source, @NonNull String destination, final ResponseListener<String> listener) {
    return rpoplpush(source, destination, null, listener);
  }

  /**
   * {@link #rpoplpush(String, String, Options, ResponseListener)}
   */
  public MemoryStorage rpoplpush(@NonNull String source, @NonNull String destination, Options options) {
    return rpoplpush(source, destination, options, null);
  }

  /**
   * Removes the last element of the list at source and 
   * pushes it back at the start of the list at destination.
   * @param  source       Source key ID
   * @param  destination  Destination key ID
   * @param  options     [description]
   * @param  listener    [description]
   * @return             [description]
   */
  public MemoryStorage rpoplpush(@NonNull String source, @NonNull String destination, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("body", new KuzzleJSONObject()
        .put("source", source)
        .put("destination", destination)
      );

    send("rpoplpush", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #rpush(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage rpush(@NonNull String key, @NonNull String[] values) {
    return rpush(key, values, null, null);
  }

  /**
   * {@link #rpush(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage rpush(@NonNull String key, @NonNull String[] values, final ResponseListener<Long> listener) {
    return rpush(key, values, null, listener);
  }

  /**
   * {@link #rpush(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage rpush(@NonNull String key, @NonNull String[] values, Options options) {
    return rpush(key, values, options, null);
  }

  /**
   * Appends the specified values at the end of a list. 
   * If the key does not exist, it is created holding an empty 
   * list before performing the operation.
   * @param  key       List ID
   * @param  values    Values to append
   * @param  options  [description]
   * @param  listener [description]
   * @return          [description]
   */
  public MemoryStorage rpush(@NonNull String key, @NonNull String[] values, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("values", new JSONArray(Arrays.asList(values)))
      );

    send("rpush", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #rpushx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage rpushx(@NonNull String key, @NonNull final String value) {
    return rpushx(key, value, null, null);
  }

  /**
   * {@link #rpushx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage rpushx(@NonNull String key, @NonNull final String value, Options options) {
    return rpushx(key, value, options, null);
  }

  /**
   * {@link #rpushx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage rpushx(@NonNull String key, @NonNull final String value, final ResponseListener<Long> listener) {
    return rpushx(key, value, null, listener);
  }

  /**
   * Appends the specified value at the end of a list, 
   * only if the key already exists and if it holds a list.
   * @param  key       List ID
   * @param  value     Value to append
   * @param  options  [description]
   * @param  listener [description]
   * @return          [description]
   */
  public MemoryStorage rpushx(@NonNull String key, @NonNull final String value, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
      );

    send("rpushx", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #sadd(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage sadd(@NonNull String key, @NonNull String[] members) {
    return sadd(key, members, null, null);
  }

  /**
   * {@link #sadd(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage sadd(@NonNull String key, @NonNull String[] members, final ResponseListener<Long> listener) {
    return sadd(key, members, null, listener);
  }

  /**
   * {@link #sadd(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage sadd(@NonNull String key, @NonNull String[] members, Options options) {
    return sadd(key, members, options, null);
  }

  /**
   * Adds members to a set of unique values stored at key. 
   * If the key does not exist, it is created beforehand.
   * @param  key       Set ID
   * @param  members   Members to add
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage sadd(@NonNull String key, @NonNull String[] members, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("members", new JSONArray(Arrays.asList(members)))
      );

    send("sadd", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #scan(long, Options, ResponseListener)}
   */
  public void scan(long cursor, @NonNull final ResponseListener<JSONObject> listener) {
    scan(cursor, null, listener);
  }

  /**
   * Iterates incrementally the set of keys in the database using a cursor.
   *  
   * @param cursor    Cursor position (0 to start an iteration)
   * @param options Request options
   * @param listener Response callback listener
   */
  public void scan(long cursor, Options options, @NonNull final ResponseListener<JSONObject> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("cursor", cursor);

    if (options != null) {
      if (options.getCount() != null) {
        query.put("count", options.getCount());
      }

      if (options.getMatch() != null) {
        query.put("match", options.getMatch());
      }
    }

    send("scan", query, options, getCallbackScanResult(listener));
  }

  /**
   * {@link #scard(String, Options, ResponseListener)}
   */
  public void scard(@NonNull String key, @NonNull final ResponseListener<Long> listener) {
    scard(key, null, listener);
  }

  /**
   * Returns the number of members stored in a set of unique values.
   * @param key      Set ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void scard(@NonNull String key, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("scard", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #sdiff(String, String[], Options, ResponseListener)}
   */
  public void sdiff(@NonNull String key, @NonNull String[] keys, @NonNull final ResponseListener<String[]> listener) {
    sdiff(key, keys, null, listener);
  }

  /**
   * Returns the difference between the set of unique 
   * values stored at key and the other provided sets.
   * @param key      Reference set ID 
   * @param keys     Set IDs to compare
   * @param options Request options
   * @param listener Response callback listener
   */
  public void sdiff(@NonNull String key, @NonNull String[] keys, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("keys", new JSONArray(Arrays.asList(keys)));

    send("sdiff", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #sdiffstore(String, String[], String, Options, ResponseListener)}
   */
  public MemoryStorage sdiffstore(@NonNull String key, @NonNull String[] keys, @NonNull String destination) {
    return sdiffstore(key, keys, destination, null, null);
  }

  /**
   * {@link #sdiffstore(String, String[], String, Options, ResponseListener)}
   */
  public MemoryStorage sdiffstore(@NonNull String key, @NonNull String[] keys, @NonNull String destination, final ResponseListener<Long> listener) {
    return sdiffstore(key, keys, destination, null, listener);
  }

  /**
   * {@link #sdiffstore(String, String[], String, Options, ResponseListener)}
   */
  public MemoryStorage sdiffstore(@NonNull String key, @NonNull String[] keys, @NonNull String destination, Options options) {
    return sdiffstore(key, keys, destination, options, null);
  }

  /**
   * Computes the difference between the set of unique values stored at key and 
   * the other provided sets, and stores the result in the key stored at destination.
   * @param  key         Reference set ID
   * @param  keys        Set IDs to compare
   * @param  destination Destination set ID
   * @param  options     [description]
   * @param  listener    [description]
   * @return this
   */
  public MemoryStorage sdiffstore(@NonNull String key, @NonNull String[] keys, @NonNull String destination, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("destination", destination)
        .put("keys", new JSONArray(Arrays.asList(keys)))
      );

    send("sdiffstore", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #set(String, String, Options, ResponseListener)}
   */
  public MemoryStorage set(@NonNull String key, @NonNull String value) {
    return set(key, value, null, null);
  }

  /**
   * {@link #set(String, String, Options, ResponseListener)}
   */
  public MemoryStorage set(@NonNull String key, @NonNull String value, final ResponseListener<String> listener) {
    return set(key, value, null, listener);
  }

  /**
   * {@link #set(String, String, Options, ResponseListener)}
   */
  public MemoryStorage set(@NonNull String key, @NonNull String value, Options options) {
    return set(key, value, options, null);
  }

  /**
   * Creates a key holding the provided value, or overwrites it if it already exists.
   * @param  key      Key ID
   * @param  value    Value to set
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage set(@NonNull String key, @NonNull String value, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);
    KuzzleJSONObject body = new KuzzleJSONObject().put("value", value);

    if (options != null) {
      if (options.getEx() != null) {
        body.put("ex", options.getEx());
      }

      if (options.getPx() != null) {
        body.put("px", options.getPx());
      }

      body.put("nx", options.getNx());
      body.put("xx", options.getXx());
    }

    query.put("body", body);

    send("set", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #setex(String, String, long, Options, ResponseListener)}
   */
  public MemoryStorage setex(@NonNull String key, @NonNull String value, long seconds) {
    return setex(key, value, seconds, null, null);
  }

  /**
   * {@link #setex(String, String, long, Options, ResponseListener)}
   */
  public MemoryStorage setex(@NonNull String key, @NonNull String value, long seconds, final ResponseListener<String> listener) {
    return setex(key, value, seconds, null, listener);
  }

  /**
   * {@link #setex(String, String, long, Options, ResponseListener)}
   */
  public MemoryStorage setex(@NonNull String key, @NonNull String value, long seconds, Options options) {
    return setex(key, value, seconds, options, null);
  }

  /**
   * Sets a key with the provided value, and an expiration delay 
   * expressed in seconds. If the key does not exist, it is created beforehand.
   * @param  key      Key ID
   * @param  value    Value to set
   * @param  seconds  Expiration delay, in seconds
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage setex(@NonNull String key, @NonNull String value, long seconds, Options options, final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
        .put("seconds", seconds)
      );

    send("setex", query, options, listener != null ? getCallbackString(listener) : null);

    return this;
  }

  /**
   * {@link #setnx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage setnx(@NonNull String key, @NonNull String value) {
    return setnx(key, value, null, null);
  }

  /**
   * {@link #setnx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage setnx(@NonNull String key, @NonNull String value, final ResponseListener<Integer> listener) {
    return setnx(key, value, null, listener);
  }

  /**
   * {@link #setnx(String, String, Options, ResponseListener)}
   */
  public MemoryStorage setnx(@NonNull String key, @NonNull String value, Options options) {
    return setnx(key, value, options, null);
  }

  /**
   * Sets a value on a key, only if it does not already exist.
   * @param  key      Key ID
   * @param  value    Value to set
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage setnx(@NonNull String key, @NonNull String value, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("value", value)
      );

    send("setnx", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #sinter(String[], Options, ResponseListener)}
   */
  public void sinter(@NonNull String[] keys, @NonNull final ResponseListener<String[]> listener) {
    sinter(keys, null, listener);
  }

  /**
   * Returns the intersection of the provided sets of unique values.
   * @param keys     Array of set IDs to intersect
   * @param options Request options
   * @param listener Response callback listener
   */
  public void sinter(@NonNull String[] keys, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("keys", new JSONArray(Arrays.asList(keys)));

    send("sinter", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #sinterstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage sinterstore(@NonNull String destination, @NonNull String[] keys) {
    return sinterstore(destination, keys, null, null);
  }

  /**
   * {@link #sinterstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage sinterstore(@NonNull String destination, @NonNull String[] keys, final ResponseListener<Long> listener) {
    return sinterstore(destination, keys, null, listener);
  }

  /**
   * {@link #sinterstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage sinterstore(@NonNull String destination, @NonNull String[] keys, Options options) {
    return sinterstore(destination, keys, options, null);
  }

  /**
   * Computes the intersection of the provided sets of unique values 
   * and stores the result in the destination key. 
   * @param  destination Destination key ID
   * @param  keys        Array of set IDs to intersect
   * @param  options     [description]
   * @param  listener    [description]
   * @return             [description]
   */
  public MemoryStorage sinterstore(@NonNull String destination, @NonNull String[] keys, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("body", new KuzzleJSONObject()
        .put("destination", destination)
        .put("keys", new JSONArray(Arrays.asList(keys)))
      );

    send("sinterstore", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #sismember(String, String, Options, ResponseListener)}
   */
  public void sismember(@NonNull String key, @NonNull String member, @NonNull final ResponseListener<Integer> listener) {
    sismember(key, member, null, listener);
  }

  /**
   * Checks if member is a member of the set of unique values stored at key.
   * @param key      Set ID
   * @param member   Member name
   * @param options Request options
   * @param listener Response callback listener
   */
  public void sismember(@NonNull String key, @NonNull String member, Options options, @NonNull final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key).put("member", member);

    send("sismember", query, options, getCallbackInt(listener));
  }

  /**
   * {@link #smembers(String, Options, ResponseListener)}
   */
  public void smembers(@NonNull String key, @NonNull final ResponseListener<String[]> listener) {
    smembers(key, null, listener);
  }

  /**
   * Returns the members of a set of unique values.
   * @param key      Set ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void smembers(@NonNull String key, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("smembers", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #smove(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage smove(@NonNull String key, @NonNull String destination, @NonNull String member) {
    return smove(key, destination, member, null, null);
  }

  /**
   * {@link #smove(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage smove(@NonNull String key, @NonNull String destination, @NonNull String member, final ResponseListener<Integer> listener) {
    return smove(key, destination, member, null, listener);
  }

  /**
   * {@link #smove(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage smove(@NonNull String key, @NonNull String destination, @NonNull String member, Options options) {
    return smove(key, destination, member, options, null);
  }

  /**
   * Moves a member from a set of unique values to another.
   * @param  key         Source set ID
   * @param  destination Destination set ID
   * @param  member      Member to move
   * @param  options     [description]
   * @param  listener    [description]
   * @return this
   */
  public MemoryStorage smove(@NonNull String key, @NonNull String destination, @NonNull String member, Options options, final ResponseListener<Integer> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("destination", destination)
        .put("member", member)
      );

    send("smove", query, options, listener != null ? getCallbackInt(listener) : null);

    return this;
  }

  /**
   * {@link #sort(String, Options, ResponseListener)}
   */
  public void sort(@NonNull String key, @NonNull final ResponseListener<String[]> listener) {
    sort(key, null, listener);
  }

  /**
   * Sorts and returns elements contained in a list, a set of unique values or a 
   * sorted set. By default, sorting is numeric and elements are compared by their 
   * value interpreted as double precision floating point number.
   * @param key      ID of the key to sort
   * @param options Request options
   * @param listener Response callback listener
   */
  public void sort(@NonNull String key, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    if (options != null) {
      if (options.getBy() != null) {
        query.put("by", options.getBy());
      }

      if (options.getDirection() != null) {
        query.put("direction", options.getDirection());
      }

      if (options.getGet() != null) {
        query.put("get", new JSONArray(Arrays.asList(options.getGet())));
      }

      if (options.getLimit() != null) {
        query.put("limit", new JSONArray(Arrays.asList(options.getLimit())));
      }

      query.put("alpha", options.getAlpha());
    }

    send("sort", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #spop(String, Options, ResponseListener)}
   */
  public MemoryStorage spop(@NonNull String key) {
    return spop(key, null, null);
  }

  /**
   * {@link #spop(String, Options, ResponseListener)}
   */
  public MemoryStorage spop(@NonNull String key, final ResponseListener<String[]> listener) {
    return spop(key, null, listener);
  }

  /**
   * {@link #spop(String, Options, ResponseListener)}
   */
  public MemoryStorage spop(@NonNull String key, Options options) {
    return spop(key, options, null);
  }

  /**
   * Removes and returns one or more elements at random from a set of unique values.
   * @param  key      Set ID
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage spop(@NonNull String key, Options options, final ResponseListener<String[]> listener) {
    ResponseListener<JSONObject> callback = null;
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    if (options != null && options.getCount() != null) {
      query.put("body", new KuzzleJSONObject().put("count", options.getCount()));
    }

    if (listener != null) {
      callback = new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            if (response.get("result") instanceof String) {
              listener.onSuccess(new String[]{response.getString("result")});
            }
            else {
              JSONArray arr = response.getJSONArray("result");
              String[] elements = new String[arr.length()];

              for (int i = 0; i < arr.length(); i++) {
                elements[i] = arr.getString(i);
              }

              listener.onSuccess(elements);
            }
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      };
    }

    send("spop", query, options, callback);

    return this;
  }

  /**
   * {@link #srandmember(String, Options, ResponseListener)}
   */
  public MemoryStorage srandmember(@NonNull String key, @NonNull final ResponseListener<String[]> listener) {
    return srandmember(key, null, listener);
  }

  /**
   * Returns one or more members of a set of unique values, at random.
   * @param  key      Set ID
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage srandmember(@NonNull String key, Options options, final ResponseListener<String[]> listener) {
    ResponseListener<JSONObject> callback = null;
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    if (options != null && options.getCount() != null) {
      query.put("count", options.getCount());
    }

    if (listener != null) {
      callback = new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            if (response.get("result") instanceof String) {
              listener.onSuccess(new String[]{response.getString("result")});
            }
            else {
              JSONArray arr = response.getJSONArray("result");
              String[] elements = new String[arr.length()];

              for (int i = 0; i < arr.length(); i++) {
                elements[i] = arr.getString(i);
              }

              listener.onSuccess(elements);
            }
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      };
    }

    send("srandmember", query, options, callback);

    return this;
  }

  /**
   * {@link #srem(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage srem(@NonNull String key, @NonNull String[] members) {
    return srem(key, members, null, null);
  }

  /**
   * {@link #srem(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage srem(@NonNull String key, @NonNull String[] members, final ResponseListener<Long> listener) {
    return srem(key, members, null, listener);
  }

  /**
   * {@link #srem(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage srem(@NonNull String key, @NonNull String[] members, Options options) {
    return srem(key, members, options, null);
  }

  /**
   * Removes members from a set of unique values.
   * @param  key      Set ID
   * @param  members  Members to remove
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage srem(@NonNull String key, @NonNull String[] members, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("members", new JSONArray(Arrays.asList(members)))
      );

    send("srem", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #sscan(String, long, Options, ResponseListener)}
   */
  public void sscan(@NonNull String key, long cursor, @NonNull final ResponseListener<JSONObject> listener) {
    sscan(key, cursor, null, listener);
  }

  /**
   * Identical to scan, except that sscan iterates the members contained in a set
   * @param key       Set ID
   * @param cursor    Cursor position (0 to start a new scan)
   * @param options Request options
   * @param listener Response callback listener
   */
  public void sscan(@NonNull String key, long cursor, Options options, @NonNull final ResponseListener<JSONObject> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("cursor", cursor);

    if (options != null) {
      if (options.getCount() != null) {
        query.put("count", options.getCount());
      }

      if (options.getMatch() != null) {
        query.put("match", options.getMatch());
      }
    }

    send("sscan", query, options, getCallbackScanResult(listener));
  }

  /**
   * {@link #strlen(String, Options, ResponseListener)}
   */
  public void strlen(@NonNull String key, @NonNull final ResponseListener<Long> listener) {
    strlen(key, null, listener);
  }

  /**
   * Returns the length of a value stored at key.
   * @param key      Key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void strlen(@NonNull String key, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("strlen", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #sunion(String[], Options, ResponseListener)}
   */
  public void sunion(@NonNull String[] keys, @NonNull final ResponseListener<String[]> listener) {
    sunion(keys, null, listener);
  }

  /**
   * Returns the union of the provided sets of unique values.
   * @param keys     Set IDs
   * @param options Request options
   * @param listener Response callback listener
   */
  public void sunion(@NonNull String[] keys, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("keys", new JSONArray(Arrays.asList(keys)));

    send("sunion", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #sunionstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage sunionstore(@NonNull String destination, @NonNull String[] keys) {
    return sunionstore(destination, keys, null, null);
  }

  /**
   * {@link #sunionstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage sunionstore(@NonNull String destination, @NonNull String[] keys, final ResponseListener<Long> listener) {
    return sunionstore(destination, keys, null, listener);
  }

  /**
   * {@link #sunionstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage sunionstore(@NonNull String destination, @NonNull String[] keys, Options options) {
    return sunionstore(destination, keys, options, null);
  }

  /**
   * Computes the union of the provided sets of unique values 
   * and stores the result in the destination key.
   * @param  destination Destination set ID
   * @param  keys        Array of set IDs
   * @param  options     [description]
   * @param  listener    [description]
   * @return this
   */
  public MemoryStorage sunionstore(@NonNull String destination, @NonNull String[] keys, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("body", new KuzzleJSONObject()
        .put("destination", destination)
        .put("keys", new JSONArray(Arrays.asList(keys)))
      );

    send("sunionstore", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #time(Options, ResponseListener)}
   */
  public void time(@NonNull final ResponseListener<Long[]> listener) {
    time(null, listener);
  }

  /**
   * Returns the current server time.
   * @param options Request options
   * @param listener Response callback listener
   */
  public void time(Options options, @NonNull final ResponseListener<Long[]> listener) {
    send(
      "time",
      new KuzzleJSONObject(),
      options,
      new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            JSONArray raw = response.getJSONArray("result");
            listener.onSuccess(new Long[]{
              Long.parseLong(raw.getString(0)),
              Long.parseLong(raw.getString(1))
            });
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      }
    );
  }

  /**
   * {@link #touch(String[], Options, ResponseListener)}
   */
  public MemoryStorage touch(@NonNull String[] keys) {
    return touch(keys, null, null);
  }

  /**
   * {@link #touch(String[], Options, ResponseListener)}
   */
  public MemoryStorage touch(@NonNull String[] keys, Options options) {
    return touch(keys, options, null);
  }

  /**
   * {@link #touch(String[], Options, ResponseListener)}
   */
  public MemoryStorage touch(@NonNull String[] keys, final ResponseListener<Long> listener) {
    return touch(keys, null, listener);
  }

  /**
   * Alters the last access time of one or multiple keys. 
   * A key is ignored if it does not exist.
   * @param  keys     Array of key IDs to alter
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage touch(@NonNull String[] keys, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("body", new KuzzleJSONObject().put("keys", new JSONArray(Arrays.asList(keys))));

    send("touch", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #ttl(String, Options, ResponseListener)}
   */
  public void ttl(@NonNull String key, @NonNull final ResponseListener<Long> listener) {
    ttl(key, null, listener);
  }

  /**
   * Returns the remaining time to live of a key, in seconds, or a negative value 
   * if the key does not exist or if it is persistent.
   * @param key      Key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void ttl(@NonNull String key, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("ttl", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #type(String, Options, ResponseListener)}
   */
  public void type(@NonNull String key, @NonNull final ResponseListener<String> listener) {
    type(key, null, listener);
  }

  /**
   * Returns the type of the value held by a key.
   * @param key      Key ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void type(@NonNull String key, Options options, @NonNull final ResponseListener<String> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("type", query, options, getCallbackString(listener));
  }

  /**
   * {@link #zadd(String, JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage zadd(@NonNull String key, @NonNull JSONObject[] elements) {
    return zadd(key, elements, null, null);
  }

  /**
   * {@link #zadd(String, JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage zadd(@NonNull String key, @NonNull JSONObject[] elements, final ResponseListener<Long> listener) {
    return zadd(key, elements, null, listener);
  }

  /**
   * {@link #zadd(String, JSONObject[], Options, ResponseListener)}
   */
  public MemoryStorage zadd(@NonNull String key, @NonNull JSONObject[] elements, Options options) {
    return zadd(key, elements, options, null);
  }

  /**
   * Adds the specified elements to the sorted set stored at key. 
   * If the key does not exist, it is created, holding an empty sorted set. 
   * If it already exists and does not hold a sorted set, an error is returned.
   * @param  key      Sorted set ID
   * @param  elements Elements to add
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage zadd(@NonNull String key, @NonNull JSONObject[] elements, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);
    KuzzleJSONObject body = new KuzzleJSONObject().put("elements", new JSONArray(Arrays.asList(elements)));

    if (options != null) {
      body.put("nx", options.getNx());
      body.put("xx", options.getXx());
      body.put("ch", options.getCh());
      body.put("incr", options.getIncr());
    }

    query.put("body", body);

    send("zadd", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #zcard(String, Options, ResponseListener)}
   */
  public void zcard(@NonNull String key, @NonNull final ResponseListener<Long> listener) {
    zcard(key, null, listener);
  }

  /**
   * Returns the number of elements held by a sorted set.
   * @param key      Sorted set ID
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zcard(@NonNull String key, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key);

    send("zcard", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #zcount(String, long, long, Options, ResponseListener)}
   */
  public void zcount(@NonNull String key, long min, long max, @NonNull final ResponseListener<Long> listener) {
    zcount(key, min, max, null, listener);
  }

  /**
   * Returns the number of elements held by a sorted set 
   * with a score between the provided min and max values.
   * @param key      Sorted set ID
   * @param min      Minimum score
   * @param max      Maximum score
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zcount(@NonNull String key, long min, long max, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("min", min)
      .put("max", max);

    send("zcount", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #zincrby(String, String, double, Options, ResponseListener)}
   */
  public MemoryStorage zincrby(@NonNull String key, @NonNull String member, double value) {
    return zincrby(key, member, value, null, null);
  }

  /**
   * {@link #zincrby(String, String, double, Options, ResponseListener)}
   */
  public MemoryStorage zincrby(@NonNull String key, @NonNull String member, double value, final ResponseListener<Double> listener) {
    return zincrby(key, member, value, null, listener);
  }

  /**
   * {@link #zincrby(String, String, double, Options, ResponseListener)}
   */
  public MemoryStorage zincrby(@NonNull String key, @NonNull String member, double value, Options options) {
    return zincrby(key, member, value, options, null);
  }

  /**
   * Increments the score of a member in a sorted set by the provided value.
   * @param  key      Sorted set ID
   * @param  member   Member to increment
   * @param  value    Increment value
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage zincrby(@NonNull String key, @NonNull String member, double value, Options options, final ResponseListener<Double> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("member", member)
        .put("value", value)
      );


    send("zincrby", query, options, listener != null ? getCallbackDouble(listener) : null);

    return this;
  }

  /**
   * {@link #zinterstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage zinterstore(@NonNull String destination, @NonNull String[] keys) {
    return zinterstore(destination, keys, null, null);
  }

  /**
   * {@link #zinterstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage zinterstore(@NonNull String destination, @NonNull String[] keys, final ResponseListener<Long> listener) {
    return zinterstore(destination, keys, null, listener);
  }

  /**
   * {@link #zinterstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage zinterstore(@NonNull String destination, @NonNull String[] keys, Options options) {
    return zinterstore(destination, keys, options, null);
  }

  /**
   * Computes the intersection of the provided sorted sets and 
   * stores the result in the destination key.
   * @param  destination Destination ID
   * @param  keys        Array of sorted set IDs
   * @param  options     [description]
   * @param  listener    [description]
   * @return this
   */
  public MemoryStorage zinterstore(@NonNull String destination, @NonNull String[] keys, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", destination);
    KuzzleJSONObject body = new KuzzleJSONObject().put("keys", new JSONArray(Arrays.asList(keys)));

    if (options != null) {
      if (options.getAggregate() != null) {
        body.put("aggregate", options.getAggregate());
      }

      if (options.getWeights() != null) {
        body.put("weights", new JSONArray(Arrays.asList(options.getWeights())));
      }
    }

    query.put("body", body);

    send("zinterstore", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #zlexcount(String, String, String, Options, ResponseListener)}
   */
  public void zlexcount(@NonNull String key, @NonNull String min, @NonNull String max, @NonNull final ResponseListener<Long> listener) {
    zlexcount(key, min, max, null, listener);
  }

  /**
   * Counts elements in a sorted set where all members have equal 
   * score, using lexicographical ordering.
   * @param key      Sorted set ID
   * @param min      Minimum value
   * @param max      Maximum value
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zlexcount(@NonNull String key, @NonNull String min, @NonNull String max, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("min", min)
      .put("max", max);

    send("zlexcount", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #zrange(String, long, long, Options, ResponseListener)}
   */
  public void zrange(@NonNull String key, long start, long stop, @NonNull final ResponseListener<JSONObject[]> listener) {
    zrange(key, start, stop, null, listener);
  }

  /**
   * Returns elements from a sorted set depending on their position in the set, 
   * from a start position index to a stop position index (inclusives).
   * @param key      Sorted set ID
   * @param start    Start position
   * @param stop     End position
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zrange(@NonNull String key, long start, long stop, Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("start", start)
      .put("stop", stop)
      .put("options", new JSONArray().put("withscores"));

    send(
      "zrange",
      query,
      options,
      new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(mapZrangeResults(response.getJSONArray("result")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      }
    );
  }

  /**
   * {@link #zrangebylex(String, String, String, Options, ResponseListener)}
   */
  public void zrangebylex(@NonNull String key, @NonNull String min, @NonNull String max, @NonNull final ResponseListener<String[]> listener) {
    zrangebylex(key, min, max, null, listener);
  }

  /**
   * Returns elements in a sorted set where all members have equal 
   * score, using lexicographical ordering.
   * @param key      Sorted set ID
   * @param min      Minimum value
   * @param max      Maximum value
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zrangebylex(@NonNull String key, @NonNull String min, @NonNull String max, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("min", min)
      .put("max", max);

    if (options != null) {
      if (options.getLimit() != null) {
        query.put("limit", new JSONArray(Arrays.asList(options.getLimit())));
      }
    }

    send("zrangebylex", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #zrangebyscore(String, double, double, Options, ResponseListener)}
   */
  public void zrangebyscore(@NonNull String key, double min, double max, @NonNull final ResponseListener<JSONObject[]> listener) {
    zrangebyscore(key, min, max, null, listener);
  }

  /**
   * Returns all the elements in the sorted set at key with a 
   * score between min and max (inclusive). T
   * @param key      Sorted set ID
   * @param min      Minimum score
   * @param max      Maximum score
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zrangebyscore(@NonNull String key, double min, double max, Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("min", min)
      .put("max", max)
      .put("options", new JSONArray().put("withscores"));

    if (options != null) {
      if (options.getLimit() != null) {
        query.put("limit", new JSONArray(Arrays.asList(options.getLimit())));
      }
    }

    send(
      "zrangebyscore",
      query,
      options,
      new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(mapZrangeResults(response.getJSONArray("result")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      }
    );
  }

  /**
   * {@link #zrank(String, String, Options, ResponseListener)}
   */
  public void zrank(@NonNull String key, @NonNull String member, @NonNull final ResponseListener<Long> listener) {
    zrank(key, member, null, listener);
  }

  /**
   * Returns the position of an element in a sorted set, with scores in ascending order.
   * @param key      Sorted set ID
   * @param member   Member value
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zrank(@NonNull String key, @NonNull String member, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key).put("member", member);

    send("zrank", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #zrem(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage zrem(@NonNull String key, @NonNull String[] members) {
    return zrem(key, members, null, null);
  }

  /**
   * {@link #zrem(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage zrem(@NonNull String key, @NonNull String[] members, final ResponseListener<Long> listener) {
    return zrem(key, members, null, listener);
  }

  /**
   * {@link #zrem(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage zrem(@NonNull String key, @NonNull String[] members, Options options) {
    return zrem(key, members, options, null);
  }

  /**
   * Removes members from a sorted set.
   * @param  key      Sorted set ID
   * @param  members  Members to remove
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage zrem(@NonNull String key, @NonNull String[] members, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("members", new JSONArray(Arrays.asList(members)))
      );

    send("zrem", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #zremrangebylex(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage zremrangebylex(@NonNull String key, @NonNull String min, @NonNull String max) {
    return zremrangebylex(key, min, max, null, null);
  }

  /**
   * {@link #zremrangebylex(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage zremrangebylex(@NonNull String key, @NonNull String min, @NonNull String max, final ResponseListener<Long> listener) {
    return zremrangebylex(key, min, max, null, listener);
  }

  /**
   * {@link #zremrangebylex(String, String, String, Options, ResponseListener)}
   */
  public MemoryStorage zremrangebylex(@NonNull String key, @NonNull String min, @NonNull String max, Options options) {
    return zremrangebylex(key, min, max, options, null);
  }

  /**
   * Removes members from a sorted set where all elements have 
   * the same score, using lexicographical ordering. 
   * @param  key      Sorted set ID
   * @param  min      Minimum value
   * @param  max      Maximum value
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage zremrangebylex(@NonNull String key, @NonNull String min, @NonNull String max, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("min", min)
        .put("max", max)
      );

    send("zremrangebylex", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #zremrangebyrank(String, long, long, Options, ResponseListener)}
   */
  public MemoryStorage zremrangebyrank(@NonNull String key, long min, long max) {
    return zremrangebyrank(key, min, max, null, null);
  }

  /**
   * {@link #zremrangebyrank(String, long, long, Options, ResponseListener)}
   */
  public MemoryStorage zremrangebyrank(@NonNull String key, long min, long max, final ResponseListener<Long> listener) {
    return zremrangebyrank(key, min, max, null, listener);
  }

  /**
   * {@link #zremrangebyrank(String, long, long, Options, ResponseListener)}
   */
  public MemoryStorage zremrangebyrank(@NonNull String key, long min, long max, Options options) {
    return zremrangebyrank(key, min, max, options, null);
  }

  /**
   * Removes members from a sorted set with their position 
   * in the set
   * @param  key      Sorted set ID
   * @param  min      Start position
   * @param  max      End position
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage zremrangebyrank(@NonNull String key, long min, long max, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("start", min)
        .put("stop", max)
      );

    send("zremrangebyrank", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #zremrangebyscore(String, double, double, Options, ResponseListener)}
   */
  public MemoryStorage zremrangebyscore(@NonNull String key, double min, double max) {
    return zremrangebyscore(key, min, max, null, null);
  }

  /**
   * {@link #zremrangebyscore(String, double, double, Options, ResponseListener)}
   */
  public MemoryStorage zremrangebyscore(@NonNull String key, double min, double max, final ResponseListener<Long> listener) {
    return zremrangebyscore(key, min, max, null, listener);
  }

  /**
   * {@link #zremrangebyscore(String, double, double, Options, ResponseListener)}
   */
  public MemoryStorage zremrangebyscore(@NonNull String key, double min, double max, Options options) {
    return zremrangebyscore(key, min, max, options, null);
  }

  /**
   * Removes members from a sorted set with a score 
   * @param  key      Sorted set ID
   * @param  min      Minimum score
   * @param  max      Maximum score
   * @param  options  [description]
   * @param  listener [description]
   * @return this
   */
  public MemoryStorage zremrangebyscore(@NonNull String key, double min, double max, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("body", new KuzzleJSONObject()
        .put("min", min)
        .put("max", max)
      );

    send("zremrangebyscore", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }

  /**
   * {@link #zrevrange(String, long, long, Options, ResponseListener)}
   */
  public void zrevrange(@NonNull String key, long start, long stop, @NonNull final ResponseListener<JSONObject[]> listener) {
    zrevrange(key, start, stop, null, listener);
  }

  /**
   * Identical to zrange, except that the sorted set is traversed in descending order.
   * @param key      Sorted set ID
   * @param start    Start position
   * @param stop     End position
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zrevrange(@NonNull String key, long start, long stop, Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("start", start)
      .put("stop", stop)
      .put("options", new JSONArray().put("withscores"));

    send(
      "zrevrange",
      query,
      options,
      new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(mapZrangeResults(response.getJSONArray("result")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      }
    );
  }

  /**
   * {@link #zrevrangebylex(String, String, String, Options, ResponseListener)}
   */
  public void zrevrangebylex(@NonNull String key, @NonNull String min, @NonNull String max, @NonNull final ResponseListener<String[]> listener) {
    zrevrangebylex(key, min, max, null, listener);
  }

  /**
   * Identical to zrangebylex except that the sorted set is traversed in descending order.
   * @param key      Sorted set ID
   * @param min      Minimum value
   * @param max      Maximum value
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zrevrangebylex(@NonNull String key, @NonNull String min, @NonNull String max, Options options, @NonNull final ResponseListener<String[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("min", min)
      .put("max", max);

    if (options != null) {
      if (options.getLimit() != null) {
        query.put("limit", new JSONArray(Arrays.asList(options.getLimit())));
      }
    }

    send("zrevrangebylex", query, options, getCallbackStringArray(listener));
  }

  /**
   * {@link #zrevrangebyscore(String, double, double, Options, ResponseListener)}
   */
  public void zrevrangebyscore(@NonNull String key, double min, double max, @NonNull final ResponseListener<JSONObject[]> listener) {
    zrevrangebyscore(key, min, max, null, listener);
  }

  /**
   * Identical to zrangebyscore except that the sorted set is traversed in descending order.
   * @param key      Sorted set ID
   * @param min      Minimum score
   * @param max      Maximum score
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zrevrangebyscore(@NonNull String key, double min, double max, Options options, @NonNull final ResponseListener<JSONObject[]> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("min", min)
      .put("max", max)
      .put("options", new JSONArray().put("withscores"));

    if (options != null) {
      if (options.getLimit() != null) {
        query.put("limit", new JSONArray(Arrays.asList(options.getLimit())));
      }
    }

    send(
      "zrevrangebyscore",
      query,
      options,
      new ResponseListener<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          try {
            listener.onSuccess(mapZrangeResults(response.getJSONArray("result")));
          }
          catch(JSONException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public void onError(JSONObject error) {
          listener.onError(error);
        }
      }
    );
  }

  /**
   * {@link #zrevrank(String, String, Options, ResponseListener)}
   */
  public void zrevrank(@NonNull String key, @NonNull String member, @NonNull final ResponseListener<Long> listener) {
    zrevrank(key, member, null, listener);
  }

  /**
   * Returns the position of an element in a sorted set, with scores in descending order. 
   * @param key      Sorted set ID
   * @param member   Member value
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zrevrank(@NonNull String key, @NonNull String member, Options options, @NonNull final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key).put("member", member);

    send("zrevrank", query, options, getCallbackLong(listener));
  }

  /**
   * {@link #zscan(String, long, Options, ResponseListener)}
   */
  public void zscan(@NonNull String key, long cursor, @NonNull final ResponseListener<JSONObject> listener) {
    zscan(key, cursor, null, listener);
  }

  /**
   * Identical to scan, except that zscan iterates the members held by a sorted set.
   * @param key      Sorted set ID
   * @param cursor   Cursor position (0 to start an iteration)
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zscan(@NonNull String key, long cursor, Options options, @NonNull final ResponseListener<JSONObject> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject()
      .put("_id", key)
      .put("cursor", cursor);

    if (options != null) {
      if (options.getCount() != null) {
        query.put("count", options.getCount());
      }

      if (options.getMatch() != null) {
        query.put("match", options.getMatch());
      }
    }

    send("zscan", query, options, getCallbackScanResult(listener));
  }

  /**
   * {@link #zscore(String, String, Options, ResponseListener)}
   */
  public void zscore(@NonNull String key, @NonNull String member, @NonNull final ResponseListener<Double> listener) {
    zscore(key, member, null, listener);
  }

  /**
   * Returns the score of a member in a sorted set.
   * @param key      Sorted set ID
   * @param member   Member value
   * @param options Request options
   * @param listener Response callback listener
   */
  public void zscore(@NonNull String key, @NonNull String member, Options options, @NonNull final ResponseListener<Double> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", key).put("member", member);

    send("zscore", query, options, getCallbackDouble(listener));
  }

  /**
   * {@link #zunionstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage zunionstore(@NonNull String destination, @NonNull String[] keys) {
    return zunionstore(destination, keys, null, null);
  }

  /**
   * {@link #zunionstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage zunionstore(@NonNull String destination, @NonNull String[] keys, final ResponseListener<Long> listener) {
    return zunionstore(destination, keys, null, listener);
  }

  /**
   * {@link #zunionstore(String, String[], Options, ResponseListener)}
   */
  public MemoryStorage zunionstore(@NonNull String destination, @NonNull String[] keys, Options options) {
    return zunionstore(destination, keys, options, null);
  }

  /**
   * Computes the union of the provided sorted sets and stores the result in the destination key.
   * @param  destination Destination key ID
   * @param  keys        Array of sorted set IDs
   * @param  options     [description]
   * @param  listener    [description]
   * @return this
   */
  public MemoryStorage zunionstore(@NonNull String destination, @NonNull String[] keys, Options options, final ResponseListener<Long> listener) {
    KuzzleJSONObject query = new KuzzleJSONObject().put("_id", destination);
    KuzzleJSONObject body = new KuzzleJSONObject().put("keys", new JSONArray(Arrays.asList(keys)));

    if (options != null) {
      if (options.getAggregate() != null) {
        body.put("aggregate", options.getAggregate());
      }

      if (options.getWeights() != null) {
        body.put("weights", new JSONArray(Arrays.asList(options.getWeights())));
      }
    }

    query.put("body", body);

    send("zunionstore", query, options, listener != null ? getCallbackLong(listener) : null);

    return this;
  }
}
