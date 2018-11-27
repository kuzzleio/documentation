---
layout: sdk.html.hbs
title: truncate
description: Remove all documents from collection
---

# truncate

Remove all documents from a collection while keeping the associated mapping.  
It is faster than deleting all documents from a collection.

## Signature

```cpp
void truncate(const std::string& index, const std::string& collection, kuzzleio::query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | const std::string& | Index name    | yes  |
| ``collection`` | const std::string& | Collection name    | yes  |
| ``options`` | kuzzleio::query_options* | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Usage

[snippet=truncate]
