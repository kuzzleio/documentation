package io.kuzzle.test.core.KuzzleMemoryStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.skyscreamer.jsonassert.JSONAssert;

import java.lang.reflect.Method;
import java.util.Arrays;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.MemoryStorage;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.util.KuzzleJSONObject;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class methodsTest {
  private Kuzzle kuzzle;
  private Kuzzle.QueryArgs queryArgs = new Kuzzle.QueryArgs();
  private MemoryStorage ms;
  private ArgumentCaptor capturedQueryArgs;
  private ArgumentCaptor capturedQuery;

  private static <T> T[] concat(T[] first, T[] second) {
    T[] result = Arrays.copyOf(first, first.length + second.length);
    System.arraycopy(second, 0, result, first.length, second.length);
    return result;
  }

  private void validate(String command, JSONObject expected, boolean withOpts) throws JSONException {
    assertEquals(((Kuzzle.QueryArgs) capturedQueryArgs.getValue()).controller, "ms");
    assertEquals(((Kuzzle.QueryArgs) capturedQueryArgs.getValue()).action, command);

    /*
     if options are provided, the expected result should come first
     as the assertion expects the second argument to contain at least
     the JSON properties contained in the first one

     And vice versa without options provided
     */
    if (withOpts) {
      JSONAssert.assertEquals(expected, (JSONObject)capturedQuery.getValue(), false);
    }
    else {
      JSONAssert.assertEquals(capturedQuery.getValue().toString(), expected, false);
    }
  }

  private ResponseListener<Long> verifyResultLong(long returnValue, final long expected) throws JSONException {
    mockResult(new KuzzleJSONObject().put("result", returnValue));

    return new ResponseListener<Long>() {
      @Override
      public void onSuccess(Long response) {
        assertEquals((long)response, expected);
      }

      @Override
      public void onError(JSONObject error) {
      }
    };
  }

  private ResponseListener<Integer> verifyResultInt(int returnValue, final int expected) throws JSONException {
    mockResult(new KuzzleJSONObject().put("result", returnValue));

    return new ResponseListener<Integer>() {
      @Override
      public void onSuccess(Integer response) {
        assertEquals((int)response, expected);
      }

      @Override
      public void onError(JSONObject error) {
      }
    };
  }

  private ResponseListener<String> verifyResultString(String returnValue, final String expected) throws JSONException {
    mockResult(new KuzzleJSONObject().put("result", returnValue));

    return new ResponseListener<String>() {
      @Override
      public void onSuccess(String response) {
        assertEquals(response, expected);
      }

      @Override
      public void onError(JSONObject error) {
      }
    };
  }

  private ResponseListener<String[]> verifyResultStringArray(String returnValue, final String[] expected) throws JSONException {
    mockResult(new KuzzleJSONObject().put("result", returnValue));

    return new ResponseListener<String[]>() {
      @Override
      public void onSuccess(String[] response) {
        assertArrayEquals(response, expected);
      }

      @Override
      public void onError(JSONObject error) {
      }
    };
  }

  private ResponseListener<String[]> verifyResultStringArray(final JSONArray returnValue, final String[] expected) throws JSONException {
    mockResult(new KuzzleJSONObject().put("result", returnValue));

    return new ResponseListener<String[]>() {
      @Override
      public void onSuccess(String[] response) {
        assertArrayEquals(response, expected);
      }

      @Override
      public void onError(JSONObject error) {
      }
    };
  }

  private ResponseListener<JSONObject[]> verifyResultJSONObjectArray(JSONArray value, final JSONObject[] expected) throws JSONException {
    mockResult(new KuzzleJSONObject().put("result", value));

    return new ResponseListener<JSONObject[]>() {
      @Override
      public void onSuccess(JSONObject[] response) {
        for (int i = 0; i < response.length; i++) {
          try {
            JSONAssert.assertEquals(response[i], expected[i], false);
          }
          catch (JSONException e) {
            throw new RuntimeException(e);
          }
        }
      }

      @Override
      public void onError(JSONObject error) {

      }
    };
  }

  private ResponseListener<Double> verifyResultDouble(String returnValue, final double expected) throws JSONException {
    mockResult(new KuzzleJSONObject().put("result", returnValue));

    return new ResponseListener<Double>() {
      @Override
      public void onSuccess(Double response) {
        assertEquals(response, expected, 10e-12);
      }

      @Override
      public void onError(JSONObject error) {
      }
    };
  }

  private ResponseListener<JSONObject> verifyResultJSONObject(JSONArray value, final JSONObject expected) throws JSONException {
    mockResult(new KuzzleJSONObject().put("result", value));

    return new ResponseListener<JSONObject>() {
      @Override
      public void onSuccess(JSONObject response) {
        try {
          JSONAssert.assertEquals(response, expected, false);
        }
        catch(JSONException e) {
          fail(e.getMessage());
        }
      }

      @Override
      public void onError(JSONObject error) {

      }
    };
  }

  private void mockResult(final KuzzleJSONObject raw) throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(raw);
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
  }

  private void testReadMethod(String command, Class[] proto, Object[] args, Options opts, JSONObject expected) throws Exception {
    Class[] overloadedProto;
    Object[] overloadedArgs;
    Method mscommand;

    // With options and listener
    overloadedProto = concat(proto, new Class[]{Options.class, ResponseListener.class});
    overloadedArgs = concat(args, new Object[]{opts, mock(ResponseListener.class)});
    mscommand = MemoryStorage.class.getDeclaredMethod(command, overloadedProto);
    mscommand.invoke(ms, overloadedArgs);
    verify(kuzzle).query((Kuzzle.QueryArgs) capturedQueryArgs.capture(), (JSONObject)capturedQuery.capture(), eq(opts), any(OnQueryDoneListener.class));
    validate(command, expected, true);

    // Without options, with listener
    overloadedProto = concat(proto, new Class[]{ResponseListener.class});
    overloadedArgs = concat(args, new Object[]{mock(ResponseListener.class)});
    mscommand = MemoryStorage.class.getDeclaredMethod(command, overloadedProto);
    mscommand.invoke(ms, overloadedArgs);
    verify(kuzzle).query((Kuzzle.QueryArgs) capturedQueryArgs.capture(), (JSONObject)capturedQuery.capture(), eq((Options)null), any(OnQueryDoneListener.class));
    validate(command, expected, false);
  }

  private void testWriteMethod(String command, Class[] proto, Object[] args, Options opts, JSONObject expected) throws Exception {
    Class[] overloadedProto;
    Object[] overloadedArgs;
    Method mscommand = MemoryStorage.class.getDeclaredMethod(command, proto);

    // Without options nor listener
    mscommand.invoke(ms, args);
    verify(kuzzle).query((Kuzzle.QueryArgs) capturedQueryArgs.capture(), (JSONObject)capturedQuery.capture(), eq((Options)null));
    validate(command, expected, false);

    // With options, without listener
    overloadedProto = concat(proto, new Class[]{Options.class});
    overloadedArgs = concat(args, new Object[]{opts});
    mscommand = MemoryStorage.class.getDeclaredMethod(command, overloadedProto);
    mscommand.invoke(ms, overloadedArgs);
    verify(kuzzle).query((Kuzzle.QueryArgs) capturedQueryArgs.capture(), (JSONObject)capturedQuery.capture(), eq(opts));
    validate(command, expected, true);

    // With options and listener
    overloadedProto = concat(overloadedProto, new Class[]{ResponseListener.class});
    overloadedArgs = concat(overloadedArgs, new Object[]{mock(ResponseListener.class)});
    mscommand = MemoryStorage.class.getDeclaredMethod(command, overloadedProto);
    mscommand.invoke(ms, overloadedArgs);
    verify(kuzzle).query((Kuzzle.QueryArgs) capturedQueryArgs.capture(), (JSONObject)capturedQuery.capture(), eq(opts), any(OnQueryDoneListener.class));
    validate(command, expected, true);

    // Without options, with listener
    overloadedProto = concat(proto, new Class[]{ResponseListener.class});
    overloadedArgs = concat(args, new Object[]{mock(ResponseListener.class)});
    mscommand = MemoryStorage.class.getDeclaredMethod(command, overloadedProto);
    mscommand.invoke(ms, overloadedArgs);
    verify(kuzzle).query((Kuzzle.QueryArgs) capturedQueryArgs.capture(), (JSONObject)capturedQuery.capture(), eq((Options)null), any(OnQueryDoneListener.class));
    validate(command, expected, false);
  }

  @Before
  public void setUp() {
    kuzzle = mock(Kuzzle.class);
    ms = new MemoryStorage(kuzzle);
    capturedQueryArgs = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    capturedQuery = ArgumentCaptor.forClass(io.kuzzle.sdk.util.KuzzleJSONObject.class);
    queryArgs.controller = "ms";
  }

  @Test
  public void append() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"foo", "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("body", new JSONObject()
        .put("value", "bar")
      );

    this.testWriteMethod("append", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.append("foo", "bar", listener);
  }

  @Test
  public void bitcount() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setStart((long)13)
      .setEnd((long)42);
    Object[] args = new Object[]{"foo"};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("start", 13)
      .put("end", 42);

    this.testReadMethod("bitcount", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.bitcount("foo", listener);
  }

  @Test
  public void bitop() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar"};
    Object[] args = new Object[]{"foo", "xor", keys};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("body", new JSONObject()
        .put("operation", "xor")
        .put("keys", new JSONArray(Arrays.asList(keys)))
      );

    this.testWriteMethod("bitop", new Class[]{String.class, String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.bitop("foo", "bar", keys, listener);
  }

  @Test
  public void bitpos() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setStart((long)13)
      .setEnd((long)42);
    Object[] args = new Object[]{"foo", 1};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("bit", 1)
      .put("start", 13)
      .put("end", 42);

    this.testReadMethod("bitpos", new Class[]{String.class, int.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.bitpos("foo", 1, listener);
  }

  @Test
  public void dbsize() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{};
    JSONObject expected = new JSONObject();

    this.testReadMethod("dbsize", new Class[]{}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.dbsize(listener);
  }

  @Test
  public void decr() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"foo"};
    JSONObject expected = new JSONObject().put("_id", "foo");

    this.testWriteMethod("decr", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.decr("foo", listener);
  }

  @Test
  public void decrby() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"foo", -42};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("body", new JSONObject()
        .put("value", -42)
      );

    this.testWriteMethod("decrby", new Class[]{String.class, long.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.decrby("foo", -42, listener);
  }

  @Test
  public void del() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{keys};
    JSONObject expected = new JSONObject()
      .put("body", new JSONObject()
        .put("keys", new JSONArray(Arrays.asList(keys)))
      );

    this.testWriteMethod("del", new Class[]{String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.del(keys, listener);
  }

  @Test
  public void exists() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{keys};
    JSONObject expected = new JSONObject()
      .put("keys", new JSONArray(Arrays.asList(keys)));

    this.testReadMethod("exists", new Class[]{String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.exists(keys, listener);
  }

  @Test
  public void expire() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"foo", 42};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("body", new JSONObject()
        .put("seconds", 42)
      );

    this.testWriteMethod("expire", new Class[]{String.class, long.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.expire("foo", 42, listener);
  }

  @Test
  public void expireat() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"foo", 42};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("body", new JSONObject()
        .put("timestamp", 42)
      );

    this.testWriteMethod("expireat", new Class[]{String.class, long.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.expireat("foo", 42, listener);
  }

  @Test
  public void flushdb() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{};
    JSONObject expected = new JSONObject();

    this.testWriteMethod("flushdb", new Class[]{}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("OK", "OK");
    ms.flushdb(listener);
  }

  @Test
  public void geoadd() throws Exception {
    Options opts = new Options().setQueuable(true);
    JSONObject[] points = new JSONObject[]{
      new JSONObject()
        .put("lon", 13.361389)
        .put("lat", 38.115556)
        .put("name", "Palermo"),
      new JSONObject()
        .put("lon", 15.087269)
        .put("lat", 37.502669)
        .put("name", "Catania")
    };
    Object[] args = new Object[]{"foo", points};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("body", new JSONObject()
        .put("points", new JSONArray(Arrays.asList(points)))
      );

    this.testWriteMethod("geoadd", new Class[]{String.class, JSONObject[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.geoadd("foo", points, listener);
  }

  @Test
  public void geodist() throws Exception {
    Options opts = new Options().setQueuable(true).setUnit("ft");
    Object[] args = new Object[]{"key", "Palermo", "Catania"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("member1", "Palermo")
      .put("member2", "Catania")
      .put("unit", "ft");

    this.testReadMethod("geodist", new Class[]{String.class, String.class, String.class}, args, opts, expected);

    ResponseListener<Double> listener = verifyResultDouble("166274.1516", 166274.1516);
    ms.geodist("foo", "Palermo", "Catania", listener);
  }

  @Test
  public void geohash() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] members = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", members};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("members", new JSONArray(Arrays.asList(members)));

    this.testReadMethod("geohash", new Class[]{String.class, String[].class}, args, opts, expected);

    String[] expectedResult = new String[]{"sqc8b49rny0", "sqdtr74hyu0"};
    JSONArray rawResult = new JSONArray().put("sqc8b49rny0").put("sqdtr74hyu0");
    ResponseListener<String[]> listener = verifyResultStringArray(rawResult, expectedResult);
    ms.geohash("key", members, listener);
  }

  @Test
  public void geopos() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] members = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", members};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("members", new JSONArray(Arrays.asList(members)));

    this.testReadMethod("geopos", new Class[]{String.class, String[].class}, args, opts, expected);

    JSONArray rawResult = new JSONArray()
      .put(new JSONArray().put("13.361389").put("38.115556"))
      .put(new JSONArray().put("15.087269").put("37.502669"));

    mockResult(new KuzzleJSONObject().put("result", rawResult));

    final Double[][] expectedResult = new Double[][]{
      {13.361389, 38.115556},
      {15.087269, 37.502669}
    };

    ms.geopos("key", members, new ResponseListener<Double[][]>() {
      @Override
      public void onSuccess(Double[][] response) {
        for (int i = 0; i < response.length; i++) {
          assertArrayEquals(response[i], expectedResult[i]);
        }
      }

      @Override
      public void onError(JSONObject error) {
        fail("onError callback should not have been invoked");
      }
    });
  }

  @Test
  public void georadius() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setCount((long)10)
      .setSort("asc")
      .setWithcoord(true)
      .setWithdist(true);
    Object[] args = new Object[]{"key", 15, 37, 200, "km"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("lon", 15)
      .put("lat", 37)
      .put("distance", 200)
      .put("unit", "km");

    this.testReadMethod("georadius", new Class[]{String.class, double.class, double.class, double.class, String.class}, args, opts, expected);

    // without coordinates nor distance
    opts.setWithcoord(false).setWithdist(false);
    ResponseListener<JSONObject[]> listener = verifyResultJSONObjectArray(
      new JSONArray().put("Palermo").put("Catania"),
      new JSONObject[]{
        new JSONObject().put("name", "Palermo"),
        new JSONObject().put("name", "Catania")
      }
    );
    ms.georadius("key", 15, 37, 200, "km", opts, listener);

    // with coordinates, without distance
    opts.setWithcoord(true).setWithdist(false);
    listener = verifyResultJSONObjectArray(
      new JSONArray().put(
        new JSONArray()
          .put("Palermo").put("190.4424")
      ).put(
        new JSONArray()
          .put("Catania").put("56.4413")
      ),
      new JSONObject[]{
        new JSONObject().put("name", "Palermo").put("distance", 190.4424),
        new JSONObject().put("name", "Catania").put("distance", 56.4413)
      }
    );
    ms.georadius("key", 15, 37, 200, "km", opts, listener);

    // without coordinates, with distance
    opts.setWithcoord(false).setWithdist(true);
    listener = verifyResultJSONObjectArray(
      new JSONArray().put(
        new JSONArray()
          .put("Palermo").put(new JSONArray().put("13.3613").put("38.1155"))
      ).put(
        new JSONArray()
          .put("Catania").put(new JSONArray().put("15.0872").put("37.5026"))
      ),
      new JSONObject[]{
        new JSONObject().put("name", "Palermo")
          .put("coordinates", new JSONArray().put(13.3613).put(38.1155)),
        new JSONObject().put("name", "Catania")
          .put("coordinates", new JSONArray().put(15.0872).put(37.5026))
      }
    );
    ms.georadius("key", 15, 37, 200, "km", opts, listener);

    // with coordinates, with distance
    opts.setWithcoord(true).setWithdist(true);
    listener = verifyResultJSONObjectArray(
      new JSONArray().put(
        new JSONArray()
          .put("Palermo").put(new JSONArray().put("13.3613").put("38.1155")).put("190.4424")
      ).put(
        new JSONArray()
          .put("Catania").put("56.4413").put(new JSONArray().put("15.0872").put("37.5026"))
      ),
      new JSONObject[]{
        new JSONObject().put("name", "Palermo")
          .put("distance", 190.4424)
          .put("coordinates", new JSONArray().put(13.3613).put(38.1155)),
        new JSONObject().put("name", "Catania")
          .put("distance", 56.4413)
          .put("coordinates", new JSONArray().put(15.0872).put(37.5026))
      }
    );
    ms.georadius("key", 15, 37, 200, "km", opts, listener);
  }

  @Test
  public void georadiusbymember() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setCount((long)10)
      .setSort("asc")
      .setWithcoord(true)
      .setWithdist(true);
    Object[] args = new Object[]{"key", "Palermo", 200, "km"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("member", "Palermo")
      .put("distance", 200)
      .put("unit", "km");

    this.testReadMethod("georadiusbymember", new Class[]{String.class, String.class, double.class, String.class}, args, opts, expected);

    // without coordinates nor distance
    opts.setWithcoord(false).setWithdist(false);
    ResponseListener<JSONObject[]> listener = verifyResultJSONObjectArray(
      new JSONArray().put("Palermo").put("Catania"),
      new JSONObject[]{
        new JSONObject().put("name", "Palermo"),
        new JSONObject().put("name", "Catania")
      }
    );
    ms.georadiusbymember("key", "Palermo", 200, "km", opts, listener);

    // with coordinates, without distance
    opts.setWithcoord(true).setWithdist(false);
    listener = verifyResultJSONObjectArray(
      new JSONArray().put(
        new JSONArray()
          .put("Palermo").put("190.4424")
      ).put(
        new JSONArray()
          .put("Catania").put("56.4413")
      ),
      new JSONObject[]{
        new JSONObject().put("name", "Palermo").put("distance", 190.4424),
        new JSONObject().put("name", "Catania").put("distance", 56.4413)
      }
    );
    ms.georadiusbymember("key", "Palermo", 200, "km", opts, listener);

    // without coordinates, with distance
    opts.setWithcoord(false).setWithdist(true);
    listener = verifyResultJSONObjectArray(
      new JSONArray().put(
        new JSONArray()
          .put("Palermo").put(new JSONArray().put("13.3613").put("38.1155"))
      ).put(
        new JSONArray()
          .put("Catania").put(new JSONArray().put("15.0872").put("37.5026"))
      ),
      new JSONObject[]{
        new JSONObject().put("name", "Palermo")
          .put("coordinates", new JSONArray().put(13.3613).put(38.1155)),
        new JSONObject().put("name", "Catania")
          .put("coordinates", new JSONArray().put(15.0872).put(37.5026))
      }
    );
    ms.georadiusbymember("key", "Palermo", 200, "km", opts, listener);

    // with coordinates, with distance
    opts.setWithcoord(true).setWithdist(true);
    listener = verifyResultJSONObjectArray(
      new JSONArray().put(
        new JSONArray()
          .put("Palermo").put(new JSONArray().put("13.3613").put("38.1155")).put("190.4424")
      ).put(
        new JSONArray()
          .put("Catania").put("56.4413").put(new JSONArray().put("15.0872").put("37.5026"))
      ),
      new JSONObject[]{
        new JSONObject().put("name", "Palermo")
          .put("distance", 190.4424)
          .put("coordinates", new JSONArray().put(13.3613).put(38.1155)),
        new JSONObject().put("name", "Catania")
          .put("distance", 56.4413)
          .put("coordinates", new JSONArray().put(15.0872).put(37.5026))
      }
    );
    ms.georadiusbymember("key", "Palermo", 200, "km", opts, listener);
  }

  @Test
  public void get() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("get", new Class[]{String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.get("key", listener);
  }

  @Test
  public void getbit() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 10};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("offset", 10);

    this.testReadMethod("getbit", new Class[]{String.class, long.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.getbit("key", 10, listener);
  }

  @Test
  public void getrange() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 13, 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("start", 13)
      .put("end", 42);

    this.testReadMethod("getrange", new Class[]{String.class, long.class, long.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.getrange("key", 13, 42, listener);
  }

  @Test
  public void getset() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "value"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject().put("value", "value"));

    this.testWriteMethod("getset", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.getset("key", "value", listener);
  }

  @Test
  public void hdel() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] fields = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", fields};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject().put("fields", new JSONArray(Arrays.asList(fields))));

    this.testWriteMethod("hdel", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.hdel("key", fields, listener);
  }

  @Test
  public void hexists() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foobar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("field", "foobar");

    this.testReadMethod("hexists", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(1, 1);
    ms.hexists("key", "foobar", listener);
  }

  @Test
  public void hget() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("field", "foo");

    this.testReadMethod("hget", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("bar", "bar");
    ms.hget("key", "foo", listener);
  }

  @Test
  public void hgetall() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("hgetall", new Class[]{String.class}, args, opts, expected);

    final KuzzleJSONObject result = new KuzzleJSONObject().put("foo", "bar");
    mockResult(new KuzzleJSONObject().put("result", result));

    ResponseListener<JSONObject> listener =new ResponseListener<JSONObject>() {
      @Override
      public void onSuccess(JSONObject response) {
        assertEquals(response, result);
      }

      @Override
      public void onError(JSONObject error) {
      }
    };

    ms.hgetall("key", listener);
  }

  @Test
  public void hincrby() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("value", 42)
        .put("field", "foo")
      );

    this.testWriteMethod("hincrby", new Class[]{String.class, String.class, long.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.hincrby("foo", "bar", 42, listener);
  }

  @Test
  public void hincrbyfloat() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", 3.14159};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("value", 3.14159)
        .put("field", "foo")
      );

    this.testWriteMethod("hincrbyfloat", new Class[]{String.class, String.class, double.class}, args, opts, expected);

    ResponseListener<Double> listener = verifyResultDouble("48.14159", 48.14159);
    ms.hincrbyfloat("foo", "bar", 3.14159, listener);
  }

  @Test
  public void hkeys() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("hkeys", new Class[]{String.class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    String[] expectedResult = new String[]{"foo", "bar", "baz"};
    ResponseListener<String[]> listener = verifyResultStringArray(result, expectedResult);
    ms.hkeys("key", listener);
  }

  @Test
  public void hlen() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("hlen", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.hlen("key", listener);
  }

  @Test
  public void hmget() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] fields = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", fields};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("fields", new JSONArray(Arrays.asList(fields)));

    this.testReadMethod("hmget", new Class[]{String.class, String[].class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    ResponseListener<String[]> listener = verifyResultStringArray(result, fields);
    ms.hmget("key", fields, listener);
  }

  @Test
  public void hmset() throws Exception {
    Options opts = new Options().setQueuable(true);
    JSONObject[] entries = new JSONObject[]{
      new JSONObject().put("field", "field1").put("value", "foo"),
      new JSONObject().put("field", "field2").put("value", "bar"),
      new JSONObject().put("field", "...").put("value", "...")
    };

    Object[] args = new Object[]{"key", entries};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("entries", new JSONArray(Arrays.asList(entries)))
      );

    this.testWriteMethod("hmset", new Class[]{String.class, JSONObject[].class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("OK", "OK");
    ms.hmset("key", entries, listener);
  }

  @Test
  public void hscan() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setCount((long)10)
      .setMatch("foo*");
    Object[] args = new Object[]{"key", 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("cursor", 42)
      .put("count", 10)
      .put("match", "foo*");

    this.testReadMethod("hscan", new Class[]{String.class, long.class}, args, opts, expected);

    JSONArray result = new JSONArray()
      .put("18")
      .put(new JSONArray()
        .put("field1")
        .put("field1 value")
        .put("field2")
        .put("field2 value")
      );

    JSONObject expectedResult = new JSONObject()
      .put("cursor", 18)
      .put("values", new JSONArray()
        .put("field1")
        .put("field1 value")
        .put("field2")
        .put("field2 value")
      );

    ResponseListener<JSONObject> listener = verifyResultJSONObject(result, expectedResult);
    ms.hscan("key", 42, listener);
  }

  @Test
  public void hset() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("field", "foo")
        .put("value", "bar")
      );

    this.testWriteMethod("hset", new Class[]{String.class, String.class, String.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.hset("key", "foo", "bar", listener);
  }

  @Test
  public void hsetnx() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("field", "foo")
        .put("value", "bar")
      );

    this.testWriteMethod("hsetnx", new Class[]{String.class, String.class, String.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.hsetnx("key", "foo", "bar", listener);
  }

  @Test
  public void hstrlen() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("field", "foo");

    this.testReadMethod("hstrlen", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.hstrlen("key", "foo", listener);
  }

  @Test
  public void hvals() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("hvals", new Class[]{String.class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    String[] expectedResult = new String[]{"foo", "bar", "baz"};
    ResponseListener<String[]> listener = verifyResultStringArray(result, expectedResult);
    ms.hvals("key", listener);
  }

  @Test
  public void incr() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"foo"};
    JSONObject expected = new JSONObject().put("_id", "foo");

    this.testWriteMethod("incr", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.incr("foo", listener);
  }

  @Test
  public void incrby() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"foo", -42};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("body", new JSONObject()
        .put("value", -42)
      );

    this.testWriteMethod("incrby", new Class[]{String.class, long.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.incrby("foo", -42, listener);
  }

  @Test
  public void incrbyfloat() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"foo", 3.14159};
    JSONObject expected = new JSONObject()
      .put("_id", "foo")
      .put("body", new JSONObject()
        .put("value", 3.14159)
      );

    this.testWriteMethod("incrbyfloat", new Class[]{String.class, double.class}, args, opts, expected);

    ResponseListener<Double> listener = verifyResultDouble("48.14159", 48.14159);
    ms.incrbyfloat("foo", 3.14159, listener);
  }

  @Test
  public void keys() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"foo*"};
    JSONObject expected = new JSONObject()
      .put("pattern", "foo*");

    this.testReadMethod("keys", new Class[]{String.class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    String[] expectedResult = new String[]{"foo", "bar", "baz"};
    ResponseListener<String[]> listener = verifyResultStringArray(result, expectedResult);
    ms.keys("foo*", listener);
  }

  @Test
  public void lindex() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 10};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("index", 10);

    this.testReadMethod("lindex", new Class[]{String.class, long.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foo", "foo");
    ms.lindex("key", 10, listener);
  }

  @Test
  public void linsert() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "before", "foo", "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("value", "bar")
        .put("position", "before")
        .put("pivot", "foo")
      );

    this.testWriteMethod("linsert", new Class[]{String.class, String.class, String.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.linsert("key", "before", "foo", "bar", listener);
  }

  @Test
  public void llen() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("llen", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.llen("key", listener);
  }

  @Test
  public void lpop() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testWriteMethod("lpop", new Class[]{String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.lpop("key", listener);
  }

  @Test
  public void lpush() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] values = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", values};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("values", new JSONArray(Arrays.asList(values)))
      );

    this.testWriteMethod("lpush", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.lpush("key", values, listener);
  }

  @Test
  public void lpushx() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "value"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject().put("value", "value"));

    this.testWriteMethod("lpushx", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.lpushx("key", "value", listener);
  }

  @Test
  public void lrange() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 13, 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("start", 13)
      .put("stop", 42);

    this.testReadMethod("lrange", new Class[]{String.class, long.class, long.class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    String[] expectedResult = new String[]{"foo", "bar", "baz"};
    ResponseListener<String[]> listener = verifyResultStringArray(result, expectedResult);
    ms.lrange("key", 13, 42, listener);
  }

  @Test
  public void lrem() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 42, "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("value", "bar")
        .put("count", 42)
      );

    this.testWriteMethod("lrem", new Class[]{String.class, long.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.lrem("key", 42, "bar", listener);
  }


  @Test
  public void lset() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 42, "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("value", "bar")
        .put("index", 42)
      );

    this.testWriteMethod("lset", new Class[]{String.class, long.class, String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foo", "foo");
    ms.lset("key", 42, "bar", listener);
  }

  @Test
  public void ltrim() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 13, 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("start", 13)
        .put("stop", 42)
      );

    this.testWriteMethod("ltrim", new Class[]{String.class, long.class, long.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foo", "foo");
    ms.ltrim("key", 13, 42, listener);
  }

  @Test
  public void mget() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};

    Object[] args = new Object[]{keys};
    JSONObject expected = new JSONObject()
      .put("keys", new JSONArray(Arrays.asList(keys)));

    this.testReadMethod("mget", new Class[]{String[].class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    ResponseListener<String[]> listener = verifyResultStringArray(result, keys);
    ms.mget(keys, listener);
  }

  @Test
  public void mset() throws Exception {
    Options opts = new Options().setQueuable(true);
    JSONObject[] entries = new JSONObject[]{
      new JSONObject().put("key", "key1").put("value", "foo"),
      new JSONObject().put("key", "key2").put("value", "bar"),
      new JSONObject().put("key", "...").put("value", "...")
    };

    Object[] args = new Object[]{entries};
    JSONObject expected = new JSONObject()
      .put("body", new JSONObject()
        .put("entries", new JSONArray(Arrays.asList(entries)))
      );

    this.testWriteMethod("mset", new Class[]{JSONObject[].class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("OK", "OK");
    ms.mset(entries, listener);
  }


  @Test
  public void msetnx() throws Exception {
    Options opts = new Options().setQueuable(true);
    JSONObject[] entries = new JSONObject[]{
      new JSONObject().put("key", "key1").put("value", "foo"),
      new JSONObject().put("key", "key2").put("value", "bar"),
      new JSONObject().put("key", "...").put("value", "...")
    };

    Object[] args = new Object[]{entries};
    JSONObject expected = new JSONObject()
      .put("body", new JSONObject()
        .put("entries", new JSONArray(Arrays.asList(entries)))
      );

    this.testWriteMethod("msetnx", new Class[]{JSONObject[].class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.msetnx(entries, listener);
  }

  @Test
  public void object() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "encoding"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("subcommand", "encoding");

    this.testReadMethod("object", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.object("key", "encoding", listener);
  }

  @Test
  public void persist() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testWriteMethod("persist", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.persist("key", listener);
  }

  @Test
  public void pexpire() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 42000};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("milliseconds", 42000)
      );

    this.testWriteMethod("pexpire", new Class[]{String.class, long.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.pexpire("key", 42000, listener);
  }

  @Test
  public void pexpireat() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 1234567890};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("timestamp", 1234567890)
      );

    this.testWriteMethod("pexpireat", new Class[]{String.class, long.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.pexpireat("key", 1234567890, listener);
  }

  @Test
  public void pfadd() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] elements = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", elements};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("elements", new JSONArray(Arrays.asList(elements)))
      );

    this.testWriteMethod("pfadd", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(123, 123);
    ms.pfadd("key", elements, listener);
  }

  @Test
  public void pfcount() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{keys};
    JSONObject expected = new JSONObject()
      .put("keys", new JSONArray(Arrays.asList(keys)));

    this.testReadMethod("pfcount", new Class[]{String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.pfcount(keys, listener);
  }

  @Test
  public void pfmerge() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar" , "baz"};
    Object[] args = new Object[]{"key", keys};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("sources", new JSONArray(Arrays.asList(keys)))
      );

    this.testWriteMethod("pfmerge", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("OK", "OK");
    ms.pfmerge("key", keys, listener);
  }

  @Test
  public void ping() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{};
    JSONObject expected = new JSONObject();

    this.testReadMethod("ping", new Class[]{}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("OK", "OK");
    ms.ping(listener);
  }

  @Test
  public void psetex() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", 42000};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("value", "foo")
        .put("milliseconds", 42000)
      );

    this.testWriteMethod("psetex", new Class[]{String.class, String.class, long.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("OK", "OK");
    ms.psetex("key", "foo", 42000, listener);
  }

  @Test
  public void pttl() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("pttl", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.pttl("key", listener);
  }

  @Test
  public void randomkey() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{};
    JSONObject expected = new JSONObject();

    this.testReadMethod("randomkey", new Class[]{}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.randomkey(listener);
  }

  @Test
  public void rename() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("newkey", "foo")
      );

    this.testWriteMethod("rename", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("OK", "OK");
    ms.rename("key", "foo", listener);
  }

  @Test
  public void renamenx() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("newkey", "foo")
      );

    this.testWriteMethod("renamenx", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(1, 1);
    ms.renamenx("key", "foo", listener);
  }

  @Test
  public void rpop() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testWriteMethod("rpop", new Class[]{String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.rpop("key", listener);
  }

  @Test
  public void rpoplpush() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "dest"};
    JSONObject expected = new JSONObject()
      .put("body", new JSONObject()
        .put("source", "key")
        .put("destination", "dest")
      );

    this.testWriteMethod("rpoplpush", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.rpoplpush("key", "dest", listener);
  }

  @Test
  public void rpush() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] values = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", values};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("values", new JSONArray(Arrays.asList(values)))
      );

    this.testWriteMethod("rpush", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.rpush("key", values, listener);
  }

  @Test
  public void rpushx() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "value"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject().put("value", "value"));

    this.testWriteMethod("rpushx", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.rpushx("key", "value", listener);
  }

  @Test
  public void sadd() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] members = new String[]{"foo", "bar", "baz"};

    Object[] args = new Object[]{"key", members};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("members", new JSONArray(Arrays.asList(members)))
      );

    this.testWriteMethod("sadd", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.sadd("key", members, listener);
  }

  @Test
  public void scan() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setCount((long)10)
      .setMatch("foo*");
    Object[] args = new Object[]{42};
    JSONObject expected = new JSONObject()
      .put("cursor", 42)
      .put("count", 10)
      .put("match", "foo*");

    this.testReadMethod("scan", new Class[]{long.class}, args, opts, expected);

    JSONArray result = new JSONArray()
      .put("18")
      .put(new JSONArray()
        .put("field1")
        .put("field1 value")
        .put("field2")
        .put("field2 value")
      );

    JSONObject expectedResult = new JSONObject()
      .put("cursor", 18)
      .put("values", result.getJSONArray(1));

    ResponseListener<JSONObject> listener = verifyResultJSONObject(result, expectedResult);
    ms.scan(42, listener);
  }

  @Test
  public void scard() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("scard", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.scard("key", listener);
  }

  @Test
  public void sdiff() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", keys};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("keys", new JSONArray(Arrays.asList(keys)));

    this.testReadMethod("sdiff", new Class[]{String.class, String[].class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    ResponseListener<String[]> listener = verifyResultStringArray(result, keys);
    ms.sdiff("key", keys, listener);
  }

  @Test
  public void sdiffstore() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", keys, "dest"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("keys", new JSONArray(Arrays.asList(keys)))
        .put("destination", "dest")
    );

    this.testWriteMethod("sdiffstore", new Class[]{String.class, String[].class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.sdiffstore("key", keys, "dest", listener);
  }

  @Test
  public void set() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setEx((long)123)
      .setNx(true)
      .setPx((long)456)
      .setXx(false);
    Object[] args = new Object[]{"key", "value"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("value", "value")
        .put("ex", 123)
        .put("nx", true)
        .put("px", 456)
        .put("xx", false)
      );

    this.testWriteMethod("set", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.set("key", "value", listener);
  }

  @Test
  public void setex() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("value", "foo")
        .put("seconds", 42)
      );

    this.testWriteMethod("setex", new Class[]{String.class, String.class, long.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("OK", "OK");
    ms.setex("key", "foo", 42, listener);
  }

  @Test
  public void setnx() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "value"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject().put("value", "value"));

    this.testWriteMethod("setnx", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(1, 1);
    ms.setnx("key", "value", listener);
  }

  @Test
  public void sinter() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{keys};
    JSONObject expected = new JSONObject()
      .put("keys", new JSONArray(Arrays.asList(keys)));

    this.testReadMethod("sinter", new Class[]{String[].class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    ResponseListener<String[]> listener = verifyResultStringArray(result, keys);
    ms.sinter(keys, listener);
  }

  @Test
  public void sinterstore() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", keys};
    JSONObject expected = new JSONObject()
      .put("body", new JSONObject()
        .put("keys", new JSONArray(Arrays.asList(keys)))
        .put("destination", "key")
      );

    this.testWriteMethod("sinterstore", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.sinterstore("key", keys, listener);
  }

  @Test
  public void sismember() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("member", "foo");

    this.testReadMethod("sismember", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(1, 1);
    ms.sismember("key", "foo", listener);
  }

  @Test
  public void smembers() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("smembers", new Class[]{String.class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    String[] expectedResult = new String[]{"foo", "bar", "baz"};
    ResponseListener<String[]> listener = verifyResultStringArray(result, expectedResult);
    ms.smembers("key", listener);
  }

  @Test
  public void smove() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("destination", "foo")
        .put("member", "bar")
      );

    this.testWriteMethod("smove", new Class[]{String.class, String.class, String.class}, args, opts, expected);

    ResponseListener<Integer> listener = verifyResultInt(1, 1);
    ms.smove("key", "foo", "bar", listener);
  }

  @Test
  public void sort() throws Exception {
    String[] array = new String[]{"foo", "bar", "baz"};
    Integer[] limit = new Integer[]{13, 42};
    Options opts = new Options()
      .setQueuable(true)
      .setAlpha(true)
      .setBy("foobar")
      .setDirection("asc")
      .setGet(array)
      .setLimit(limit);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("alpha", true)
      .put("by", "foobar")
      .put("direction", "asc")
      .put("get", new JSONArray(Arrays.asList(array)))
      .put("limit", new JSONArray(Arrays.asList(limit)));

    this.testReadMethod("sort", new Class[]{String.class}, args, opts, expected);

    JSONArray rawResult = new JSONArray().put("foo").put("bar").put("baz");
    ResponseListener<String[]> listener = verifyResultStringArray(rawResult, array);
    ms.sort("key", listener);
  }

  @Test
  public void spop() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setCount((long)10);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject().put("count", 10));

    this.testWriteMethod("spop", new Class[]{String.class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    String[] expectedResult = new String[]{"foo", "bar", "baz"};
    ResponseListener<String[]> listener = verifyResultStringArray(result, expectedResult);
    ms.spop("key", listener);

    listener = verifyResultStringArray("foo", new String[]{"foo"});
    ms.spop("key", listener);
  }

  @Test
  public void srandmember() throws Exception {
    Options opts = new Options().setQueuable(true).setCount((long)10);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("count", 10);

    this.testReadMethod("srandmember", new Class[]{String.class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    String[] expectedResult = new String[]{"foo", "bar", "baz"};
    ResponseListener<String[]> listener = verifyResultStringArray(result, expectedResult);
    ms.srandmember("key", listener);

    listener = verifyResultStringArray("foo", new String[]{"foo"});
    ms.srandmember("key", listener);
  }

  @Test
  public void srem() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] members = new String[]{"foo", "bar", "baz"};

    Object[] args = new Object[]{"key", members};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("members", new JSONArray(Arrays.asList(members)))
      );

    this.testWriteMethod("srem", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.srem("key", members, listener);
  }

  @Test
  public void sscan() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setCount((long)10)
      .setMatch("foo*");
    Object[] args = new Object[]{"key", 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("cursor", 42)
      .put("count", 10)
      .put("match", "foo*");

    this.testReadMethod("sscan", new Class[]{String.class, long.class}, args, opts, expected);

    JSONArray result = new JSONArray()
      .put("18")
      .put(new JSONArray()
        .put("field1")
        .put("field1 value")
        .put("field2")
        .put("field2 value")
      );

    JSONObject expectedResult = new JSONObject()
      .put("cursor", 18)
      .put("values", result.getJSONArray(1));

    ResponseListener<JSONObject> listener = verifyResultJSONObject(result, expectedResult);
    ms.sscan("key", 42, listener);
  }

  @Test
  public void strlen() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("strlen", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.strlen("key", listener);
  }

  @Test
  public void sunion() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{keys};
    JSONObject expected = new JSONObject()
      .put("keys", new JSONArray(Arrays.asList(keys)));

    this.testReadMethod("sunion", new Class[]{String[].class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    ResponseListener<String[]> listener = verifyResultStringArray(result, keys);
    ms.sunion(keys, listener);
  }

  @Test
  public void sunionstore() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", keys};
    JSONObject expected = new JSONObject()
      .put("body", new JSONObject()
        .put("keys", new JSONArray(Arrays.asList(keys)))
        .put("destination", "key")
      );

    this.testWriteMethod("sunionstore", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.sunionstore("key", keys, listener);
  }

  @Test
  public void time() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{};
    JSONObject expected = new JSONObject();

    this.testReadMethod("time", new Class[]{}, args, opts, expected);

    mockResult(new KuzzleJSONObject().put("result", new JSONArray().put("123").put("456")));

    ms.time(new ResponseListener<Long[]>() {
      @Override
      public void onSuccess(Long[] response) {
        assertArrayEquals(response, new Long[]{Long.valueOf(123), Long.valueOf(456)});
      }

      @Override
      public void onError(JSONObject error) {

      }
    });
  }

  @Test
  public void touch() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{keys};
    JSONObject expected = new JSONObject()
      .put("body", new JSONObject()
        .put("keys", new JSONArray(Arrays.asList(keys)))
      );

    this.testWriteMethod("touch", new Class[]{String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.touch(keys, listener);
  }

  @Test
  public void ttl() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("ttl", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.ttl("key", listener);
  }

  @Test
  public void type() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("type", new Class[]{String.class}, args, opts, expected);

    ResponseListener<String> listener = verifyResultString("foobar", "foobar");
    ms.type("key", listener);
  }

  @Test
  public void zadd() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setCh(true)
      .setIncr(true)
      .setNx(true)
      .setXx(false);
    JSONObject[] elements = new JSONObject[]{
      new JSONObject().put("score", 1).put("member", "foo"),
      new JSONObject().put("score", 2).put("member", "bar"),
      new JSONObject().put("score", 3).put("member", "baz")
    };


    Object[] args = new Object[]{"key", elements};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("elements", new JSONArray(Arrays.asList(elements)))
        .put("ch", true)
        .put("incr", true)
        .put("nx", true)
        .put("xx", false)
      );

    this.testWriteMethod("zadd", new Class[]{String.class, JSONObject[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zadd("key", elements, listener);
  }

  @Test
  public void zcard() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key"};
    JSONObject expected = new JSONObject()
      .put("_id", "key");

    this.testReadMethod("zcard", new Class[]{String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zcard("key", listener);
  }

  @Test
  public void zcount() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 13, 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("min", 13)
      .put("max", 42);

    this.testReadMethod("zcount", new Class[]{String.class, long.class, long.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zcount("key", 13, 42, listener);
  }

  @Test
  public void zincrby() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", 3.14159};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("value", 3.14159)
        .put("member", "foo")
      );

    this.testWriteMethod("zincrby", new Class[]{String.class, String.class, double.class}, args, opts, expected);

    ResponseListener<Double> listener = verifyResultDouble("48.14159", 48.14159);
    ms.zincrby("foo", "bar", 3.14159, listener);
  }

  @Test
  public void zinterstore() throws Exception {
    Integer[] weights = new Integer[]{1, 2, 3};
    Options opts = new Options()
      .setQueuable(true)
      .setAggregate("max")
      .setWeights(weights);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", keys};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("keys", new JSONArray(Arrays.asList(keys)))
        .put("aggregate", "max")
        .put("weights", new JSONArray(Arrays.asList(weights)))
      );

    this.testWriteMethod("zinterstore", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zinterstore("key", keys, listener);
  }

  @Test
  public void zlexcount() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("min", "foo")
      .put("max", "bar");

    this.testReadMethod("zlexcount", new Class[]{String.class, String.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zlexcount("key", "foo", "bar", listener);
  }

  @Test
  public void zrange() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 13, 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("start", 13)
      .put("stop", 42)
      .put("options", new JSONArray().put("withscores"));

    this.testReadMethod("zrange", new Class[]{String.class, long.class, long.class}, args, opts, expected);

    ResponseListener<JSONObject[]> listener = verifyResultJSONObjectArray(
      new JSONArray().put("foo").put("3.14159").put("bar").put("123.456"),
      new JSONObject[]{
        new JSONObject().put("member", "foo").put("score", 3.14159),
        new JSONObject().put("member", "bar").put("score", 123.456)
      }
    );
    ms.zrange("key", 13, 42, listener);
  }

  @Test
  public void zrangebylex() throws Exception {
    Integer[] limit = new Integer[]{1, 2};
    Options opts = new Options()
      .setQueuable(true)
      .setLimit(limit);
    Object[] args = new Object[]{"key", "foo", "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("min", "foo")
      .put("max", "bar")
      .put("limit", new JSONArray(Arrays.asList(limit)));

    this.testReadMethod("zrangebylex", new Class[]{String.class, String.class, String.class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    String[] expectedResult = new String[]{"foo", "bar", "baz"};
    ResponseListener<String[]> listener = verifyResultStringArray(result, expectedResult);
    ms.zrangebylex("key", "foo", "bar", listener);
  }

  @Test
  public void zrangebyscore() throws Exception {
    Integer[] limit = new Integer[]{1, 2};
    Options opts = new Options()
      .setQueuable(true)
      .setLimit(limit);
    Object[] args = new Object[]{"key", 3.14, 42.24};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("min", 3.14)
      .put("max", 42.24)
      .put("limit", new JSONArray(Arrays.asList(limit)))
      .put("options", new JSONArray().put("withscores"));

    this.testReadMethod("zrangebyscore", new Class[]{String.class, double.class, double.class}, args, opts, expected);

    ResponseListener<JSONObject[]> listener = verifyResultJSONObjectArray(
      new JSONArray().put("foo").put("3.14159").put("bar").put("123.456"),
      new JSONObject[]{
        new JSONObject().put("member", "foo").put("score", 3.14159),
        new JSONObject().put("member", "bar").put("score", 123.456)
      }
    );
    ms.zrangebyscore("key", 3.14, 42.24, listener);
  }

  @Test
  public void zrank() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("member", "foo");

    this.testReadMethod("zrank", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zrank("key", "foo", listener);
  }

  @Test
  public void zrem() throws Exception {
    Options opts = new Options().setQueuable(true);
    String[] members = new String[]{"foo", "bar", "baz"};

    Object[] args = new Object[]{"key", members};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("members", new JSONArray(Arrays.asList(members)))
      );

    this.testWriteMethod("zrem", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zrem("key", members, listener);
  }

  @Test
  public void zremrangebylex() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo", "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("min", "foo")
        .put("max", "bar")
    );

    this.testWriteMethod("zremrangebylex", new Class[]{String.class, String.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zremrangebylex("key", "foo", "bar", listener);
  }

  @Test
  public void zremrangebyrank() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 13, 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("start", 13)
        .put("stop", 42)
      );

    this.testWriteMethod("zremrangebyrank", new Class[]{String.class, long.class, long.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zremrangebyrank("key", 13, 42, listener);
  }

  @Test
  public void zremrangebyscore() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 13.1, 42.3};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("min", 13.1)
        .put("max", 42.3)
      );

    this.testWriteMethod("zremrangebyscore", new Class[]{String.class, double.class, double.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zremrangebyscore("key", 13, 42, listener);
  }

  @Test
  public void zrevrange() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", 13, 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("start", 13)
      .put("stop", 42)
      .put("options", new JSONArray().put("withscores"));

    this.testReadMethod("zrevrange", new Class[]{String.class, long.class, long.class}, args, opts, expected);

    ResponseListener<JSONObject[]> listener = verifyResultJSONObjectArray(
      new JSONArray().put("foo").put("3.14159").put("bar").put("123.456"),
      new JSONObject[]{
        new JSONObject().put("member", "foo").put("score", 3.14159),
        new JSONObject().put("member", "bar").put("score", 123.456)
      }
    );
    ms.zrevrange("key", 13, 42, listener);
  }

  @Test
  public void zrevrangebylex() throws Exception {
    Integer[] limit = new Integer[]{1, 2};
    Options opts = new Options()
      .setQueuable(true)
      .setLimit(limit);
    Object[] args = new Object[]{"key", "foo", "bar"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("min", "foo")
      .put("max", "bar")
      .put("limit", new JSONArray(Arrays.asList(limit)));

    this.testReadMethod("zrevrangebylex", new Class[]{String.class, String.class, String.class}, args, opts, expected);

    JSONArray result = new JSONArray().put("foo").put("bar").put("baz");
    String[] expectedResult = new String[]{"foo", "bar", "baz"};
    ResponseListener<String[]> listener = verifyResultStringArray(result, expectedResult);
    ms.zrevrangebylex("key", "foo", "bar", listener);
  }

  @Test
  public void zrevrangebyscore() throws Exception {
    Integer[] limit = new Integer[]{1, 2};
    Options opts = new Options()
      .setQueuable(true)
      .setLimit(limit);
    Object[] args = new Object[]{"key", 3.14, 42.24};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("min", 3.14)
      .put("max", 42.24)
      .put("limit", new JSONArray(Arrays.asList(limit)))
      .put("options", new JSONArray().put("withscores"));

    this.testReadMethod("zrevrangebyscore", new Class[]{String.class, double.class, double.class}, args, opts, expected);

    ResponseListener<JSONObject[]> listener = verifyResultJSONObjectArray(
      new JSONArray().put("foo").put("3.14159").put("bar").put("123.456"),
      new JSONObject[]{
        new JSONObject().put("member", "foo").put("score", 3.14159),
        new JSONObject().put("member", "bar").put("score", 123.456)
      }
    );
    ms.zrevrangebyscore("key", 3.14, 42.24, listener);
  }

  @Test
  public void zrevrank() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("member", "foo");

    this.testReadMethod("zrevrank", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zrevrank("key", "foo", listener);
  }

  @Test
  public void zscan() throws Exception {
    Options opts = new Options()
      .setQueuable(true)
      .setCount((long)10)
      .setMatch("foo*");
    Object[] args = new Object[]{"key", 42};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("cursor", 42)
      .put("count", 10)
      .put("match", "foo*");

    this.testReadMethod("zscan", new Class[]{String.class, long.class}, args, opts, expected);

    JSONArray result = new JSONArray()
      .put("18")
      .put(new JSONArray()
        .put("field1")
        .put("field1 value")
        .put("field2")
        .put("field2 value")
      );

    JSONObject expectedResult = new JSONObject()
      .put("cursor", 18)
      .put("values", result.getJSONArray(1));

    ResponseListener<JSONObject> listener = verifyResultJSONObject(result, expectedResult);
    ms.zscan("key", 42, listener);
  }

  @Test
  public void zscore() throws Exception {
    Options opts = new Options().setQueuable(true);
    Object[] args = new Object[]{"key", "foo"};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("member", "foo");

    this.testReadMethod("zscore", new Class[]{String.class, String.class}, args, opts, expected);

    ResponseListener<Double> listener = verifyResultDouble("3.14159", 3.14159);
    ms.zscore("key", "foo", listener);
  }

  @Test
  public void zunionstore() throws Exception {
    Integer[] weights = new Integer[]{1, 2, 3};
    Options opts = new Options()
      .setQueuable(true)
      .setAggregate("max")
      .setWeights(weights);
    String[] keys = new String[]{"foo", "bar", "baz"};
    Object[] args = new Object[]{"key", keys};
    JSONObject expected = new JSONObject()
      .put("_id", "key")
      .put("body", new JSONObject()
        .put("keys", new JSONArray(Arrays.asList(keys)))
        .put("aggregate", "max")
        .put("weights", new JSONArray(Arrays.asList(weights)))
      );

    this.testWriteMethod("zunionstore", new Class[]{String.class, String[].class}, args, opts, expected);

    ResponseListener<Long> listener = verifyResultLong(123, 123);
    ms.zunionstore("key", keys, listener);
  }

}
