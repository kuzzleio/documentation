---
layout: sdk.html.hbs
algolia: true
title: GetStats
description: Returns statistics snapshots within a provided timestamp range.
order: 200
---

# GetStats
{{{since "1.0.0"}}}

Returns statistics snapshots within a provided timestamp range.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Signature

```go
func (s *Server) GetStats(startTime *time.Time, stopTime *time.Time, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `startTime` | time.Time | begining of statistics frame set (timestamp or datetime format) | yes       |
| `stopTime`  | time.Time | end of statistics frame set (timestamp or datetime format)      | yes       |
| `options`   | Object         | An object containing query options.                             | no        |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns snapshots within the provided timestamp range as a `json.RawMessage` or a `KuzzleError`. See how to [handle error]({{ site_base_path }}sdk-reference/go/1/essentials/error-handling).

## Usage

[snippet=get-stats]
