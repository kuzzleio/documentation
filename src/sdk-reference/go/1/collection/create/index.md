---
layout: sdk.html.hbs
title: create
description: Create a new collection
---

# create

Creates a new [collection]({{ site_base_path }}guide/1/essentials/persisted) in Kuzzle via the persistence engine, in the provided index.

{{{since "Kuzzle 1.3.0"}}}

You can also provide an optional body with a [collection mapping]({{ site_base_path }}guide/1/essentials/database-mappings) that allow you to exploit the full capabilities of our persistent data storage layer.

This method will only update the mapping when the collection already exists.

{{{since "Kuzzle 1.7.1"}}}

You can define the collection [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) within the `_meta` root field.

This method will only update the mapping if the collection already exists.

## Signature

```go
Create(index string, collection string, mapping json.RawMessage, options types.QueryOptions) error
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| ``mapping`` | json.RawMessage | Collection mapping in JSON format  | no  |
| `options` | QueryOptions | Query options | no       |

### mapping

A `json.RawMessage` containing the representation of the collection mapping.  

```json
{
  "dynamic": "[true|false|strict]",
  "_meta": {
    "field": "value"
  },
  "properties": {
    "field1": { "type": "text" },
    "field2": {
      "properties": {
        "nestedField": { "type": "keyword"}
      }
    }
  }
}
```

More informations about collection mapping [here]({{ site_base_path}}guide/1/essentials/database-mappings)

### options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return an error or `nil` if collection successfully created.

## Usage

[snippet=create]
