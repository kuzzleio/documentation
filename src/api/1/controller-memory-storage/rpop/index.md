---
layout: full.html.hbs
algolia: true
title: rpop
---

# rpop

{{{since "1.0.0"}}}

Remove the last element of a list and returns it.

[[_Redis documentation_]](https://redis.io/commands/rpop)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_rpop/<_id>
Method: POST
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "rpop",
  "_id": "<key>"
}
```

---

## Argument

* `_id`: list key identifier

---

## Response

Return the removed element.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "rpop",
  "collection": null,
  "index": null,
  "result": "bar"
}
```
