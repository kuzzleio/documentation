---
layout: sdk.html.hbs
algolia: true
title: create
description: Create a new collection
order: 200
---

# create

Creates a new [collection]({{ site_base_path }}guide/essentials/persisted) in Kuzzle via the persistence engine, in the provided `index`.  
You can also provide an optional data mapping that allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html)).  

This method will only update the mapping if the collection already exists.

## Signature

```cpp
void create(const std::string& index, const std::string& collection, const std::string* mapping=nullptr, kuzzleio::query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | const std::string& | Index name    | yes  |
| ``collection`` | const std::string& | Collection name    | yes  |
| ``mapping`` | const std::string* | Collection data mapping in JSON format  | no  |
| ``options`` | kuzzleio::query_options* |  A pointer to a `query_options` containing query options  | no  |

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
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=create]
