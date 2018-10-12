---
layout: full.html.hbs
algolia: true
title: create
---

# create

{{{since "1.0.0"}}}

Creates a new [data index]({{ site_base_path }}guide/1/essentials/persisted) in Kuzzle.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/_create
Method: POST
```

### Other protocols

```js
{
  "index": "<index>",
  "controller": "index",
  "action": "create"
}
```

---

## Arguments

* `index`: index name to create

---

## Response

Returns a confirmation that the index is being created:

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "action": "create",
  "controller": "index",
  "requestId": "<unique request identifier>",
  "result": {
    "acknowledged": true,
    "shards_acknowledged": true
  }
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
