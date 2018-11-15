std::vector<std::string> indexes;

indexes.push_back("nyc-open-data");
indexes.push_back("mtp-open-data");

try {
  std::vector<std::string> deleted = kuzzle->index->mDelete(indexes);

  std::cout << "Successfully deleted " << deleted.size() << " indexes" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
