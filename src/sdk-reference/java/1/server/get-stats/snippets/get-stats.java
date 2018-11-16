try {
    String stats = new String(kuzzle.getServer().getStats(
      1234567890000L,
      1541426610000L
    ));
    System.out.println("Kuzzle Stats as JSON string: " + stats);
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
