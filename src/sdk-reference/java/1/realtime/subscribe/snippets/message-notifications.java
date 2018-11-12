String filters = "{ \"exists\": \"name\" }";

NotificationListener listener = new NotificationListener() {
  public void onMessage(NotificationResult notification) {
    System.out.println("Message notification received");
  }
};

try {
    kuzzle.getRealtime().subscribe(
      "i-dont-exist", 
      "in-database", 
      "{}", 
      listener
    );

    String message = "{ \"metAt\": \"Insane\", \"hello\": \"world\" }";
    kuzzle.getRealtime().publish("i-dont-exist", "in-database", message);
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
