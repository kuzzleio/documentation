---
layout: full.html.hbs
algolia: true
title: type
algolia: true
---

# type

{{{since "1.0.0"}}}

Returns the type of the value held by a key.

[[_Redis documentation_]](https://redis.io/commands/type)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_type/<_id>
Method: GET
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "type",
  "_id": "<key>"
}
```

---

## Arguments

* `_id`: key identifier

---

## Response

Returns one of the following: `hash`, `list`, `string`, `set`, `zset`.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "type",
  "collection": null,
  "index": null,
  "result": "list"
}
```
