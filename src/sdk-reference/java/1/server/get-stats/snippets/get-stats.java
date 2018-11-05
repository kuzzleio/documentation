try {
    kuzzle.getServer().getStats(
      1234567890,
      1541426610
    );
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
