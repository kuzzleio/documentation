---
layout: sdk.html.hbs
title: getAllStats
description: Gets all stored internal statistic snapshots.
---

# getAllStats

{{{since "1.0.0"}}}

Gets all stored internal statistic snapshots.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame


## Signature

```csharp
public string getAllStats();
public string getAllStats(QueryOptions options);
```

### **Options**

Additional query options

| Option     | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |


## Return
Returns all stored internal statistic snapshots as `string`.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=get-all-stats]
