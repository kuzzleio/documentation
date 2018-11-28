---
layout: full.html.hbs
title: hmget
---

# hmget

{{{since "1.0.0"}}}

Returns the values of the specified hash's fields.

[[_Redis documentation_]](https://redis.io/commands/hmget)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_hmget/<_id>?fields=<field1,field2,...>
Method: GET
```

### Other protocols


```js
{
  "controller": "ms",
  "action": "hmget",
  "_id": "<key>",
  "fields": ["field1", "field2", "..."]
}
```

---

## Arguments

* `_id`: hash key identifier
* `fields`: the list of fields to fetch

---

## Response

Returns the list of requested field values, in the same order than in the query.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hmget",
  "collection": null,
  "index": null,
  "result": [
    "field1's value",
    "field2's value",
    "...'s value"
  ]
}
```
