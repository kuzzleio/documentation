func main() {
    conn := websocket.NewWebSocket("localhost:7512", nil)
    k, _ := kuzzle.NewKuzzle(conn, nil)

    res, err := k.GetAllStatistics(nil)

    if err != nil {
        fmt.Println(err.Error())
        return
    }

    for _, stat := range res {
        fmt.Println(
            stat.Timestamp, 
            stat.FailedRequests, 
            stat.Connections, 
            stat.CompletedRequests, 
            stat.OngoingRequests
        )
    }
}