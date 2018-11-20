---
layout: sdk.html.hbs
algolia: true
title: Now
description: Returns the current server timestamp, in Epoch-millis
---


# Now

{{{since "1.0.0"}}}

Returns the current server timestamp, in Epoch-millis format.

## Arguments

```go
func (s *Server) Now(options types.QueryOptions) (int64, error)
```

| Arguments | Type         | Description                           |
| --------- | ------------ | ------------------------------------- |
| `options` | types.QueryOptions | Query options. |

### **Options**

Additional query options

| Option     | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | bool | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |

## Return

Returns current server timestamp as `int64` or a `KuzzleError`. See how to [handle error]({{ site_base_path }}sdk-reference/go/1/essentials/error-handling).

## Usage

[snippet=now]
