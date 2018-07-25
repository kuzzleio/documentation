try {
    User currentUser = kuzzle.getAuth().getCurrentUser();
    System.out.println(currentUser.getId());
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
