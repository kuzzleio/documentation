NotificationListener listener = new NotificationListener() {
  public void onMessage(NotificationResult notification) {
    System.out.println("Document moved " + notification.getScope() + " from the scope");
  }
};

try {
    String filters = "{ \"range\": { \"age\": { \"lte\": 20 } } }";
    RoomOptions options = new RoomOptions();
    options.setScope("out");

    // Subscribe to notifications when document leaves the scope
    kuzzle.getRealtime().subscribe(
      "nyc-open-data",
      "yellow-taxi",
      filters,
      listener,
      options
    );

    String document = "{ \"name\": \"nina vkote\", \"age\": 19 }";

    // The document is in the scope
    kuzzle.getDocument().create(
      "nyc-open-data",
      "yellow-taxi",
      "nina-vkote",
      document
    );

    // The document isn't in the scope anymore
    kuzzle.getDocument().update(
      "nyc-open-data",
      "yellow-taxi",
      "nina-vkote",
      "{ \"age\": 42 }"
    );
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
