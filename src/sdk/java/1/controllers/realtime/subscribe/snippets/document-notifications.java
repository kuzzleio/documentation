String filters = "{ \"exists\": \"name\" }";

NotificationListener listener = new NotificationListener() {
  public void onMessage(NotificationResult notification) {
    String id = notification.getResult().getId();

    if (notification.getScope().equals("in")) {
      System.out.println("Document " + id + " enter the scope");
    } else {
      System.out.println("Document " + id + " leave the scope");
    }
  }
};

String document = "{ \"name\": \"nina vkote\", \"age\": 19 }";

try {
    kuzzle.getRealtime().subscribe(
      "nyc-open-data",
      "yellow-taxi",
      filters,
      listener
    );

    kuzzle.getDocument().create(
      "nyc-open-data",
      "yellow-taxi",
      "nina-vkote",
      document
    );
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
