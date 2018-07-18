try {
    kuzzle.getIndex().delete("nyc-open-data");
} catch (KuzzleException e) {
    System.out.println(e.getMessage());
}
