---
layout: sdk.html.hbs
algolia: true
title: getAllStatistics
description: Kuzzle:getAllStatistics
algolia: true
---
  

# getAllStatistics
> Callback response example:

```json
[{ "connections": { "socketio": 1 },
    "ongoingRequests": { "rest": 0, "socketio": 0 },
    "completedRequests": { "mqtt": 37, "socketio": 17 },
    "failedRequests": { "socketio": 1 },
    "timestamp": "1453110641308" },
  { "connections": { "socketio": 1 },
    "ongoingRequests": { "rest": 0, "socketio": 0 },
    "completedRequests": { "socketio": 34 },
    "failedRequests": { "socketio": 3 },
    "timestamp": "1453110642308" },
  { "connections": {},
    "ongoingRequests": { "rest": 0, "socketio": 0 },
    "completedRequests": { "socketio": 40 },
    "failedRequests": {},
    "timestamp": "1453110643308" }]
```

Kuzzle monitors active connections, and ongoing/completed/failed requests.  
This method returns all available statistics from Kuzzle.

---

## getAllStatistics([options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

### Callback Response

Returns an array of JSON objects that each contain a statistics frame.

## Usage

[snippet=get-all-statistics-1]
