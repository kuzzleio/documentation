
Collection collection = kuzzle.collection("collection", "index");

// or using a default index:
kuzzle.setDefaultIndex("index");
Collection collection = kuzzle.collection("collection");
