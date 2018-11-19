package main

import (
	"fmt"
	"os"
	"log"

	"github.com/kuzzleio/sdk-go/protocol/websocket"
	"github.com/kuzzleio/sdk-go/kuzzle"
)

func main()	{
	// Create websocket protocol, replace "kuzzle" with
	// your Kuzzle hostname like "localhost"
	c := websocket.NewWebSocket("kuzzle", nil)
	// Create Kuzzle SDK instances
	kuzzle, _ := kuzzle.NewKuzzle(c, nil)

	// ... then connect to the server.
	err := kuzzle.Connect()
	if err != nil {
		log.Fatal(err)
		os.Exit(1)
	}
	fmt.Println("Connected!")

	// Fresh installed Kuzzle is empty, so we need to create
	// a new index.
	if err := kuzzle.Index.Create("nyc-open-data", nil); err != nil {
		log.Fatal(err)
		os.Exit(1)
	}
	fmt.Println("Index nyc-open-data created!")

	// We can now create collection passing index and collection name.
	// Third argument is the previously declared mapping.
	if err := kuzzle.Collection.Create(
		"nyc-open-data",
		"yellow-taxi",
		nil,
		nil,
	); err != nil {
		log.Fatal(err)
		os.Exit(1)
	}
	fmt.Println("Collection yellow-taxi created!")

	// Finally, we can disconnect the SDK
	kuzzle.Disconnect()
}
