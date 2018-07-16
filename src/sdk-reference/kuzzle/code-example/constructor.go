opts := types.NewOptions()
opts.SetPort(7512)
c := websocket.NewWebSocket("kuzzle", opts)

o := types.NewOptions()
o.SetAutoResubscribe(false)
k, _ := kuzzle.NewKuzzle(c, o)
