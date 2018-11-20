---
layout: full.html.hbs
algolia: true
title: Connecting to Kuzzle
description: Connecting to Kuzzle using HTTP, WebSocket or Protocol plugins
order: 100
---



# Connecting to Kuzzle

A connection to Kuzzle can be made using different protocols. Currently Kuzzle natively supports HTTP, WebSocket and Socket.io, but other protocols can be added by implementing the [Protocol]({{ site_base_path }}protocols/1) interface.


## WebSocket

By default, Kuzzle has websockets enabled, accepting requests via the http server on port 7512.

### Example

```html
<script>
    var socket = new WebSocket("ws://localhost:7512");
</script>
```


## MQTT protocols

Kuzzle provides a plugin that supports the [MQTT protocol](https://github.com/kuzzleio/kuzzle-plugin-mqtt).
Using the plugin you can perform two-way communication between your application and Kuzzle.


### Example


```bash
# shell 1
node_modules/.bin/mqtt subscribe -v -h rabbit -t Kuzzle

# shell 2
node_modules/.bin/mqtt publish -h rabbit -t Kuzzle -m '{
  "collection":"index",
  "collection":"collection",
  "controller": "document",
  "action": "createOrReplace",
  "body": {
    "firstName": "John",
    "lastName": "Doe"
  }
}'

# shell 1 (prettified)
Kuzzle {
  "error": null,
  "result": {
    "_id": "AVF8NG3k5ZVpUuiPrN1K",
    "_index": "index",
    "_source": {"firstName":"John", "lastName":"Doe"},
    "_type": "collection",
    "_version": 1,
    "action": "createOrReplace",
    "collection": "collection",
    "controller": "document",
    "created": true,
    "volatile": {},
    "requestId":"5cb4d930-62f4-4393-afc1-9a71e284a214"
  },
  "status": 200
}
```

By default, the MQTT plugin protocol listens on port 1883.

<aside class="notice">
    The examples given in this documentation use the CLI client from the mqtt node.js
    library that is shipped in the Kuzzle Docker image.<br />
    To test them out yourself you will need to enter into the container shell once your docker compose stack is up and running:<br />
    <code>docker exec -ti kuzzle_kuzzle_1 bash</code>
</aside>
