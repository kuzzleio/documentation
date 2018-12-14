---
layout: sdk.html.hbs
title: list
description: Returns the collection list of an index
---

# list

Returns the complete list of realtime and stored data collections in requested index sorted by name in alphanumerical order.  
The `from` and `size` arguments allow pagination. They are returned in the response if provided.


## Signature

```csharp
public string list(string index);
public string list(string index, QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``options`` | Kuzzleio::QueryOptions | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |
| `from` | long | Offset of the first result | `0` |
| `size` | long | Maximum number of returned results | `10` |

## Return

Returns a string containing a JSON representation of the API response.

## Usage

[snippet=list]
