StringVector ids = new StringVector();

ids.add("some-id");
ids.add("some-other-id");

try {
  kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", "{\"capacity\": 4}");
  kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-other-id", "{\"capacity\": 7}");

  String response = kuzzle.getDocument().mGet(
    "nyc-open-data",
    "yellow-taxi",
    ids);

  System.out.println(response);
  /*
    [
      {
        "_index": "nyc-open-data",
        "_type": "yellow-taxi",
        "_id": "some-id",
        "_version": 1,
        "found": true,
        "_source": {
          "capacity": 4,
          "_kuzzle_info": {
            "author": "-1",
            "createdAt": 1545411356404,
            "updatedAt": null,
            "updater": null,
            "active": true,
            "deletedAt": null
          }
        }
      },
      {
        "_index": "nyc-open-data",
        "_type": "yellow-taxi",
        "_id": "some-other-id",
        "_version": 1,
        "found": true,
        "_source": {
          "capacity": 7,
          "_kuzzle_info": {
            "author": "-1",
            "createdAt": 1545411356424,
            "updatedAt": null,
            "updater": null,
            "active": true,
            "deletedAt": null
          }
        }
      }
    ]
  */
  System.out.println("Successfully retrieved 2 documents");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
