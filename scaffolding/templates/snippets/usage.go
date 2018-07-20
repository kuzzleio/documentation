err := kuzzle.{{controller}}.{{action}}()

if err != nil {
  log.Fatal(err)
} else {
  fmt.Println("{{success_message}}")
}
