---
layout: full.html.hbs
algolia: true
title: getLastStats
---


# getLastStats

{{{since "1.0.0"}}}

Returns the most recent statistics snapshot.


## Response

Returns the last statistic snapshot, with the following properties:

* `completedRequests`: completed requests, per network protocol
* `connections`: number of active connections, per network protocol
* `failedRequests`: failed requests, per network protocol
* `ongoingRequests`: requests underway, per network protocol
* `timestamp`: snapshot timestamp, in Epoch-millis format

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "getLastStats",
  "controller": "server",
  "requestId": "<unique request identifier>",
  "result": {
    "completedRequests": {
      "websocket": 148,
      "http": 24,
      "mqtt": 78
    },
    "failedRequests": {
      "websocket": 3
    },
    "ongoingRequests": {
      "mqtt": 8,
      "http": 2
    }
    "connections": {
      "websocket": 13
    },
    "timestamp": 1453110641308
  }
}
```
