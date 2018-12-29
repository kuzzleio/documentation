try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", R"({"capacity": 4})");
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-other-id", R"({"capacity": 7})");

  std::string documents = R"(
    [
      {
        "_id": "some-id",
        "body": {"category": "sedan"}
      },
      {
        "_id": "some-other-id",
        "body": {"category": "limousine"}
      }
    ]
  )";

  std::string response = kuzzle->document->mUpdate(
    "nyc-open-data",
    "yellow-taxi",
    documents);

  std::cout << response << std::endl;
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
  std::cout << "Successfully updated 2 documents" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
