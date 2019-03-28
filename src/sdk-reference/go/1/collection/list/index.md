---
layout: sdk.html.hbs
title: list
description: Returns the collection list of an index
---

# list

Returns the complete list of realtime and stored collections in requested index sorted by name in alphanumerical order.  
The `from` and `size` arguments allow pagination. They are returned in the response if provided.


## Signature

```go
List(index string, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``options`` | types.QueryOptions | An object containing query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |
| `from` | int | Offset of the first result | `0` |
| `size` | int | Maximum number of returned results | `10` |

## Return

Return a json representation of the API return containing the collection list and an error is something was wrong.

## Usage

[snippet=list]
