---
layout: sdk.html.hbs
title: updateMapping
description: Update the collection mapping
---

# updateMapping

Updates a collection mapping.

{{{since "Kuzzle 1.7.1"}}}

You can define the collection [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) within the `_meta` root field.

## Signature

```cpp
void updateMapping(
    const std::string& index, 
    const std::string& collection, 
    const std::string& mapping);

void updateMapping(
    const std::string& index, 
    const std::string& collection, 
    const std::string& mapping, 
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>const std::string&</pre> | Index name    | 
| `collection` | <pre>const std::string&</pre> | Collection name    |
| `mapping` | <pre>const std::string*</pre> | JSON string representing the collection mapping |
| `options` | <pre>kuzzleio::query_options\*</pre> |  Query options  |

### mapping

A JSON string representing the collection mapping.

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

More information about collection mapping: {{ site_base_path}}guide/1/essentials/database-mappings.


### options

Additional query options

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Usage

[snippet=update-mapping]
