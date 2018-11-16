try {
    String lastStats = new String(kuzzle.getServer().getLastStats());
    System.out.println("Last Kuzzle Stats as JSON string: " + lastStats);
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
