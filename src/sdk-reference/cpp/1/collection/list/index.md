---
layout: sdk.html
algolia: true
title: list
description: Returns the collection list of an index
order: 200
---

# list

Returns the complete list of realtime and stored data collections in requested index sorted by name in alphanumerical order.  
The `from` and `size` arguments allow pagination. They are returned in the response if provided.


## Signature

```cpp
std::string list(const std::string& index, kuzzleio::query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | const std::string& | Index name    | yes  |
| ``options`` | kuzzleio::query_options* | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `from` | long | Offset of the first result | `0` |
| `size` | long | Maximum number of returned results | `10` |

## Return

Returns a string containing a JSON representation of the API response.

## Usage

[snippet=list]
