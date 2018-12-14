using Kuzzleio;
using System;

public class Example {
  static void Main() {
    WebSocket ws = new WebSocket("kuzzle");
    Kuzzle kuzzle = new Kuzzle(ws);

    kuzzle.connect();

    [snippet-code]
  }
}
