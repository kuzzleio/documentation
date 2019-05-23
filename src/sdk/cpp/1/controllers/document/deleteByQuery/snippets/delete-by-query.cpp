try {
  std::vector<std::string> response =
    kuzzle->document->deleteByQuery("nyc-open-data", "yellow-taxi", R"({
      "query": {
        "term": {
          "capacity": 7
        }
      }
    })");

  std::cout << "Successfully deleted " << response.size() << " documents" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
