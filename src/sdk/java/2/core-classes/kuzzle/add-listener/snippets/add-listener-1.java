
EventListener eventListener = new EventListener() {
  @Override
  public void trigger(Object... args) {
    // Actions to perform when receiving a 'subscribed' global event
  }
};
kuzzle.addListener(Event.connected, eventListener);
