---
layout: sdk.html.hbs
algolia: true
title: GetConfig
description: Returns the current Kuzzle configuration.
order: 200
---

# GetConfig

{{{since "1.0.0"}}}

Returns the current Kuzzle configuration.

<div class="alert alert-warning">
  This route should only be accessible to administrators, as it might return sensitive information about the backend.
</div>

## Signature

```go
func (s *Server) GetConfig(options types.QueryOptions) (json.RawMessage, error)
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

Returns server configuration as a `json.RawMessage` or an error.

## Usage

[snippet=get-config]
