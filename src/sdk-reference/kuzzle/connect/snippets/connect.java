String error = kuzzle.connect();

if (error == null) {
  System.out.println("Successfully connected");
} else {
  System.err.println(error);
}
