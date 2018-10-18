// Avoid program exit before receiving notification
exitPrgm := make(chan bool)

// Subscribe to notifications for documents containing a 'name' property
filters := json.RawMessage(`{ "range": { "age": { "lte": 20 } } }`)

// Start an async listener
listener := make(chan types.KuzzleNotification)
go func() {
  count := 0

  for count < 2 {
    notification := <-listener

    if notification.Type == "user" {
      fmt.Printf("Currently %d users in the room\n", notification.Result.Count)
    }

    count += 1
  }

  exitPrgm <- true
}()

options := types.NewRoomOptions()
options.SetUsers(types.USERS_ALL)

_, err := kuzzle.Realtime.Subscribe("nyc-open-data", "yellow-taxi", json.RawMessage(`{}`), listener, options)

if err != nil {
  log.Fatal(err)
}

opfions := types.NewRoomOptions()
options.SetUsers(types.USERS_ALL)
options.SetVolatile(json.RawMessage(`{ "username": "nina vkote" }`))

_, err := kuzzle.Realtime.Subscribe("nyc-open-data", "yellow-taxi", json.RawMessage(`{}`), listener, opfions)

<-exitPrgm
