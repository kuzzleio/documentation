---
layout: sdk.html.hbs
title: create
description: Create a new collection
---

# create

Creates a new [collection]({{ site_base_path }}guide/1/essentials/persisted) in Kuzzle via the persistence engine, in the provided index.

{{{since Kuzzle "1.3.0"}}}

You can also provide an optional body with a [collection mapping]({{ site_base_path }}guide/1/essentials/database-mappings) that allow you to exploit the full capabilities of our persistent data storage layer.

This method will only update the mapping when the collection already exists.

{{{since Kuzzle "1.7.1"}}}

You can define the collection [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) within the `_meta` root field.

## Signature

```cpp
void create(const std::string& index, const std::string& collection);

void create(
    const std::string& index, 
    const std::string& collection, 
    const kuzzleio::query_options& options);

void create(
    const std::string& index, 
    const std::string& collection, 
    const std::string& mapping);

void create(
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
| `mapping` | <pre>const std::string*</pre> | JSON string representing the collection mapping  |
| `options` | <pre>kuzzleio::query_options\*</pre> |  Query options  |

### mapping

A JSON string representing the collection mapping.

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

More informations about collection mapping: {{ site_base_path}}guide/1/essentials/database-mappings.

### options

Additional query options

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=create]
