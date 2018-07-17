try {
    kuzzle.getIndex().delete("nyc-open-data");
} catch (kuzzleio::KuzzleException e) {
    System.out.println(e.getMessage());
}
