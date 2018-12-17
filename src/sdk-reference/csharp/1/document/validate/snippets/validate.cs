try {
  if (kuzzle.document.validate("nyc-open-data", "yellow-taxi", @"{
    ""capacity"": 4
  }")) {
    Console.WriteLine("Success");
  }
} catch  {
  Console.Error.WriteLine("");
}
