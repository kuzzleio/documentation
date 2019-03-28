---
layout: full.html.hbs
title: exists
---

# exists

{{{since "1.0.0"}}}

Checks if the given index exists in Kuzzle.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/_exists
Method: GET
```

### Other protocols

```js
{
  "index": "<index>",
  "controller": "index",
  "action": "exists"
}
```

---

## Arguments

* `index`: index name name to test for existence

---

## Response

Returns a boolean telling whether the requested index exists.

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "controller": "index",
  "action": "exists",
  "requestId": "<unique request identifier>",
  "result": true
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/essentials/errors/#common-errors)
