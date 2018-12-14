---
layout: sdk.html.hbs
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the mapping for the given `collection`.

## Signature

```csharp
public string getMapping(string index, string collection);
public string getMapping(string index, string collection, QueryOptions options);
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

## Return

Return a string containing the collection mapping in JSON format.

## Usage

[snippet=get-mapping]
