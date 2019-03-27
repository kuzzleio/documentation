---
layout: full.html.hbs
title: delete
---

# delete

{{{since "1.0.0"}}}

Deletes an [index]({{ site_base_path }}guide/1/essentials/persisted).

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>
Method: DELETE
```

### Other protocols


```js
{
  "index": "<index>",
  "controller": "index",
  "action": "delete"
}
```

---

## Arguments

* `index`: index name name to delete

---

## Response

Returns a confirmation that the index is being deleted:

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "controller": "index",
  "action": "delete",
  "requestId": "<unique request identifier>",
  "result": {
    "acknowledged": true
  }
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/essentials/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/essentials/errors/#notfounderror)
