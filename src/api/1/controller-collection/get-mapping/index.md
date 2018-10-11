---
layout: full.html.hbs
algolia: true
title: getMapping
---

# getMapping

{{{since "1.0.0"}}}

Return the mapping for the given `collection`.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/_mapping
Method: GET
```

### Other protocols


```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "getMapping"
}
```

---

## Arguments

* `collection`: data collection
* `index`: data index

---

## Response

Return an object with the following structure:

```
<index>
   |- mappings
         |- <collection>
               |- properties
                     |- mapping for field 1
                     |- mapping for field 2
                     |- ...
                     |- mapping for field n
```

Example:

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "getMapping",
  "requestId": "<unique request identifier>",
  "result": {
    "<index>": {
      "mappings": {
        "<collection>": {
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
    }
  }
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/errors/#notfounderror)
