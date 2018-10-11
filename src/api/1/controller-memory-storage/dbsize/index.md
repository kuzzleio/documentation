---
layout: full.html.hbs
algolia: true
title: dbsize
---

# dbsize

{{{since "1.0.0"}}}

Return the number of keys in the application database.

[[_Redis documentation_]](https://redis.io/commands/dbsize)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_dbsize
Method: GET  
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "dbsize",
}
```

---

## Response

Return the number of found keys.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "dbsize",
  "collection": null,
  "index": null,
  "result": 42
}
```
