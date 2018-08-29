---
layout: sdk.html
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
| `index`   | String       | Index name                            | yes      |
| `options` | QueryOptions | A structure containing query options. | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns a `boolean` that indicate whether the index exists, or an error

## Usage

[code-example=exists]
