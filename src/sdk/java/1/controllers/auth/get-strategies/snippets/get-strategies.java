try {
    kuzzle.getAuth().login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
    kuzzle.getAuth().getStrategies();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
