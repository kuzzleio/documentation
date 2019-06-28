
kuzzle
  .security
  .scrollUsers(scrollId, options, new ResponseListener<SecurityDocumentList>() {
    @Override
    public void onSuccess(SecurityDocumentList response) {
      // called once the scroll action has been completed
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
