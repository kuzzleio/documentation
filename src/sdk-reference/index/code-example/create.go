response, err := kuzzle.index.Create("nyc-open-data")

if err != nil {
  fmt.Println(err)
} else {
  fmt.Println("index created")
}
