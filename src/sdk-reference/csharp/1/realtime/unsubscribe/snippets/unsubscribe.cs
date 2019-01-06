NotificationListener listener =
  [](const notification_result *notification){};

try {
  string room_id = kuzzle.realtime.subscribe(
    "nyc-open-data",
    "yellow-taxi",
    "{}",
    listener);

  kuzzle.realtime.unsubscribe(room_id);

  Console.WriteLine("Successfully unsubscribed");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
