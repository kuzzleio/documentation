
// Optional: a mapping can be provided and will be
// applied when the collection is created
JSONObject mapping = new JSONObject()
  .put("properties", new JSONObject()
    .put("field1", new JSONObject().put("type", "<es field type>"))
    .put("field2", new JSONObject().put("type", "<es field type>"))
  );

kuzzle
  .collection("collection", "index")
  .create(mapping, new ResponseListener<JSONObject>() {
    @Override
    public void onSuccess(JSONObject object) {
      // callback called once the create operation has completed
      // => the result is a JSON object containing the raw Kuzzle response:
      // {
      //    acknowledged: true
      // }
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
