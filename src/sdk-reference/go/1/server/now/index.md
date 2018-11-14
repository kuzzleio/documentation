---
layout: sdk.html.hbs
algolia: true
title: Now
description: Returns the current server timestamp, in Epoch-millis
order: 200
---

# Now

{{{since "1.0.0"}}}

Returns the current server timestamp, in Epoch-millis format.

## Signature

```go
func (s *Server) Now(options types.QueryOptions) (int64, error)
```

## Arguments

| Arguments | Type         | Description                           |
| --------- | ------------ | ------------------------------------- |
| `options` | QueryOptions | Query options. |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns current server timestamp as `int64` or a `KuzzleError`. See how to [handle error]({{ site_base_path }}sdk-reference/go/1/essentials/error-handling).

## Usage

[snippet=now]
