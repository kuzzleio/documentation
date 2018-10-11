// Type assertion of error to KuzzleError
err := kuzzle.Index.Create("nyc-open-data", nil)

if err != nil {
  fmt.Println(err.(types.KuzzleError).Message)

  if err.(types.KuzzleError).Status == 400 {
    fmt.Println("Try with an other name!")
  }
}
