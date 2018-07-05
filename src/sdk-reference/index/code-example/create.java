try {
    kuzzle.getIndex().create("nyc-open-data");
} catch (KuzzleException e) {
    System.out.println(e.getMessage());
}
