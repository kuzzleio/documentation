try {
  string mapping = "{\"properties\": {\"plate\": {\"type\": \"keyword\"}}}";

  kuzzle.collection.updateMapping("nyc-open-data", "yellow-taxi", mapping);

  Console.WriteLine("Success");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
