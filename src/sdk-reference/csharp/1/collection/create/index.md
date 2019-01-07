---
layout: sdk.html.hbs
title: create
description: Create a new collection
---

# create

Creates a new [collection]({{ site_base_path }}guide/1/essentials/persisted) in Kuzzle via the persistence engine, in the provided `index`.
You can also provide an optional data mapping that allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html)).

This method will only update the mapping if the collection already exists.

## Signature

```csharp
public void create(string index, string collection);

public void create(string index, string collection, QueryOptions options);

public void create(string index, string collection, string body);

public void create(
    string index, 
    string collection, 
    string body, 
    QueryOptions options);

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>string</pre> | Index name    | 
| `collection` | <pre>string</pre> | Collection name    |
| `mapping` | <pre>string</pre> | JSON string representing the collection data mapping  |
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> |  Query options  |

### mapping

A JSON string representing the collection data mapping.

The mapping must have a root field `properties` containing the mapping definition:
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

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=create]
