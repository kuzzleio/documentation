
kuzzle
  .security
  .deleteRole("myrole", new ResponseListener<String>() {
    @Override
    public void onSuccess(String roleName) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
