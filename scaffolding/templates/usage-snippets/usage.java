try {
    kuzzle.get<%= _.upperFirst(_.camelCase(controller)) %>().<%= action %>();
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
