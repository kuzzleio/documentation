---
layout: sdk.html.hbs
algolia: true
title: updateMapping
description: Update the collection mapping
order: 200
---

# updateMapping

Update the collection mapping.  
Mapping allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html)).

## Signature

```cpp
void updateMapping(const std::string& index, const std::string& collection, const std::string& mapping, kuzzleio::query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | const std::string& | Index name    | yes  |
| ``collection`` | const std::string& | Collection name    | yes  |
| ``mapping`` | const std::string* | Collection data mapping in JSON format  | yes  |
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

## Usage

[snippet=update-mapping]
