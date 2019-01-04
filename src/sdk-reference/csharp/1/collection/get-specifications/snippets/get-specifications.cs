try {
  string specifications = kuzzle.collection.getSpecifications("nyc-open-data", "yellow-taxi");

  Console.WriteLine(specifications);
  // {"nyc-open-data": {"yellow-taxi": {"strict": false, "fields": {"license": {"type": "string"}}}}}
} catch  {
  Console.Error.WriteLine(e.what());
}
