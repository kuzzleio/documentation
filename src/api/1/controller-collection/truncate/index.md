---
layout: full.html.hbs
algolia: true
title: truncate
---

# truncate

{{{since "1.0.0"}}}

Empties a collection by removing all its documents, while keeping any associated mapping.  

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/_truncate
Method: DELETE
```

### Other protocols


```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "truncate"
}
```

---

## Arguments

* `collection`: data collection
* `index`: data index

---

## Response

Return a confirmation that the collection is being emptied:

```javascript
{
  "status": 200,
  "error": null,
  "action": "truncate",
  "controller": "collection",
  "index": "<index>",
  "collection": "<collection>",
  "requestId": "<unique request identifier>",
  "result": {
    "acknowledged": true,
  }
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/errors/#notfounderror)
