String filters = "{ \"exists\": \"name\" }";
RoomOptions options = new RoomOptions();
options.setUsers("all");

NotificationListener listener = new NotificationListener() {
  public void onMessage(NotificationResult notification) {
    System.out.println("Currently " + notification.getResult().getCount() + " users in the room");
  }
};

String document = "{ \"name\": \"nina vkote\", \"age\": 19 }";

try {
    kuzzle.getRealtime().subscribe(
      "nyc-open-data",
      "yellow-taxi",
      filters,
      listener,
      options
    );

    // instantiate a second kuzzle client because
    // the same sdk instance does not receive his own notifications
    Kuzzle fuzzle = new Kuzzle("kuzzle");
    fuzzle.connect();

    // Set some volatile data
    fuzzle.setVolatile("{ \"username\": \"nina vkote\" }");

    // Subscribe to the same room with the second client
    fuzzle.getRealtime().subscribe(
      "nyc-open-data",
      "yellow-taxi",
      filters,
      listener
    );
  } catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
