try {
    kuzzle.getServer().adminExists();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
