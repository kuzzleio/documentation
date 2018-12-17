try {
  string message = "{ \"realtime\": \"rule the web\" }";
  kuzzle.realtime.publish("i-dont-exist", "in-database", message);

  Console.WriteLine("Success");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}