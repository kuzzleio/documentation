---
layout: full.html.hbs
algolia: true
title: randomkey
---

# randomkey

{{{since "1.0.0"}}}

Return a key identifier from the memory storage, at random.

[[_Redis documentation_]](https://redis.io/commands/randomkey)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_randomkey
Method: GET  
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "randomkey"
}
```

---

## Response

Return one of the database key, at random.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "randomkey",
  "collection": null,
  "index": null,
  "result": "qux"
}
```
