List<string> indexes = new List<string>();

indexes.Add("nyc-open-data");
indexes.Add("mtp-open-data");

try {
  List<string> deleted = kuzzle.index.mDelete(indexes);

  Console.WriteLine("Successfully deleted " + deleted.Count + " indexes");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
