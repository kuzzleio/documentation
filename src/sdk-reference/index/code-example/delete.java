try {
    kuzzle.getIndex().delete("nyc-open-data");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
