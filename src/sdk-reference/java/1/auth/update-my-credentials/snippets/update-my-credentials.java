try {
    kuzzle.getAuth().login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
    kuzzle.getAuth().updateMyCredentials("local", "{\"username\":\"foo\",\"password\":\"bar\",\"other\":\"value\"}");
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
