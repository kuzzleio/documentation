exists, err := kuzzle.Index.Exists("nyc-open-data", nil)

if err != nil {
  fmt.Println(err)
} else if exists == true {
  fmt.Println("index exists")
} else {
  fmt.Println("index does not exist")
}
