---
layout: sdk.html
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

```go
UpdateMapping(index string, collection string, mapping json.RawMessage, options types.QueryOptions) error
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``mapping`` | json.RawMessage | Collection data mapping in JSON format  | yes  |
| `options` | QueryOptions | Query options. | no       |

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

## Return

Return an error if something went wrong.

## Usage

[snippet=update-mapping]
