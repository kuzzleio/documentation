using Kuzzleio;
using System;
using System.Collections.Generic;

public class Example {
  static void Main() {
    WebSocket ws = new WebSocket("kuzzle");
    Kuzzle kuzzle = new Kuzzle(ws);

    [snippet-code]
  }
}
