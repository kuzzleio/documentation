---
layout: sdk.html.hbs
algolia: true
title: GetAllStats
description: Gets all stored internal statistic snapshots.
order: 200
---

# GetAllStats

{{{since "1.0.0"}}}

Gets all stored internal statistic snapshots.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Signature

```go
func (s *Server) GetAllStats(options types.QueryOptions) (json.RawMessage, error)
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

Returns all stored internal statistic snapshots as a `json.RawMessage` or a `KuzzleError`. See how to [handle error]({{ site_base_path }}sdk-reference/go/1/essentials/error-handling).

## Usage

[snippet=get-all-stats]
