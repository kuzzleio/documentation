List<string> ids = new List<string>();

ids.Add("some-id");
ids.Add("some-other-id");

try {
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-id", "{\"capacity\": 4}");
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-other-id", "{\"capacity\": 4}");

  string response = kuzzle.document.mGet("nyc-open-data", "yellow-taxi", ids);

  Console.WriteLine(response);
  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}