
kuzzle.logout(new ResponseListener<Void>() {
  @Override
  public void onSuccess(Void result) {
    // ...
  }

  @Override
  public void onError() {
    // Handle error
  }
});
