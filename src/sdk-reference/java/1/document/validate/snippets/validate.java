try {
    if (kuzzle.getDocument().validate("nyc-open-data", "yellow-taxi", "{\"capacity\": 4}")) {
        System.out.println("Success");
    }
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
