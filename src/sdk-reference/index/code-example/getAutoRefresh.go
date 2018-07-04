response, err := kuzzle.index.GetAutoRefresh("nyc-open-data")

if err != nil {
  fmt.Println(err)
} else if response == true {
  fmt.Println("autorefresh is true")
} else {
  fmt.Println("autorefresh is false")
}
