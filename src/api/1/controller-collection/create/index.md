---
layout: full.html.hbs
title: create
---

# create

{{{since "1.0.0"}}}

Creates a new [collection]({{ site_base_path }}guide/1/essentials/persisted), in the provided `index`.  

{{{since "1.3.0"}}}

You can also provide an optional body with a data mapping that allow you to exploit the full capabilities of our persistent data storage layer.

This method will only update the mapping when the collection already exists.

{{{since "1.7.1"}}}

For each collection, you can set the policy against new fields that are not referenced in the collection mapping
The value of this configuration will change the way Elasticsearch manages the creation of new fields that are not declared in the collection mapping.
  - "true": Stores the document and updates the collection mapping with the inferred type
  - "false": Stores the document and does not update the collection mapping (fields are not indexed)
  - "strict": Rejects the document
  
See https://www.elastic.co/guide/en/elasticsearch/guide/current/dynamic-mapping.html

_meta => nepalea: { liaa: 'meh ry' }

dynamic

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

- [Common errors]({{ site_base_path }}api/1/essentials/errors/#common-errors)
- [PreconditionError]({{ site_base_path }}api/1/essentials/errors/#preconditionerror)
