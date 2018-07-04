err := kuzzle.Index.Create("nyc-open-data", nil)

if err != nil {
  fmt.Println(err)
} else {
  fmt.Println("index created")
}
