---
layout: full.html.hbs
algolia: true
title: lpop
---

# lpop

{{{since "1.0.0"}}}

Remove and return the first element of a list.

[[_Redis documentation_]](https://redis.io/commands/lpop)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_lpop/<_id>
Method: POST
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "lpop",
  "_id": "<key>"
}
```

---

## Argument

* `_id`: list key identifier

---

## Response

Return the removed value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "lpop",
  "collection": null,
  "index": null,
  "result": "foo"
}
```
