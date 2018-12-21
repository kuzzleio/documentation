try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", "{}");
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-other-id", "{}");

  std::string documents = R"(
    [
      {
        "_id": "some-id",
        "body": {"capacity": 4}
      },
      {
        "_id": "some-other-id",
        "body": {"capacity": 4}
      }
    ]
  )";

  std::string response = kuzzle->document->mReplace(
    "nyc-open-data",
    "yellow-taxi",
    documents
  );

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
              capacity: 4 },
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
              capacity: 4 },
          _index: 'nyc-open-data',
          _type: 'yellow-taxi',
          _version: 2,
          result: 'updated',
          _shards: { total: 2, successful: 1, failed: 0 },
          created: false,
          status: 200 } ],
      total: 2 }
  */

  std::cout << "Successfully replaced 2 documents" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
