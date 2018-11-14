---
layout: sdk.html.hbs
algolia: true
title: Info
description: Returns information about Kuzzle server.
order: 200
---

# Info

{{{since "1.0.0"}}}

Returns information about Kuzzle: available API (base + extended), plugins, external services (Redis, Elasticsearch, ...), servers, etc.

## Signature
```go
func (s* Server) Info(options types.QueryOptions) (json.RawMessage, error)
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

Returns server informations as a `json.RawMessage` or a `KuzzleError`. See how to [handle error]({{ site_base_path }}sdk-reference/go/1/essentials/error-handling).

## Usage

[snippet=info]
