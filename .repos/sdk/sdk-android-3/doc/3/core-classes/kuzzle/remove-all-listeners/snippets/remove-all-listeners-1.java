
// Removes all listeners on the "unsubscribed" global event
kuzzle.removeAllListeners(Event.disconnected);

// Removes all listeners on all global events
kuzzle.removeAllListeners();
