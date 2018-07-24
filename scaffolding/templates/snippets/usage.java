try {
    kuzzle.get{{controller}}().{{action}}();
    System.out.println("{{success_message}}");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
