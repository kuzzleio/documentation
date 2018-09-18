specifications := json.RawMessage(`{ "nyc-open-data": { "yellow-taxi": { "strict": false, "fields": { "license": { "mandatory": true, "type": "string" } } } } }`)
response, err := kuzzle.Collection.UpdateSpecifications("nyc-open-data", "yellow-taxi", specifications, nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("Success")
}
