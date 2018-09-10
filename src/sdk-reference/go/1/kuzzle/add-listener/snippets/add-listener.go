ch := make(chan interface{})

kuzzle.AddListener(event.Connected, ch)
