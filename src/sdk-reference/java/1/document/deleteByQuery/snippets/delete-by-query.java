try {
    StringVector deleted = kuzzle.getDocument().deleteByQuery("nyc-open-data", "yellow-taxi", "{\"query\": {\"match\": {\"capacity\": 7}}}");

    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
