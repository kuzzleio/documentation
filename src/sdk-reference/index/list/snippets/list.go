indexes, err := kuzzle.Index.List(nil)

if err != nil {
  fmt.Println(err)
} else {
  fmt.Printf("Kuzzle contains %d indexes", len(indexes))
}
