
JSONObject filters = new JSONObject();

kuzzle
  .collection("collection", "index")
  .count(filters, new ResponseListener<Integer>() {
    @Override
    public void onSuccess(Integer object) {
      // Handle success
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
