---
layout: sdk.html.hbs
title: deleteSpecifications
description: Delete validation specifications for a collection
---

# deleteSpecifications

Delete the validation specifications associated with the collection.  

## Signature

```csharp
public void deleteSpecifications(string index, string collection);
public void deleteSpecifications(string index, string collection, QueryOptions options);
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

[snippet=delete-specifications]
