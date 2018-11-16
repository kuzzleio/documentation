
kuzzle
  .security
  .deleteProfile("myprofile", new ResponseListener<String>() {
    @Override
    public void onSuccess(String profileName) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
