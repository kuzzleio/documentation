String filters = "{ \"exists\": \"name\" }";
RoomOptions options = new RoomOptions();
options.setUsers("all");

NotificationListener listener = new NotificationListener() {
  public void onMessage(NotificationResult notification) {
    System.out.println(notification.getVolatiles());
    // "{ "username\": "nina vkote" }"
    System.out.println("Currently " + notification.getResult().getCount() + " users in the room");
  }
};

try {
  kuzzle.getRealtime().subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    listener,
    options
  );

  // Instantiates a second kuzzle client: multiple subscriptions
  // made by the same user will not trigger "new user" notifications
  WebSocket ws2 = new WebSocket("kuzzle");
  Kuzzle kuzzle2 = new Kuzzle(ws2);
  kuzzle2.connect();

  // Set some volatile data
  RoomOptions options2 = new RoomOptions();
  options2.setVolatiles("{ \"username\": \"nina vkote\" }");

  // Subscribe to the same room with the second client
  kuzzle2.getRealtime().subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    listener,
    options2
  );
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
