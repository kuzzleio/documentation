package main

import (
	"fmt"

	"github.com/kuzzleio/sdk-go/connection/websocket"
	"github.com/kuzzleio/sdk-go/kuzzle"
	"github.com/kuzzleio/sdk-go/types"
)

func main() {
	[snippet-code]
	k.Server.Now(nil)
	fmt.Println("Everything is ok")
}
