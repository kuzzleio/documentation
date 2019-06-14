copts := types.NewOptions()
copts.SetPort(7512)

// Use secure SSL connection to Kuzzle
copts.SetSslConnection(false)

// Instantiates the WebSocket protocol
conn := websocket.NewWebSocket("kuzzle", copts)

// Use it with Kuzzle
k, _ := kuzzle.NewKuzzle(conn, nil)