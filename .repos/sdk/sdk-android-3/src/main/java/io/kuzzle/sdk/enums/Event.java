package io.kuzzle.sdk.enums;

/**
 * The enum Event type.
 */
public enum Event {
  disconnected,
  reconnected,
  connected,
  error,
  tokenExpired,
  loginAttempt,
  offlineQueuePush,
  offlineQueuePop
}
