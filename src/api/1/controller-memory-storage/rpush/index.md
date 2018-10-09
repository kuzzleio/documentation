---
layout: full.html.hbs
algolia: true
title: rpush
---

# rpush

{{{since "1.0.0"}}}

Append values at the end of a list. 

If the destination list does not exist, it is created holding an empty list before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/rpush)


---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_rpush/<_id>
Method: POST  
Body:
```

```js
{
  "values": ["value1", "value2", "..."]
}
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "rpush",
  "_id": "<key>",
  "body": {
    "values": ["value1", "value2", "..."]
  }
}
```

---

## Argument

* `_id`: list key identifier

---

## Body properties

* `values`: an array of values to push to the list

---

## Response

Return the updated list length.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "rpush",
  "collection": null,
  "index": null,
  "result": 12
}
```
