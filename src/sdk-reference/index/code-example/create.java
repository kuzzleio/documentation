try {
    kuzzle.getIndex().create("nyc-open-data");
    System.out.println("index created");
} catch (kuzzleio::KuzzleException e) {
    System.out.println(e.getMessage());
}
