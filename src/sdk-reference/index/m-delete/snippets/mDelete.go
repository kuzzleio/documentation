indexes := []string{"nyc-open-data", "mtp-open-data"}

deleted, err := kuzzle.Index.MDelete(indexes, nil)

if err != nil {
  fmt.Println(err)
} else {
  fmt.Printf("Successfully deleted %d indexes", len(deleted))
}
