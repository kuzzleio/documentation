try {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", "{\"capacity\": 4}");
    String response = kuzzle.getDocument().update("nyc-open-data", "yellow-taxi", "some-id", "{\"category\": \"suv\"}");

    System.out.println(response);
    /*
    {
        "_index": "nyc-open-data",
        "_type": "yellow-taxi",
        "_id": "some-id",
        "_version": 2,
        "result": "updated",
        "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
        }
    }
    */
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
