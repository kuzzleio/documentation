
// Deleting one document
kuzzle
  .collection("collection", "index")
  .deleteSpecifications(new ResponseListener<JSONObject>() {
    @Override
    public void onSuccess(JSONObject result) {

    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
