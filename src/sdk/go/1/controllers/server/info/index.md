---
code: true
type: page
title: Info
description: Returns information about Kuzzle server.
---

# Info



Returns information about Kuzzle: available API (base + extended), plugins, external services (Redis, Elasticsearch, ...), servers, etc.

## Arguments

```go
func (s* Server) Info(options types.QueryOptions) (json.RawMessage, error)
```

| Arguments | Type               | Description                         | Required |
| --------- | ------------------ | ----------------------------------- | -------- |
| `options` | types.QueryOptions | An object containing query options. | no       |

### **Options**

Additional query options

| Option     | Type | Description                                                                  | Default |
| ---------- | ---- | ---------------------------------------------------------------------------- | ------- |
| `Queuable` | bool | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |

## Return

Returns server informations as a `json.RawMessage` or a `KuzzleError`. See how to [handle error](/sdk/go/1/essentials/error-handling).

## Usage

<<< ./snippets/info.go
