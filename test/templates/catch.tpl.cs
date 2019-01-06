using Kuzzleio;
using System;
using System.Collections.Generic;

public class Example {
  static void Main() {
    WebSocket ws = new WebSocket("kuzzle");
    Kuzzle kuzzle = new Kuzzle(ws);

    try {
      kuzzle.connect();
    } catch (KuzzleException e) {
      Console.Error.WriteLine(e.getMessage());
    }

    try {
      [snippet-code]
    } catch (BadRequestException e) {
      Console.WriteLine("Success");
    }
  }
}
