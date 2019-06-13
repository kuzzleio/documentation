try {
    kuzzle.getAuth().login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
    kuzzle.getAuth().validateMyCredentials("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
