
kuzzle
  .collection("collection", "index")
  .truncate(new ResponseListener<JSONObject>() {
    @Override
    public void onSuccess(JSONObject object) {
      // callback called once the truncate operation has completed
      // => the result is a JSON object containing the raw Kuzzle response
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
