request := types.KuzzleRequest{Controller: "document", Action: "create"}
request.Index = "nyc-open-data"
request.Collection = "yellow-taxi"
request.Id = "my-custom-document-id"
request.Body = json.RawMessage("{\"trip_distance\": 4.23, \"passenger_count\": 2}")

options := types.NewQueryOptions()
options.SetRefresh("wait_for")

ch := make(chan *types.KuzzleResponse)

go kuzzle.Query(&request, options, ch)
response := <-ch

if response.Status == 200 {
  fmt.Println("Document created")
} else {
  fmt.Println(response.Error.Message)
}
