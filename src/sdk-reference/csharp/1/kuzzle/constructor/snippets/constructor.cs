string hostname = "kuzzle";
WebSocket ws = new WebSocket(hostname);

options options = new options();
options.auto_resubscribe = false;

Kuzzle kuzzle = new Kuzzle(ws, options);

Console.WriteLine("New SDK instance successfully created");
