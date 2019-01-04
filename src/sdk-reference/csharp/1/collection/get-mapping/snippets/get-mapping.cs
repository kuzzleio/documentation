try {
  string mapping = kuzzle.collection.getMapping("nyc-open-data", "yellow-taxi");

  Console.WriteLine(mapping);
  // {"properties":{"license":{"type":"keyword"},"driver":{"properties":{"name":{"type":"keyword"},"curriculum":{"type":"text"}}}}}
} catch  {
  Console.Error.WriteLine(e.what());
}
