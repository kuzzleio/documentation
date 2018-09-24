---
layout: sdk.html
algolia: true
title: deleteSpecifications
description: Delete validation specifications for a collection
order: 200
---

# deleteSpecifications

Delete the validation specifications associated with the collection.  

## Signature

```go
DeleteSpecifications(index string, collection string, options types.QueryOptions) error
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| ``options`` | types.QueryOptions | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return an error or `nil` if collection successfully created.

## Usage

[snippet=delete-specifications]
