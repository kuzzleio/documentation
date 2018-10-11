specifications := json.RawMessage(`{ "nyc-open-data": { "yellow-taxi": { "strict": false, "fields": { "license": { "mandatory": true, "type": "string" } } } } }`)
vr, err := kuzzle.Collection.ValidateSpecifications(specifications, nil)

if err != nil {
  log.Fatal(err)
} else if vr.Valid == true {
  fmt.Println("Success")
}
