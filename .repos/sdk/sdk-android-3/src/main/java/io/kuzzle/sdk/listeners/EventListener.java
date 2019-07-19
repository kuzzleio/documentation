package io.kuzzle.sdk.listeners;

/**
 * The interface Event listener.
 */
public interface EventListener {

  /**
   * Trigger.
   *
   * @param args the args
   */
  void trigger(Object ... args);

}
