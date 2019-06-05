
JSONObject headers = new JSONObject()
  .put("someContent", "someValue")
  .put("volatile", new JSONObject()
    .put("someVolatileData", new JSONArray()
      .put("with")
      .put("some")
      .put("values")
    )
  );

kuzzle
  .collection("collection", "index")
  .setHeaders(content, true);
