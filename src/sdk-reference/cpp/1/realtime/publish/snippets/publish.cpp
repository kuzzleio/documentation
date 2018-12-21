try {
  std::string message = "{ \"realtime\": \"rule the web\" }";
  kuzzle->realtime->publish("i-dont-exist", "in-database", message);

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
