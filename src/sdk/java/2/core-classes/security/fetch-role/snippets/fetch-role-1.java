

kuzzle
  .security
  .fetchRole("myrole", new ResponseListener<Role>() {
    @Override
    public void onSuccess(Role role) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
