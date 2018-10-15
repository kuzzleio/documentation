---
layout: full.html.hbs
algolia: true
title: create
---

# create

{{{since "1.0.0"}}}

Creates a new [collection]({{ site_base_path }}guide/1/essentials/persisted) in Kuzzle via the persistence engine, in the provided `index`.  

{{{since "1.3.0"}}}

You can also provide an optional body with a data mapping that allow you to exploit the full capabilities of our persistent data storage layer.

This method will only update the mapping if the collection already exists.

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

* `collection`: data collection to create
* `index`: data index that will host the new data collection

---

## Body properties

### Optional:

* `properties`: object describing the data mapping to associate to the new collection, using [Elasticsearch mapping format](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping.html).

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

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [PreconditionError]({{ site_base_path }}api/1/errors/#preconditionerror)
