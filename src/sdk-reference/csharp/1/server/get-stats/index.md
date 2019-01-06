---
layout: sdk.html.hbs
title: getStats
description: Returns statistics snapshots within a provided timestamp range.
---

# getStats

Returns statistics snapshots within a provided timestamp range.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Signature

```csharp
public string getStats(SWIGTYPE_p_time_t start, SWIGTYPE_p_time_t end);
public string getStats(SWIGTYPE_p_time_t start, SWIGTYPE_p_time_t end, QueryOptions options);
```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `startTime` | <pre>time_t</pre>   | Timestamp of the begining of statistics frame set |
| `stopTime`  | <pre>time_t</pre>   | Timestamp of the end of statistics frame set      |
| `options`   | <pre>Kuzzleio::QueryOptions\*</pre> | Query options        |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A JSON string representing the statistics for the given period of time.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=get-stats]
