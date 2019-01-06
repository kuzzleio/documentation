try {
  int count = kuzzle.document.count("nyc-open-data", "yellow-taxi", @"{
    ""query"": {
      ""match"": {
        ""license"": ""valid""
      }
    }
  }");

  Console.WriteLine("Found " + count + " documents matching license:valid");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
