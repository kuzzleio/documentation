try {
    kuzzle.getServer().getAllStats();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
