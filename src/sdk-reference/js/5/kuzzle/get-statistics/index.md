---
layout: sdk.html.hbs
algolia: true
title: getStatistics
description: Kuzzle:getStatistics
---

  

# getStatistics

> Without argument, retrieves the last statistic frame in an array:
> When providing a timestamp, retrieves all frames recorded after that timestamp:
Kuzzle Backend monitors active connections, and ongoing/completed/failed requests.  
This method returns either the last statistics frame, or a set of frames starting from a provided timestamp.


## Options

| Option | Type | Description | Default |
|
### Callback Response

Returns an `array` containing one or more statistics frame (as JSON objects).

## Usage

[snippet=get-statistics-1]

[snippet=get-statistics-2]
> Callback response:

```json
[
  {
    "connections": { "socketio": 1 },
    "ongoingRequests": { "rest": 0, "socketio": 0 },
    "completedRequests": { "mqtt": 37, "socketio": 17 },
    "failedRequests": { "socketio": 1 },
    "timestamp": "1453110641308"
  }
]
```