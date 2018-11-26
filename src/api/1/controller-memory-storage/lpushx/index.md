---
layout: full.html.hbs
algolia: true
title: lpushx
algolia: true
---

# lpushx

{{{since "1.0.0"}}}

Prepends the specified value to a list, only if the key already exists and if it holds a list.

[[_Redis documentation_]](https://redis.io/commands/lpushx)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_lpushx/<_id>
Method: POST  
Body:
```

```js
{
  "value": "<value>"
}
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "lpushx",
  "_id": "<key>",
  "body": {
    "value": "<value>"
  }
}
```

---

## Argument

* `_id`: list key identifier

---

## Body properties

* `value`: value to push to the list

---

## Response

Returns the updated length of the list.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "lpushx",
  "collection": null,
  "index": null,
  "result": 2
}
```
