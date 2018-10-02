package main

import (
	"fmt"
	"log"
	"os"

	"github.com/kuzzleio/sdk-go/connection/websocket"
	"github.com/kuzzleio/sdk-go/kuzzle"
	"github.com/kuzzleio/sdk-go/types"
)

func main() {
	c := websocket.NewWebSocket("kuzzle", nil)
	kuzzle, _ := kuzzle.NewKuzzle(c, nil)

	connectErr := kuzzle.Connect()
	if connectErr != nil {
		log.Fatal(connectErr)
		os.Exit(1)
	}

	[snippet-code]
}
