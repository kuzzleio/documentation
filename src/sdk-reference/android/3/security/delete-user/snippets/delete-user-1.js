
kuzzle
  .security
  .deleteUser("myuser", new ResponseListener<String>() {
    @Override
    public void onSuccess(String userName) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
