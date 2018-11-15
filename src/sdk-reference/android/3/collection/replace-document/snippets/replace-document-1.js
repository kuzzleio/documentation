
JSONObject newContent = new JSONObject("new", "document content");

kuzzle
  .collection("collection", "index")
  .replaceDocument("documentId", newContent, new ResponseListener<Document>() {
    @Override
    public void onSuccess(Document document) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
