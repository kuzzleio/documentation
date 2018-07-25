char* error = kuzzle->connect();

if (error != nullptr) {
  std::cerr << error << std::endl;
} else {
  std::cout << "Successfully connected" << std::endl;
}
