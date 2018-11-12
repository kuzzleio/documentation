---
layout: sdk.html.hbs
algolia: true
title: delete
description: Deletes an index
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
| `index`   | string       | Index name                            | yes      |
| `options` | QueryOptions | Query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns an error or `nil` if the request succeed.

## Usage

[snippet=delete]
