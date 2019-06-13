---
code: true
type: page
title: GetStrategies
description: Get all authentication strategies registered in Kuzzle.
---

# GetStrategies

Get all authentication strategies registered in Kuzzle.

## Signature

```go
func (a *Auth) GetStrategies(options types.QueryOptions) ([]string, error)
```

## Arguments

| Arguments | Type            | Description                                                       |
| --------- | --------------- | ----------------------------------------------------------------- |
| `options` | query_options\* | A pointer to a `kuzzleio::query_options` containing query options |

### **Options**

Additional query options

| Property   | Type | Description                       | Default |
| ---------- | ---- | --------------------------------- | ------- |
| `Queuable` | bool | Make this request queuable or not | `true`  |

## Return

An array of string containing the list of strategies and an error or nil.

## Usage

<<< ./snippets/get-strategies.go
