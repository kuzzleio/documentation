---
layout: sdk.html.hbs
algolia: true
title: getMapping
description: Return collection mapping
---


# getMapping

Returns the mapping for the given `collection`.

## Signature

```cpp
std::string getMapping(const std::string& index, const std::string& collection, kuzzleio::query_options *options=nullptr);
```

## Arguments

| Arguments    | Type    | Description | Required
|| ``index`` | const std::string& | Index name    | yes  |
| ``collection`` | const std::string& | Collection name    | yes  |
| ``options`` | kuzzleio::query_options* | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Return a string containing the collection mapping in JSON format.

## Usage

[snippet=get-mapping]
