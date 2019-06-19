
JSONObject newContent = new JSONObject()
  .put("firstname", "My Name Is")
  .put("lastname", "Jonas");

kuzzle
  .updateSelf(newContent, new ResponseListener<JSONObject>() {
    @Override
    public void onSuccess(JSONObject user) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
