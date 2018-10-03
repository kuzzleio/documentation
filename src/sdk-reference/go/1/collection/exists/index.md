---
layout: sdk.html.hbs
algolia: true
title: exists
description: Check if collection exists
order: 200
---

# exists

Check if a collection exists in Kuzzle.

## Signature

```go
Exists(index string, collection string, options types.QueryOptions) (bool, error)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| ``options`` | types.QueryOptions | An object containing query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

True if the collection exists

## Usage

[snippet=exists]
