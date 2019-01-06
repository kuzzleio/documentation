try {
  string specifications = @"{
    ""strict"": false,
    ""fields"": {
      ""license"": {
        ""mandatory"": true,
        ""type"": ""string""
      }
    }
  }";

  string updated_specifications =
    kuzzle.collection.updateSpecifications("nyc-open-data", "yellow-taxi", specifications);

  Console.WriteLine(updated_specifications);
  // {"strict":false,"fields":{"license":{"mandatory":true,"type":"string"}}}
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
