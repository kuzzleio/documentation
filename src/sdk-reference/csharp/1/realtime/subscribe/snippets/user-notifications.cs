NotificationListener listener =
  [](const notification_result *notification) {
    Console.WriteLine("Currently " + notification.result.count + " users in the room");

    Console.WriteLine(notification.volatiles);
    // "{ "username": "nina vkote" }"
  };

try {
  // Subscription to notifications and notifications when user join or leave
  const char *filters = @"{ ""exists"": ""name"" }";
  room_options options;
  options.users = "all";

  kuzzle.realtime.subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    listener,
    options);

  // Instantiates a second kuzzle client:
  //  multiple subscriptions made by the same user
  //  will not trigger "new user" notifications
  WebSocket* ws2 = new WebSocket("kuzzle");
  Kuzzle* kuzzle2 = new Kuzzle(ws2);
  kuzzle2.connect();

  // Set some volatile data
  room_options options2;
  options2.volatiles = @"{ ""username"": ""nina vkote"" }";

  // Subscribe to the same room with the second client
  kuzzle2.realtime.subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    listener,
    options2);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
