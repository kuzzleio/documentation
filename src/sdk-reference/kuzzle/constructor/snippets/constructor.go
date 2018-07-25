copts := types.NewOptions()
copts.SetPort(7512)
conn := websocket.NewWebSocket("kuzzle", copts)

kopts := types.NewOptions()
kopts.SetAutoResubscribe(false)
k, _ := kuzzle.NewKuzzle(conn, kopts)
