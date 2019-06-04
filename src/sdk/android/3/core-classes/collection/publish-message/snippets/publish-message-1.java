
JSONObject message = new JSONObject().put("some", "content");
JSONObject volatile = new JSONObject().put("volatile", "are volatile information");
Options opts = new Options().setVolatile(volatile);

kuzzle
  .collection("collection", "index")
  .publish(message, opts);
