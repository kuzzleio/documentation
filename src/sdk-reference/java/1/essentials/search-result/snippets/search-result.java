try {
    kuzzle.getEssentials().searchResult();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
