package io.kuzzle.sdk.util;

import java.util.HashMap;
import io.kuzzle.sdk.listeners.EventListener;

public class EventList extends HashMap<EventListener, Event> {
  public long lastEmitted = 0;
}
