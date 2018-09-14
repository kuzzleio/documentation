---
layout: sdk.html
algolia: true
title: delete
description: Deletes an index
order: 500
---

# Delete

Deletes an entire data index from Kuzzle.

## Signature

```go
Delete(index string, options types.QueryOptions) error
```

## Arguments

| Arguments | Type         | Description                           | Required |
| --------- | ------------ | ------------------------------------- | -------- |
| `index`   | String       | Index name                            | yes      |
| `options` | QueryOptions | Query options. | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns an error or `nil` if the request succeed.

## Usage

[snippet=delete]
