NotificationListener listener =
  [](const notification_result *notification){};

try {
  string room_id = kuzzle.realtime.subscribe(
    "nyc-open-data",
    "yellow-taxi",
    "{}",
    listener);

  int count = kuzzle.realtime.count(room_id);

  Console.WriteLine("Currently " + count + " active subscription");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
