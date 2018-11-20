---
layout: full.html.hbs
algolia: true
title: getStats
---


# getStats

{{{since "1.0.0"}}}

Returns statistics snapshots within a provided timestamp range.


## Response

Returns the found statistic snapshots in the following format:

* `hits`: array of statistic snapshots. By default, snapshots are made every 10 seconds and they are stored for 1 hour. Each snapshot is an object with the following properties:
  * `completedRequests`: completed requests, per network protocol
  * `connections`: number of active connections, per network protocol
  * `failedRequests`: failed requests, per network protocol
  * `ongoingRequests`: requests underway, per network protocol
  * `timestamp`: snapshot timestamp, in Epoch-millis format
* `total`: total number of available snapshots

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "getStats",
  "controller": "server",
  "requestId": "<unique request identifier>",
  "result": {
    "total": 1,
    "hits": [
      {
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
    ]
  }
}
```
