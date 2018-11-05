try {
    kuzzle.getServer().getLastStats();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
