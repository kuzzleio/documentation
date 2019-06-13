kuzzle.addListener(Event.CONNECTED, new EventListener() {
  @Override
  public void trigger(String jsonPayload) {
    System.out.println(jsonPayload);
  }
}).addListener(Event.DISCONNECTED, new EventListener() {
  @Override
  public void trigger(String jsonPayload) {
    System.err.println(jsonPayload);
  }
});
