try {
    kuzzle.getIndex().create("nyc-open-data");
} catch(GatewayTimeoutException e) {
  /* Won't be called */
} catch (BadRequestException e) {
  System.err.println(e.getMessage());
  System.err.println("Try with an other name!");
} catch (KuzzleException e) {
  /* Won't be called */
}
