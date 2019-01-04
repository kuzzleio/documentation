try {
  kuzzle.collection.deleteSpecifications("nyc-open-data", "yellow-taxi");

  Console.WriteLine("Specifications successfully deleted");
} catch  {
  Console.Error.WriteLine(e.what());
}
