try {
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-id", "{}");
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-other-id", "{}");

  string body = @"
    [
      {
        ""_id"": ""some-id"",
        ""body"": {""capacity"": 4}
      },
      {
        ""_id"": ""some-other-id"",
        ""body"": {""capacity"": 4}
      }
    ]
  ";

  string response = kuzzle.document.mReplace("nyc-open-data", "yellow-taxi", body);

  Console.WriteLine(response);
  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
