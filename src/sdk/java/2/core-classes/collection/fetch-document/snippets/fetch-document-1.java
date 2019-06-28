
kuzzle
  .collection("collection", "index")
  .fetchDocument("documentId", new ResponseListener<Document>() {
    @Override
    public void onSuccess(Document object) {
      // result is a Document object
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
