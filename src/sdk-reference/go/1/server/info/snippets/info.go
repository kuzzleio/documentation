_, err := kuzzle.Server.Info(nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("Success")
}
