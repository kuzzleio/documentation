---
layout: sdk.html.hbs
algolia: true
title: truncate
description: Remove all documents from collection
---

# truncate

Remove all documents from a collection while keeping the associated mapping.  
It is faster than deleting all documents from a collection.

## Signature

```go
Truncate(index string, collection string, options types.QueryOptions) error
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| ``options`` | types.QueryOptions | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return an error is something was wrong.

## Usage

[snippet=truncate]
