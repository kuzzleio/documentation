---
layout: sdk.html.hbs
algolia: true
title: list
description: List the indexes
---


# List

Get the complete list of data indexes handled by Kuzzle.

## Signature

```go
List(options types.QueryOptions) ([]string, error)
```

## Arguments

| Arguments | Type         | Description                           |
| --------- | ------------ | ------------------------------------- |
| `options` | QueryOptions | Query options |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns an `Array` of strings containing the list of indexes names present in Kuzzle or an error

## Usage

[snippet=list]
