response, err := kuzzle.index.Exists("nyc-open-data")

if err != nil {
  fmt.Println(err)
} else if response == true {
  fmt.Println("index exists")
} else {
  fmt.Println("index does not exist")
}
