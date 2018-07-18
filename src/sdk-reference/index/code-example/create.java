try {
    kuzzle.getIndex().create("nyc-open-data");
    System.out.println("index created");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
