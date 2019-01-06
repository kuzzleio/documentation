try {
  kuzzle.index.create("nyc-open-data");
} catch (BadRequestException e) {
  Console.WriteLine("Status: " + e.status + " Message: " + e.Message());
  Console.WriteLine("Try with another name!");
}
