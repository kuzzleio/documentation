try {
  List<string> response =
    kuzzle.document.deleteByQuery("nyc-open-data", "yellow-taxi", @"{
      ""query"": {
        ""term"": {
          ""capacity"": 7
        }
      }
    }");

  Console.WriteLine("Successfully deleted " + response.Count + " documents");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
