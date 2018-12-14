---
layout: sdk.html.hbs
title: getSpecifications
description: Returns the validation specifications
---

# getSpecifications

Returns the validation specifications associated to the collection.

## Signature

```csharp
public string getSpecifications(string index, string collection);
public string getSpecifications(string index, string collection, QueryOptions options);
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

Return a string in JSON format representing the validation specifications.

## Usage

[snippet=get-specifications]
