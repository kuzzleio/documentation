
kuzzle.checkToken("some jwt token", new ResponseListener<TokenValidity>() {
  @Override
  public void onSuccess(TokenValidity tokenInfo) {
    if (tokenInfo.isValid()) {
      // tokenInfo.getExpiresAt() returns the expiration timestamp
    }
    else {
      // tokenInfo.getState() returns the invalidity reason
    }
  }
});
