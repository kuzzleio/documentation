
Document document = kuzzle
  .collection("collection", "index")
  .document("id", new JSONObject().put("some", "content"))
  .save();
