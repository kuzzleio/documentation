package main

import (
	"fmt"

	"github.com/kuzzleio/sdk-go/connection/websocket"
	"github.com/kuzzleio/sdk-go/kuzzle"
)

func main() {
	c := websocket.NewWebSocket("kuzzle", nil)
	kuzzle, _ := kuzzle.NewKuzzle(c, nil)

	err := kuzzle.Index.SetAutoRefresh("nyc-open-data", true, nil)

	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println("autorefresh flag is set to true")
	}

}
