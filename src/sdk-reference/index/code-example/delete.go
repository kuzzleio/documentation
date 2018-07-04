response, err := kuzzle.index.Delete("nyc-open-data")

if err != nil {
  fmt.Println(err)
} else {
  fmt.Println("index deleted")
}
