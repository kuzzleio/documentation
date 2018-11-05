_, err := kuzzle.Server.GetLastStats(nil)

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("Success")
}
