try {
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-id", @"{""capacity"": 4}");

  string response = kuzzle.document.update(
    "nyc-open-data",
    "yellow-taxi",
    "some-id",
    @"{""category"": ""suv""}");

  Console.WriteLine(response);
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
  Console.WriteLine("Document successfully updated");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
