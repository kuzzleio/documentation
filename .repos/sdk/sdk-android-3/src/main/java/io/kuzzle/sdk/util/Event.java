package io.kuzzle.sdk.util;

import io.kuzzle.sdk.listeners.EventListener;

public abstract class Event implements EventListener {
  private io.kuzzle.sdk.enums.Event type;

  /**
   * Instantiates a new Event.
   *
   * @param type Event type
   */
  public Event(io.kuzzle.sdk.enums.Event type) {
    this.type = type;
  }

  public abstract void trigger(Object... args);

  /**
   * @return Event type value
   */
  public io.kuzzle.sdk.enums.Event getType() {
    return this.type;
  }
}
