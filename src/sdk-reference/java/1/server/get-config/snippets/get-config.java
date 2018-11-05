try {
    kuzzle.getServer().getConfig();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
