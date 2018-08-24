cu, err := kuzzle.Auth.GetCurrentUser()

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println(cu.Id)
}
