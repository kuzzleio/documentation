count, err := kuzzle.Document.Count("nyc-open-data", "yellow-taxi", json.RawMessage(`{"query": {"match": {"licence": "valid"}}}`), nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Printf("Found %d documents matching licence:valid\n", count)
}
