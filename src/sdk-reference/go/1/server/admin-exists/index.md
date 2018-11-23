---
layout: sdk.html.hbs
algolia: true
title: AdminExists
description: Returns information about Kuzzle server.
algolia: true
---

# AdminExists

{{{since "1.0.0"}}}

Checks that an administrator account exists.

## Arguments

```go
func (s *Server) AdminExists(options types.QueryOptions) (bool, error)
```

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | types.QueryOptions | An object containing query options. | no       |

### **Options**

Additional query options

| Option     | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | bool | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |


## Return

Returns a `bool` set to `true` if an admin exists and `false` if it does not, or a `KuzzleError`. See how to [handle error]({{ site_base_path }}sdk-reference/go/1/essentials/error-handling).

## Usage

[snippet=admin-exists]
