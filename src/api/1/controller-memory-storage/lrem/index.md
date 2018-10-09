---
layout: full.html.hbs
algolia: true
title: lrem
---


# lrem

{{{since "1.0.0"}}}

Remove the first occurences of an element from a list.

[[_Redis documentation_]](https://redis.io/commands/lrem)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_lrem/<_id>
Method: DELETE  
Body:
```

```js
{
  "count": 4,
  "value": "foobar"
}
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "lrem",
  "_id": "<key>",
  "body": {
    "count": 4,
    "value": "foobar"
  }
}
```

---

## Argument

* `_id`: list key identifier

---

## Body properties

* `count`: the number of the first found occurences to remove
* `value`: the value to remove

---

## Response

Return the number of removed elements.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "lrem",
  "collection": null,
  "index": null,
  "result": 2
}
```
