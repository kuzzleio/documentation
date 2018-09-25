specifications := json.RawMessage(`{ "nyc-open-data": { "yellow-taxi": { "strict": false, "fields": { "license": { "mandatory": true, "type": "string" } } } } }`)
valid, err := kuzzle.Collection.ValidateSpecifications(specifications, nil)

if err != nil {
  log.Fatal(err)
} else if valid == true {
  fmt.Println("Success")
}
