---
layout: full.html.hbs
title: create
---

# create

Creates a new [collection]({{ site_base_path }}guide/1/essentials/persisted), in the provided `index`.  

{{{since "1.3.0"}}}

You can also provide an optional body with a [collection mapping]({{ site_base_path }}guide/1/essentials/database-mappings) allowing you to exploit the full capabilities of our persistent data storage layer.

This method will only update the mapping when the collection already exists.

{{{since "1.7.1"}}}

You can define the collection [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) within the `_meta` root field.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>
Method: PUT  
Body:  
```

```js
{
  "dynamic": "[false|true|strict]",
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
  "action": "create",
  "body": {
    "dynamic": "[false|true|strict]",
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

* `dynamic`: [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) for new fields. Allowed values: `true` (default), `false`, `strict`
* `_meta`: [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) stored next to the collection
* `properties`: object describing the data mapping to associate to the new collection, using [Elasticsearch types definitions format]({{ site_base_path}}guide/1/essentials/database-mappings/#properties-types-definition)

---

## Response

Returns a confirmation that the collection is being created:

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "create",
  "requestId": "<unique request identifier>",
  "result": {
    "acknowledged": true
  }
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/essentials/errors/#common-errors)
- [PreconditionError]({{ site_base_path }}api/1/essentials/errors/#preconditionerror)
