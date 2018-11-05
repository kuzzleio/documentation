_, err := kuzzle.Server.GetConfig(nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("Success")
}
