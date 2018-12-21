try {
  std::string id = response = kuzzle->document->delete_(
    "nyc-open-data",
    "yellow-taxi",
    "some-id"
  );

  std::cout << "Document " << id << " successfully deleted" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
