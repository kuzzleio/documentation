try {
  string response;
  response = kuzzle.document.delete("nyc-open-data", "yellow-taxi", "some-id");

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}