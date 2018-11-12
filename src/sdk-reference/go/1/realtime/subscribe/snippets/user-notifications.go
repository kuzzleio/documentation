// Avoid program exit before receiving notification
exitPrgm := make(chan bool)

// Subscribe to notifications for documents containing a 'name' property
filters := json.RawMessage(`{ "exists": "name" }`)

// Start an async listener
listener := make(chan types.KuzzleNotification)
go func() {
  notification := <-listener

  if notification.Type == "user" {
    fmt.Printf("Volatile data: %s\n", notification.Volatile)
    // Volatile data: {"sdkVersion":"1.0.0","username":"nina vkote"}
    fmt.Printf("Currently %d users in the room\n", notification.Result.Count)
  }

  exitPrgm <- true
}()

options := types.NewRoomOptions()
options.SetUsers(types.USERS_ALL)

_, err := kuzzle.Realtime.Subscribe(
	"nyc-open-data",
	"yellow-taxi",
	filters,
	listener,
	options)

if err != nil {
  log.Fatal(err)
}

// instantiate a second kuzzle client because
// the same sdk instance does not receive their own notifications
f := websocket.NewWebSocket("kuzzle", nil)
fuzzle, _ := kuzzlepkg.NewKuzzle(f, nil)

connectErr = fuzzle.Connect()
if connectErr != nil {
  log.Fatal(connectErr)
  os.Exit(1)
}

// Set some volatile data
fuzzle.SetVolatile(json.RawMessage(`{ "username": "nina vkote" }`))

// Subscribe to the same room with the second client
fuzzle.Realtime.Subscribe(
	"nyc-open-data",
	"yellow-taxi",
	filters,
	make(chan types.KuzzleNotification),
	nil)

<-exitPrgm
