try {
  List<string> response = new List<string>();

  response = kuzzle.document.deleteByQuery("nyc-open-data", "yellow-taxi", @"{
    ""query"": {
      ""term"": {
        ""capacity"": 7
      }
    }
  }");

  Console.WriteLine("Successfully deleted " + response.Count + " documents");
} catch  {
  Console.Error.WriteLine("");
}