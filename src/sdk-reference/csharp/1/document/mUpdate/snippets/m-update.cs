try {
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-id", @"{""capacity"": 4}");
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-other-id", @"{""capacity"": 7}");

  string body = @"
    [
      {
        ""_id"": ""some-id"",
        ""body"": {""category"": ""sedan""}
      },
      {
        ""_id"": ""some-other-id"",
        ""body"": {""category"": ""limousine""}
      }
    ]
  ";

  string response = kuzzle.document.mUpdate("nyc-open-data", "yellow-taxi", body);

  Console.WriteLine(response);
  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}