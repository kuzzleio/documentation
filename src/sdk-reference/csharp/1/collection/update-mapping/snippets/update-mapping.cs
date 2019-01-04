try {
  string mapping = @"{
    ""properties"": {
      ""plate"": { ""type"": ""keyword"" }
    }
  }";

  kuzzle.collection.updateMapping("nyc-open-data", "yellow-taxi", mapping);

  Console.WriteLine("Mapping successfully updated");
} catch  {
  Console.Error.WriteLine(e.what());
}
