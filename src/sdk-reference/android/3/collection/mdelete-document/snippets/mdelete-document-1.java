
String[] documentIds = new String[]{"doc1", "doc2"};

kuzzle
  .collection("collection", "index")
  .mDeleteDocument(documentIds, new ResponseListener<JSONObject>() {
    @Override
    public void onSuccess(JSONObject object) {
      // callback called once the mDelete operation has completed
      // => the result is a JSON object containing the raw Kuzzle response
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });
