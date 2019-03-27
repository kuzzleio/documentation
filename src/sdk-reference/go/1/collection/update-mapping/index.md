---
layout: sdk.html.hbs
title: updateMapping
description: Update the collection mapping
---

# updateMapping

Updates a collection mapping.

{{{since Kuzzle "1.7.1"}}}

You can define the collection [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) within the `_meta` root field.

## Signature

```go
UpdateMapping(index string, collection string, mapping json.RawMessage, options types.QueryOptions) error
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| ``mapping`` | json.RawMessage | Collection mapping in JSON format  | yes  |
| `options` | QueryOptions | Query options | no       |

### mapping

A `json.RawMessage` containing the representation of the collection mapping.  

```json
{
  "dynamic": "false",
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

More informations about collection mapping: {{ site_base_path}}guide/1/essentials/database-mappings

### options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return an error if something went wrong.

## Usage

[snippet=update-mapping]
