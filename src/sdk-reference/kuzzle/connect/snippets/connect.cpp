char* error = kuzzle->connect();

if (error == nullptr) {
  std::cout << "Successfully connected" << std::endl;
} else {
  std::cerr << error << std::endl;
}
