
JSONObject fooField = new JSONObject()
    .put("mandatory", "true")
    .put("type", "string")
    .put("defaultValue", "bar");

JSONObject fields = new JSONObject()
    .put("foo", fooField);

JSONObject specifications = new JSONObject()
    .put("strict", "true")
    .put("fields", fields);

kuzzle
  .collection("collection", "index")
  .updateSpecifications(specifications, new ResponseListener<JSONObject>() {
    @Override
    public void onSuccess(JSONObject res) {
      // result is a JSONObject
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
