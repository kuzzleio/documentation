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

    kuzzle.getRealtime().unsubscribe(roomId);

    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
