try {
    String token = kuzzle.getAuth().login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
    TokenValidity res = kuzzle.getAuth().checkToken(token);
    if (res.getValid() == true)
      System.out.println("Success");
    else
      System.err.println(res.getState());
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
