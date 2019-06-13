try {
    Date ts = kuzzle.getServer().now();
    System.out.println("Epoch-millis timestamp: " + ts.getTime());
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
