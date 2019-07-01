---
code: true
type: page
title: create
description: Create a new collection
---

# create

Creates a new [collection](/core/1/guides/essentials/store-access-data/) in the provided `index`.
You can also provide an optional data mapping that allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html)).

This method will only update the mapping if the collection already exists.

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

| Arguments    | Type                                 | Description                                          |
| ------------ | ------------------------------------ | ---------------------------------------------------- |
| `index`      | <pre>const std::string&</pre>        | Index name                                           |
| `collection` | <pre>const std::string&</pre>        | Collection name                                      |
| `mapping`    | <pre>const std::string\*</pre>       | JSON string representing the collection data mapping |
| `options`    | <pre>kuzzleio::query_options\*</pre> | Query options                                        |

### mapping

A JSON string representing the collection data mapping.

The mapping must have a root field `properties` that contain the mapping definition:

```json
{
  "properties": {
    "field1": { "type": "text" },
    "field2": {
      "properties": {
        "nestedField": { "type": "keyword" }
      }
    }
  }
}
```

More informations about database mappings [here](/core/1/guides/essentials/database-mappings).

### options

Additional query options

| Property   | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/create.cpp
