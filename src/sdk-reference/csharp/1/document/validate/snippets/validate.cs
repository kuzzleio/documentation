try {
  bool valid = kuzzle.document.validate("nyc-open-data", "yellow-taxi", @"{
    ""capacity"": 4
  }");

  if (valid) {
    Console.WriteLine("The document is valid");
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
