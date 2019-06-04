
CollectionMapping dataMapping = kuzzle
  .collection("collection", "index")
  .collectionMapping(new JSONObject().put("someFiled", new JSONObject().put("type", "string").put("index", "analyzed"))
  .apply(new ResponseListener<CollectionMapping>() {
     @Override
     public void onSuccess(CollectionMapping object) {
       // called once the mapping action has been completed
     }

     @Override
     public void onError(JSONObject error) {
       // Handle error
     }
  });
