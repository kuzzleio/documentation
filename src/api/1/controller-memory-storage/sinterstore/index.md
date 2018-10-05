---
layout: full.html.hbs
algolia: true
title: sinterstore
---

# sinterstore

{{{since "1.0.0"}}}

Compute the intersection of the provided sets of unique values, and store the result in a destination key.

If the destination key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/sinterstore)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_sinterstore
Method: POST  
Body:
```

```js
{
  "destination": "<destination key>",
  "keys": ["key1", "key2", "..."]
}
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "sinterstore",
  "body": {
    "destination": "<destination key>",
    "keys": ["key1", "key2", "..."]
  }
}
```

---

## Body properties

* `destination`: new set to create, holding the intersection
* `keys`: source sets to intersect with the reference set

---

## Response

Return the number of values stored in the destination key.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sinterstore",
  "collection": null,
  "index": null,
  "result": 3
}
```
