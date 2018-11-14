---
layout: sdk.html.hbs
algolia: true
title: AdminExists
description: Returns information about Kuzzle server.
order: 200
---

# AdminExists

{{{since "1.0.0"}}}

Checks that an administrator account exists.

## Signature

```go
func (s *Server) AdminExists(options types.QueryOptions) (bool, error)
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

Returns if an admin exists as a `bool` or a `KuzzleError`. See how to [handle error]({{ site_base_path }}sdk-reference/go/1/essentials/error-handling).

## Usage

[snippet=admin-exists]
