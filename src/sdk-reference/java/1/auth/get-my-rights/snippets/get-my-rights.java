try {
    kuzzle.getAuth().login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
    user_right rights = kuzzle.getAuth().getMyRights();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
