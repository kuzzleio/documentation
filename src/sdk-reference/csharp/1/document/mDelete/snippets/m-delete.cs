List<string> ids = new List<string>();

ids.Add("some-id");
ids.Add("some-other-id");

try {
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-id", "{}");
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "some-other-id", "{}");

  List<string> deleted = kuzzle.document.mDelete("nyc-open-data", "yellow-taxi", ids);

  Console.WriteLine("Successfully deleted " + deleted.Count + " documents");
} catch  {
  Console.Error.WriteLine("");
}