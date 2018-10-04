kuzzle.Document.Create("nyc-open-data", "yellow-taxi", "some-id", `{"color": "yellow"}`)

response, err := kuzzle.Document.Replace("nyc-open-data", "yellow-taxi", "some-id", `{
  "capacity": 4,
  "category": "sedan"
}`)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println(string(response))
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
    },
    "created": false,
    "_source": {
      "capacity": 4,
      "category": "sedan",
      "_kuzzle_info": {
        "author": "-1",
        "createdAt": 1538641029988,
        "updatedAt": 1538641029988,
        "updater": "-1",
        "active": true,
        "deletedAt": null
      }
    },
    "_meta": {
      "author": "-1",
      "createdAt": 1538641029988,
      "updatedAt": 1538641029988,
      "updater": "-1",
      "active": true,
      "deletedAt": null
    }
  }
  */
  fmt.Println("Success")
}
