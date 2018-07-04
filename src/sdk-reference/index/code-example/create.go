// Create a new index named 'nyc-open-data'
response, err := kuzzle.index.Create("nyc-open-data")
if err != nil {
  fmt.Println(err)
} else {
  fmt.Println("index created")
}
