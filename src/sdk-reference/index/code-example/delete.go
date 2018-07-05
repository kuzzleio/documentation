err := kuzzle.Index.Delete("nyc-open-data", nil)

if err != nil {
  fmt.Println(err)
} else {
  fmt.Println("index deleted")
}
