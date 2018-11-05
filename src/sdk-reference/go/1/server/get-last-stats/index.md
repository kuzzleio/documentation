---
layout: sdk.html.hbs
algolia: true
title: GetLastStats
description: Returns the most recent statistics snapshot.
order: 200
---

# GetLastStats

{{{since "1.0.0"}}}

Returns the most recent statistics snapshot.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Signature

```go
func (s *Server) GetLastStats(options types.QueryOptions) (json.RawMessage, error)
```
## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | QueryOptions | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns the most recent statistics snapshot as a `json.RawMessage` or an error.

## Return

## Usage

[snippet=get-last-stats]
