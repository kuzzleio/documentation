package main

import (
	"encoding/json"
	"fmt"
	"log"
	"os"

	"github.com/kuzzleio/sdk-go/kuzzle"
	"github.com/kuzzleio/sdk-go/protocol/websocket"
	"github.com/kuzzleio/sdk-go/types"
)

func main() {
	// Create websocket protocol, replace "kuzzle" with
	// your Kuzzle hostname like "localhost"
	c := websocket.NewWebSocket("kuzzle", nil)
	// Instanciate a Kuzzle client
	kuzzle, _ := kuzzle.NewKuzzle(c, nil)

	// ... then connect to the server.
	if err := kuzzle.Connect(); err != nil {
		log.Fatal(err)
		os.Exit(1)
	}
	fmt.Println("Connected!")

	// Avoid program exit before receiving notification
	exit := make(chan bool)
	// Start an async listener
	listener := make(chan types.NotificationResult)
	go func() {
		notification := <-listener

		// Access document content through notification.
		// We can organize data like we want using the Go Reflection API...
		var doc struct {
			Name     string `json:"name"`
			Birthday string `json:"birthday"`
			License  string `json:"license"`
		}

		// ...and json.Unmarshal() function.
		json.Unmarshal(notification.Result.Content, &doc)
		fmt.Printf("Driver %s born on %s got a %s license.\n",
			doc.Name,
			doc.Birthday,
			doc.License,
		)

		// It's time to quit.
		exit <- true
	}()

	// Subscribe to notifications for drivers having B drive license.
	filters := json.RawMessage(`
		{
			"equals": {
				"license":"B"
			}
		}
	`)

	// Start subscribing.
	if _, err := kuzzle.Realtime.Subscribe(
		"nyc-open-data",
		"yellow-taxi",
		filters,
		listener,
		nil,
	); err != nil {
		log.Fatal(err)
		os.Exit(1)
	}
	fmt.Println("Successfully subscribing!")

	// Now let's trigger our subscription.
	content := json.RawMessage(`
		{
			"name": "John",
			"birthday": "1995-11-27",
			"license": "B"
		}
	`)

	// Create the new document matching our filter
	if _, err := kuzzle.Document.Create(
		"nyc-open-data",
		"yellow-taxi",
		"",
		content,
		nil,
	); err != nil {
		log.Fatal(err)
		os.Exit(1)
	}
	fmt.Println("New document added to yellow-taxi collection!")

	// Wait for notification
	<-exit

	// Finally, we can disconnect the SDK.
	kuzzle.Disconnect()
}
