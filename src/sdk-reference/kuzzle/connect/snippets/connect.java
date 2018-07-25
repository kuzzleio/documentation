String error = kuzzle.connect();

if (error) {
  System.err.println(error);
} else {
  System.out.println("Successfully connected");
}
