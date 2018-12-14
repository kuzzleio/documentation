try {
  string specifications = "{ \"strict\": false, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } }";

  string response = kuzzle.collection.updateSpecifications("nyc-open-data", "yellow-taxi", specifications);

  Console.WriteLine("Success");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
