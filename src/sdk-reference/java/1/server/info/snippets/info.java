try {
    kuzzle.getServer().info();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
