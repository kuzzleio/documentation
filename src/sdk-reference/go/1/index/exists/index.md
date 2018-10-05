---
layout: sdk.html.hbs
algolia: true
title: exists
description: Check for index existence
order: 300
---

# Exists

Checks if the given index exists in Kuzzle.

## Signature

```go
Exists(index string, options types.QueryOptions) (bool, error)
```

## Arguments

| Arguments | Type         | Description                           | Required |
| --------- | ------------ | ------------------------------------- | -------- |
| `index`   | string       | Index name                            | yes      |
| `options` | QueryOptions | Query options. | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns a `bool` that indicate whether the index exists, or an error

## Usage

[snippet=exists]
