try {
    boolean exists = kuzzle.getServer().adminExists();
    System.out.println("Admin exists? " + exists);
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
