try {
    String message = "{ \"realtime\": \"rule the web\" }";
    kuzzle.getRealtime().publish("i-dont-exist", "in-database", message);

    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
