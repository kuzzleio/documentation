_, err := kuzzle.Server.Now(nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("Success")
}
