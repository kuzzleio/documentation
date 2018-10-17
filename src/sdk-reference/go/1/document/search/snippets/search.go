for i := 0; i < 5; i++ {
  kuzzle.Document.Create("nyc-open-data", "yellow-taxi", "", json.RawMessage(`{
    "category": "suv"
  }`), nil)
}
for i := 5; i < 15; i++ {
  kuzzle.Document.Create("nyc-open-data", "yellow-taxi", "", json.RawMessage(`{
    "category": "limousine"
  }`), nil)
}
kuzzle.Index.Refresh("nyc-open-data", nil)

response, err := kuzzle.Document.Search("nyc-open-data", "yellow-taxi", json.RawMessage(`{
  "query": {
    "match": {
      "category": "suv"
    }
  }
}`), nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Printf("Successfully retrieved %d documents", response.Total)
}
