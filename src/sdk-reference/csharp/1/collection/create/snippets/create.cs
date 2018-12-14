try {
  string mapping = "{\"properties\": {\"license\": {\"type\": \"keyword\"}}}";

  kuzzle.collection.create("nyc-open-data", "yellow-taxi", mapping);

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
