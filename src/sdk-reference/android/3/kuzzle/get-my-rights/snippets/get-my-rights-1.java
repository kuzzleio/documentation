

kuzzle
  .security
  .getMyRights(new ResponseListener<JSONObject[]>() {
    @Override
    public void onSuccess(JSONObject[] rights) {
    }

    @Override
    public void onError(JSONObject error) {
    }
  });
