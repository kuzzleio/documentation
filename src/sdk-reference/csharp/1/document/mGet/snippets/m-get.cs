List<string> ids = new List<string>();

ids.Add("some-id");
ids.Add("some-other-id");

try {
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-id", @"{""capacity"": 4}");
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-other-id", @"{""capacity"": 7}");

  string response = kuzzle.document.mGet(
    "nyc-open-data",
    "yellow-taxi",
    ids);

  Console.WriteLine(response);
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
  Console.WriteLine("Successfully retrieved 2 documents");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
