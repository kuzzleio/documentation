---
layout: full.html.hbs
algolia: true
title: hgetall
algolia: true
---

# hgetall

{{{since "1.0.0"}}}

Returns all fields and values of a hash.

[[_Redis documentation_]](https://redis.io/commands/hgetall)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_hgetall/<_id>
Method: GET
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "hgetall",
  "_id": "<key>"
}
```

---

## Arguments

* `_id`: hash key identifier

---

## Response

Returns the requested hash content as a `field: value` object.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hgetall",
  "collection": null,
  "index": null,
  "result": {
    "field1": "value",
    "field2": "value",
    "...": "..."
  }
}
```
