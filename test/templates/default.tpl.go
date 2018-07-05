package main

import (
	"encoding/json"
	"fmt"

	"github.com/kuzzleio/sdk-go/connection/websocket"
	"github.com/kuzzleio/sdk-go/kuzzle"
)

func main() {
	c := websocket.NewWebSocket("kuzzle", nil)
	kuzzle, _ := kuzzle.NewKuzzle(c, nil)

  [snippet-code]
}
