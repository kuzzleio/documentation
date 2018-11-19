try {
    String config = new String(kuzzle.getServer().getConfig());
    System.out.println("Kuzzle Server configuration as JSON string: " + config);
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
