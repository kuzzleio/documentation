try {
    kuzzle.getIndex().delete("nyc-open-data");
    System.out.println("index deleted");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
