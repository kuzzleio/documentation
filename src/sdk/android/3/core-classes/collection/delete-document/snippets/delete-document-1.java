
// Deleting one document
kuzzle
  .collection("collection", "index")
  .deleteDocument("document unique ID", new ResponseListener<String>() {
    @Override
    public void onSuccess(String object) {
      // The resulting string contains the deleted document ID
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });

// Deleting multiple documents
JSONObject equalsFilter = new JSONObject()
  .put("filter", new JSONObject()
  .put("equals",
    new JSONObject().put("title", "foo")
  ));

kuzzle
  .collection("collection", "index")
  .deleteDocument(equalsFilter, new ResponseListener<String[]>() {
    @Override
    public void onSuccess(String[] object) {
      // The resulting object contains the list of deleted document IDs
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
