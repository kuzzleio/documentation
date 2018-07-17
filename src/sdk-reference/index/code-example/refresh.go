err := kuzzle.Index.Refresh("nyc-open-data", nil)

if err != nil {
  fmt.Println(err)
} else {
  fmt.Println("0 shards fail to refresh")
}
