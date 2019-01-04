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

  validation_response validation_response =
    kuzzle.collection.validateSpecifications(
      "nyc-open-data",
      "yellow-taxi",
      specifications);

  if (validation_response.valid) {
    Console.WriteLine("Specifications are valid");
  }
} catch  {
  Console.Error.WriteLine(e.what());
}
