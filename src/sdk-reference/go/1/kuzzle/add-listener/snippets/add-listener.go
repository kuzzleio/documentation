ch := make(chan json.RawMessage)

kuzzle.AddListener(event.Connected, ch)
