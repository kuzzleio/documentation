mapping := json.RawMessage(`{"properties":{"license": {"type": "keyword"}}}`)
err := kuzzle.Collection.Create("nyc-open-data", "yellow-taxi", mapping, nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("Success")
}
