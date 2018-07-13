cOpts := types.NewOptions()
cOpts.setPort(7512)
c := websocket.NewWebSocket("kuzzle", cOpts)

kOpts := types.NewOptions()
kOpts.SetAutoResubscribe(false)
kuzzle, err := kuzzle.NewKuzzle(c, kOpts)
