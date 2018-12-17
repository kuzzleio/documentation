---
layout: sdk.html.hbs
title: getLastStats
description: Returns the most recent statistics snapshot.
---

# getLastStats

{{{since "1.0.0"}}}

Returns the most recent statistics snapshot.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Signature

```csharp
public string getLastStats();
public string getLastStats(QueryOptions options);
```

### **Options**

Additional query options

| Option     | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |

## Return
Returns a JSON string representing the most recent statistics snapshot.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=get-last-stats]
