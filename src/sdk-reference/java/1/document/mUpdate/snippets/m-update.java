try {
  kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", "{\"capacity\": 4}");
  kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-other-id", "{\"capacity\": 7}");

  String documents = "" +
    "[" +
      "{" +
        "\"_id\": \"some-id\"," +
        "\"body\": {\"category\": \"sedan\"}" +
      "}," +
      "{" +
        "\"_id\": \"some-other-id\"," +
        "\"body\": {\"category\": \"limousine\"}" +
      "}" +
    "]" +
  "";

  String response = kuzzle.getDocument().mUpdate(
    "nyc-open-data",
    "yellow-taxi",
    documents);

  System.out.println(response);
    /*
    {  hits:
      [ { _id: 'some-id',
        _source:
          { _kuzzle_info:
            { active: true,
              author: '-1',
              updater: null,
              updatedAt: null,
              deletedAt: null,
              createdAt: 1538639586995 },
            capacity: 4,
            category: "sedan"},
        _index: 'nyc-open-data',
        _type: 'yellow-taxi',
        _version: 2,
        result: 'updated',
        _shards: { total: 2, successful: 1, failed: 0 },
        created: false,
        status: 200 },
      { _id: 'some-other-id',
        _source:
          { _kuzzle_info:
            { active: true,
              author: '-1',
              updater: null,
              updatedAt: null,
              deletedAt: null,
              createdAt: 1538639586995 },
            capacity: 4,
            category: "limousine" },
        _index: 'nyc-open-data',
        _type: 'yellow-taxi',
        _version: 2,
        result: 'updated',
        _shards: { total: 2, successful: 1, failed: 0 },
        created: false,
        status: 200 } ],
    total: 2 }
  */
  System.out.println("Successfully updated 2 documents");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
