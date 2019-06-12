---
code: true
type: page
title: getStats
description: Returns statistics snapshots within a provided timestamp range.
---

# getStats

Returns statistics snapshots within a provided timestamp range.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

- the number of connected users per protocol (not available for all protocols)
- the number of ongoing requests
- the number of completed requests since the last frame
- the number of failed requests since the last frame

## Signature

```cpp
std::string getStats(time_t start, time_t end);

std::string getStats(time_t start, time_t end, const kuzzleio::query_options& options);
```

## Arguments

| Arguments   | Type                                 | Description                                       |
| ----------- | ------------------------------------ | ------------------------------------------------- |
| `startTime` | <pre>time_t</pre>                    | Timestamp of the begining of statistics frame set |
| `stopTime`  | <pre>time_t</pre>                    | Timestamp of the end of statistics frame set      |
| `options`   | <pre>kuzzleio::query_options\*</pre> | Query options                                     |

### options

Additional query options

| Option     | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A JSON string representing the statistics for the given period of time.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/get-stats.cpp
