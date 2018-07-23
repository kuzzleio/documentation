opts := types.NewOptions()
opts.SetPort(7512)
conn := websocket.NewWebSocket("kuzzle", opts)

opts := types.NewOptions()
opts.SetAutoResubscribe(false)
k, _ := kuzzle.NewKuzzle(conn, opts)
