package main

import "fmt"

func main() {
    conn := websocket.NewWebSocket("kuzzle", nil)
    k, _ := kuzzle.NewKuzzle(conn, nil)

    err := k.Connect()

    if err != nil {
        fmt.Println(err.Error())
        return
    }

    _, err = k.Server.GetAllStats(nil)

    if err != nil {
        fmt.Println(err.Error())
        return
    }

    [snippet-code]
}
