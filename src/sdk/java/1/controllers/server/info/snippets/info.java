try {
    String info = new String(kuzzle.getServer().info());
    System.out.println("Kuzzle Server information as JSON string: " + info);
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
