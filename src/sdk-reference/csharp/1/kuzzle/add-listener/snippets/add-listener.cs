EventListener listener = [](const string payload) {
  Console.WriteLine(payload);
};
EventListener other_listener = [](const string payload) {
  Console.Error.WriteLine(payload);
};

kuzzle.addListener(CONNECTED, listener).addListener(CONNECTED, other_listener);

Console.WriteLine("Listener successfully added");
