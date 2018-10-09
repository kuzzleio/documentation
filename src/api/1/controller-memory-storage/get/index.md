---
layout: full.html.hbs
algolia: true
title: get
---

# get

{{{since "1.0.0"}}}

Return the value of a key, or `null` if the key doesn't exist.

[[_Redis documentation_]](https://redis.io/commands/get)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/<_id>
Method: GET
```

### Other protocols


```js
{
  "controller": "ms",
  "action": "get",
  "_id": "<key>"
}
```

---

## Arguments

* `_id`: key to fetch

---

## Response

Return the value stored at the provided key `key`.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "get",
  "collection": null,
  "index": null,
  "result": "value"
}
```
