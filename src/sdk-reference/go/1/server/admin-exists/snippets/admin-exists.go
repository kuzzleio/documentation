_, err := kuzzle.Server.AdminExists(nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("Success")
}
