err := kuzzle.Index.RefreshInternal(nil)

if err != nil {
  fmt.Println(err)
} else {
  fmt.Println("Internal index successfully refreshed")
}
