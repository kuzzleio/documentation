
QueryArgs args = new QueryArgs();
args.controller = "controller";
args.action = "action";

JSONObject query = new JSONObject()
  .put("body", new JSONObject()
    .put("foo", "bar")
  )
  .put("other", "argument");

kuzzle.query(args, query, new OnQueryDoneListener() {
  @Override
  public void onSuccess(JSONObject object) {

  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
});
