try {
    kuzzle.getServer().now();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
