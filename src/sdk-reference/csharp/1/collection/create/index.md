---
layout: sdk.html.hbs
title: create
description: Create a new collection
---

# create

Creates a new [collection]({{ site_base_path }}guide/essentials/persisted) in Kuzzle via the persistence engine, in the provided `index`.
You can also provide an optional data mapping that allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html)).

This method will only update the mapping if the collection already exists.

## Signature

```csharp
public void create(string index, string collection);
public void create(string index, string collection, QueryOptions options);
public void create(string index, string collection, string body);
public void create(string index, string collection, string body, QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| ``mapping`` | string | Collection data mapping in JSON format  | no  |
| ``options`` | Kuzzleio::QueryOptions |  A `Kuzzleio::QueryOptions` containing query options  | no  |

### **mapping**

An string containing the JSON representation of the collection data mapping.

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

You can see the full list of Elasticsearch mapping types [here](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html).

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=create]
