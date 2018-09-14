---
layout: sdk.html
algolia: true
title: getSpecifications
description: Returns the validation specifications
order: 200
---

# getSpecifications

Returns the validation specifications associated to the collection.

## Signature

```go
GetSpecifications(index string, collection string, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| ``options`` | types.QueryOptions | Query options    | no  |

###### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Return a json representation of the specifications and an error is something was wrong.

## Usage

[snippet=get-specifications]
