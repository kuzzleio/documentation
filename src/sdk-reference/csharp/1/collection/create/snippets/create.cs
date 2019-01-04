try {
  string mapping = @"{
    ""properties"": {
      ""license"": { ""type"": ""keyword"" },
      ""driver"": {
        ""properties"": {
          ""name"": { ""type"": ""keyword"" },
          ""curriculum"": { ""type"": ""text"" }
        }
      }
    }
  }";

  kuzzle.collection.create("nyc-open-data", "yellow-taxi", mapping);

  Console.WriteLine("Collection successfully created");
} catch  {
  Console.Error.WriteLine(e.what());
}
