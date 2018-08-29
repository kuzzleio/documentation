MyListener *listener = new MyListener();
MyListener *other_listener = new MyListener();

kuzzle->addListener(CONNECTED, listener)->addListener(CONNECTED, other_listener);
