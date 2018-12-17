try {
  kuzzle.index.create("nyc-open-data");
} catch (BadRequestException e) {
  Console.WriteLine("Status: " + e.status + " Message: " + e.what());
  Console.WriteLine("Try with another name!");
}
