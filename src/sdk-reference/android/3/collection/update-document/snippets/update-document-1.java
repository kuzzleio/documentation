
JSONObject newTitle = new JSONObject().put("title", "a shiny new title");

kuzzle
  .collection("collection", "index")
  .updateDocument("documentId", newTitle, new ResponseListener<Document>() {
    @Override
    public void onSuccess(Document result) {
      // result is an updated Document object
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
