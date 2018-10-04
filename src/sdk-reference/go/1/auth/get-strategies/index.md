---
layout: sdk.html
algolia: true
title: GetStrategies
description: Get all authentication strategies registered in Kuzzle.
order: 200
---

# GetStrategies

Get all authentication strategies registered in Kuzzle.

## Signature

```go
func (a *Auth) GetStrategies(options types.QueryOptions) ([]string, error)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options`  | query_options*    | A pointer to a `query_options` containing query options

###### **Options**

Additional query options

| Option     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `Queuable` | bool | Make this request queuable or not | `true`

## Return

An array of string containing the list of strategies and an error or nil.

## Usage

[snippet=get-strategies]
