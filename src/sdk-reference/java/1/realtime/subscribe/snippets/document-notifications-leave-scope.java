String filters = "{ \"range\": { \"age\": { \"lte\": 20 } } }";
RoomOptions options = new RoomOptions();
options.setScope("out");

NotificationListener listener = new NotificationListener() {
  public void onMessage(NotificationResult notification) {
    System.out.println("Document moved " + notification.getScope() + " from the scope");
  }
};

String document = "{ \"name\": \"nina vkote\", \"age\": 19 }";

try {
    kuzzle.getRealtime().subscribe("nyc-open-data", "yellow-taxi", filters, listener, options);

    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "nina-vkote", document);

    kuzzle.getDocument().update("nyc-open-data", "yellow-taxi", "nina-vkote", "{ \"age\": 42 }");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
