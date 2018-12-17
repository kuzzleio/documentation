try {
  kuzzle.index.refreshInternal();

  Console.WriteLine("Internal index successfully refreshed");
} catch  {
  Console.Error.WriteLine("");
}
