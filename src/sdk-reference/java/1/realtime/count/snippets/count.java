NotificationListener listener = new NotificationListener() {
  public void onMessage(NotificationResult notification) {}
};

try {
    String roomId = kuzzle.getRealtime().subscribe(
      "nyc-open-data", 
      "yellow-taxi", 
      "{}", 
      listener
    );

    int count = kuzzle.getRealtime().count(roomId);

    System.out.println("Currently " + count + " active subscription");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
