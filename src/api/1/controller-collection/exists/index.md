---
layout: full.html.hbs
algolia: true
title: exists
---

# exists

{{{since "1.0.0"}}}

Check if a collection exists in Kuzzle.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/_exists
Method: GET
```

### Other protocols


```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "exists"
}
```

---

## Arguments

* `collection`: data collection
* `index`: data index

---

## Response

Return a boolean telling whether the provided data collection exists:

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "exists",
  "requestId": "<unique request identifier>",
  "result": true
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
