try {
  kuzzle.index.create("nyc-open-data");
  // Second call fail
  kuzzle.index.create("nyc-open-data");
} catch (BadRequestException e) {
  Console.WriteLine("Status: " + e.status() + " Message: " + e.getMessage());
  Console.WriteLine("Try with another name!");
}
