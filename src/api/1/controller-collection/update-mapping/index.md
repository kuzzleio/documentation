---
layout: full.html.hbs
title: updateMapping
---

# updateMapping

{{{since "1.0.0"}}}

Updates a collection mapping.

{{{since "1.7.1"}}}

You can define the collection [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) within the `_meta` root field.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/_mapping
Method: PUT  
Body:
```

```js
{
  "dynamic": "false",
  "_meta": {
    "field": "value"
  },
  "properties": {
    "field1": { 
      "type": "integer"
    },
    "field2": {
      "type": "keyword"
    },
    "field3": {
      "type":   "date",
      "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
    }
  }
}
```

### Other protocols

```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "updateMapping",
  "body": {
    "dynamic": "false",
    "_meta": {
      "field": "value"
    },
    "properties": {
      "field1": { 
        "type": "integer"
      },
      "field2": {
        "type": "keyword"
      },
      "field3": {
        "type":   "date",
        "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
      }
    }
  }
}
```

---

## Arguments

* `collection`: name of the collection to create
* `index`: index that will host the new collection

---

## Body properties

### Optional:

* `dynamic`: [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) for new fields
* `_meta`: [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) stored next to the collection
* `properties`: object describing the data mapping to associate to the new collection, using [Elasticsearch types definitions format]({{ site_base_path}}guide/1/essentials/database-mappings/#properties-types-definition)

---

## Response

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "action": "updateMapping",
  "controller": "collection",
  "requestId": "<unique request identifier>",
  "result": {}
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/essentials/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/essentials/errors/#notfounderror)
