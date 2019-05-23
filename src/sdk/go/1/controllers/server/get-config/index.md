---
code: true
type: page
title: GetConfig
description: Returns the current Kuzzle configuration.
---

# GetConfig



Returns the current Kuzzle configuration.

<div class="alert alert-warning">
  This route should only be accessible to administrators, as it might return sensitive information about the backend.
</div>

## Arguments

```go
func (s *Server) GetConfig(options types.QueryOptions) (json.RawMessage, error)
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

Returns server configuration as a `json.RawMessage` or a `KuzzleError`. See how to [handle error](/sdk/go/1/error-handling).

## Usage

<<< ./snippets/get-config.go
