kuzzle.addListener(Event.CONNECTED, new EventListener() {
  @Override
  public void trigger(String json_payload) {
    System.out.println(json_payload);
  }
}).addListener(Event.DISCONNECTED, new EventListener() {
  @Override
  public void trigger(String json_payload) {
    System.err.println(json_payload);
  }
});
