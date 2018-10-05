---
layout: full.html.hbs
algolia: true
title: hlen
---

# hlen

{{{since "1.0.0"}}}

Return the number of fields contained in a hash.

[[_Redis documentation_]](https://redis.io/commands/hlen)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_hlen/<_id>
Method: GET
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "hlen",
  "_id": "<key>"
}
```

---

## Arguments

* `_id`: hash key identifier

---

## Response

The number of fields stored in a hash.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hlen",
  "collection": null,
  "index": null,
  "result": 4
}
```
