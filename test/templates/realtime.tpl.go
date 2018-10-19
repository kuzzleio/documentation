package main

import (
	"fmt"
	"log"
	"os"
	"time"

	"github.com/kuzzleio/sdk-go/protocol/websocket"
	kuzzlepkg "github.com/kuzzleio/sdk-go/kuzzle"
	"github.com/kuzzleio/sdk-go/types"
)

func main() {
	c := websocket.NewWebSocket("kuzzle", nil)
	kuzzle, _ := kuzzlepkg.NewKuzzle(c, nil)

	connectErr := kuzzle.Connect()
	if connectErr != nil {
		log.Fatal(connectErr)
		os.Exit(1)
	}

	go func() {
		time.Sleep(1 * time.Second)
		fmt.Println("Timeout exceeded")
		os.Exit(2)
	}()

	[snippet-code]
}
