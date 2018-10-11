---
layout: full.html.hbs
algolia: true
title: persist
---

# persist

{{{since "1.0.0"}}}

Remove the expiration delay or timestamp from a key, making it persistent.

[[_Redis documentation_]](https://redis.io/commands/persist)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_persist/<_id>
Method: POST
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "persist",
  "_id": "<key>"
}
```

---

## Argument

* `_id`: key to persist

---

## Response

Return either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "persist",
  "collection": null,
  "index": null,
  "result": 1
}
```
