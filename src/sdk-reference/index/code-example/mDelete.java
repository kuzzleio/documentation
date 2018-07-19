StringVector indexes = new StringVector();

indexes.add("nyc-open-data");
indexes.add("mtp-open-data");

try{
  StringVector deleted = kuzzle.getIndex().mDelete(indexes);

  System.out.println(String.format("Successfully delete %d indexes", deleted.size()));
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
