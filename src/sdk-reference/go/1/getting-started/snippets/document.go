package main

import (
	"encoding/json"
	"fmt"
	"log"
	"os"

	"github.com/kuzzleio/sdk-go/kuzzle"
	"github.com/kuzzleio/sdk-go/protocol/websocket"
)

func main() {
	// Create websocket protocol, replace "kuzzle" with
	// your Kuzzle hostname like "localhost"
	c := websocket.NewWebSocket("kuzzle", nil)
	// Instanciate a Kuzzle client
	kuzzle, _ := kuzzle.NewKuzzle(c, nil)

	// Connect to the server.
	if err := kuzzle.Connect(); err != nil {
		log.Fatal(err)
		os.Exit(1)
	}
	fmt.Println("Connected!")

	// Once connected, we can start to create document content...
	content := json.RawMessage(`
		{
			"name": "Sirkis",
			"birthday": "1959-06-22",
			"license": "B"
		}
	`)

	// ... then save our document in "yellow-taxi" collection.
	if _, err := kuzzle.Document.Create(
		"nyc-open-data",
		"yellow-taxi",
		"some-id",
		content,
		nil,
	); err != nil {
		log.Fatal(err)
		os.Exit(1)
	}
	fmt.Println("New document added to yellow-taxi collection!")

	// Finally, we can disconnect the SDK.
	kuzzle.Disconnect()
}
