---
layout: full.html.hbs
algolia: true
title: lindex
---

# lindex

{{{since "1.0.0"}}}

Return the element at the provided index in a list.

[[_Redis documentation_]](https://redis.io/commands/lindex)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_lindex/<_id>/<index>
Method: GET
```

### Other protocols


```js
{
  "controller": "ms",
  "action": "lindex",
  "_id": "<key>",
  "index": 3
}
```

---

## Argument

* `_id`: list key identifier
* `index`: index of the list. Lists are 0-indexed. If negative, it goes backward from the end of the list

---

## Response

Return the stored value at the provided list index.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "lindex",
  "collection": null,
  "index": null,
  "result": "value"
}
```
