try {
    QueryOptions options = new QueryOptions();
    options.setFrom(1);
    options.setSize(1);

    String collectionList = kuzzle.getCollection().list("mtp-open-data", options);
    // {"type":"all","collections":[{"name":"pink-taxi","type":"stored"}],"from":1,"size":2}

    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
