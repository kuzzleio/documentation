try {
  std::string message = R"({ "realtime": "rule the web" })";

  kuzzle->realtime->publish("i-dont-exist", "in-database", message);

  std::cout << "Message successfully published" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
