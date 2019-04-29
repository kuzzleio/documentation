mapping := json.RawMessage(`{ "dynamic": "false","_meta": { "area": "Panipokhari" }, "properties":{"plate": {"type": "keyword"}}}`)
err := kuzzle.Collection.UpdateMapping("nyc-open-data", "yellow-taxi", mapping, nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("Success")
}
