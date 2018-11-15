try {
    String allStats = new String(kuzzle.getServer().getAllStats());
    System.out.println("All Kuzzle Stats as JSON string: " + allStats);
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
