---
layout: full.html.hbs
algolia: true
title: scard
algolia: true
---

# scard

{{{since "1.0.0"}}}

Returns the number of members stored in a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/scard)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_scard/<_id>
Method: GET
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "scard",
  "_id": "<key>"
}
```

---

## Argument

* `_id`: set key identifier

---

## Response

Returns the set length.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "scard",
  "collection": null,
  "index": null,
  "result": 36
}
```
