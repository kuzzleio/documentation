err := kuzzle.Index.SetAutoRefresh("nyc-open-data", true, nil)

if err != nil {
  fmt.Println(err)
} else {
  fmt.Println("autorefresh flag is set to true")
}
