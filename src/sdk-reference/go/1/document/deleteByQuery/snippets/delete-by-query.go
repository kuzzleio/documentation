ids, err := kuzzle.Document.DeleteByQuery("nyc-open-data", "yellow-taxi", json.RawMessage(`{"query": {"match": {"capacity": 7}}}`), nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("Success")
}
