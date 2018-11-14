
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
  .validateSpecifications(specifications, new ResponseListener<Boolean>() {
    @Override
    public void onSuccess(Boolean isValid) {
      // isValid is a boolean
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
