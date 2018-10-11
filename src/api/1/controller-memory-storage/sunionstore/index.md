---
layout: full.html.hbs
algolia: true
title: sunionstore
---

# sunionstore

{{{since "1.0.0"}}}

Compute the union of multiple sets of unique values and store it in a new set.

If the destination key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/sunionstore)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_sunionstore
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
  "action": "sunionstore",
  "body": {
    "destination": "<destination key>",
    "keys": ["key1", "key2", "..."]
  }
}
```

---

## Body properties

* `destination`: destination for the union result
* `keys`: array of set identifiers

---

## Response

Return the number of members stored in the destination set.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sunionstore",
  "collection": null,
  "index": null,
  "result": 31
}
```
