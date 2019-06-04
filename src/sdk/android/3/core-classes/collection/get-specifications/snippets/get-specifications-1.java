
kuzzle
  .collection("collection", "index")
  .getSpecifications(new ResponseListener<JSONObject>() {
    @Override
    public void onSuccess(JSONObject specifications) {
        // specifications is a JSONObject
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
