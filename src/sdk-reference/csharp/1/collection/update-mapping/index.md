---
layout: sdk.html.hbs
title: updateMapping
description: Update the collection mapping
---

# updateMapping

Update the collection mapping.
Mapping allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping.html)).

## Signature

```csharp
public void updateMapping(string index, string collection, string mapping);

public void updateMapping(
    string index, 
    string collection, 
    string mapping, 
    query_options options);

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>string</pre> | Index name    | 
| `collection` | <pre>string</pre> | Collection name    |
| `mapping` | <pre>string</pre> | JSON string representing the collection data mapping |
| `options` | <pre>Kuzzleio::QueryOptions</pre> |  Query options  |

### mapping

A JSON string representing the collection data mapping.

The mapping must have a root field `properties` that contain the mapping definition:
```json
{
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

You can see the full list of Elasticsearch mapping types [here](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping.html).

### options

Additional query options

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Usage

[snippet=update-mapping]
