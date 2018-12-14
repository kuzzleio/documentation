---
layout: sdk.html.hbs
title: truncate
description: Remove all documents from collection
---

# truncate

Remove all documents from a collection while keeping the associated mapping.  
It is faster than deleting all documents from a collection.

## Signature

```csharp
public void truncate(string index, string collection);
public void truncate(string index, string collection, QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| ``options`` | Kuzzleio::QueryOptions | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Usage

[snippet=truncate]
