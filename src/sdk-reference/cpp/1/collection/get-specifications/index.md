---
layout: sdk.html.hbs
algolia: true
title: getSpecifications
description: Returns the validation specifications
---

# getSpecifications

Returns the validation specifications associated to the collection.

## Signature

```cpp
std::string getSpecifications(const std::string& index, const std::string& collection, kuzzleio::query_options *options=nullptr)
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

## Return

Return a string in JSON format representing the validation specifications.

## Usage

[snippet=get-specifications]
