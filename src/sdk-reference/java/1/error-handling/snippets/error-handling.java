try {
    kuzzle.getIndex().create("nyc-open-data");
} catch (BadRequestException e) {
  System.err.println(e.getMessage());
  System.err.println("Try with another name!");
}
