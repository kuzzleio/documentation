
Document firstDocument = new Document(collection, "doc1");
firstDocument.setContent("title", "foo");
firstDocument.setContent("content", "bar");

Document secondDocument = new Document(collection, "doc2");
secondDocument.setContent("title", "foo");
secondDocument.setContent("content", "bar");

Document[] documents = new Document[]{firstDocument, secondDocument};

kuzzle
  .collection("collection", "index")
  .mReplaceDocument(documents, new ResponseListener<JSONObject>() {
    @Override
    public void onSuccess(JSONObject object) {
      // callback called once the mReplace operation has completed
      // => the result is a JSON object containing the raw Kuzzle response
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
