---
layout: full.html.hbs
algolia: true
title: getset
---

# getset

{{{since "1.0.0"}}}

Set a new value for a key, and return its previously stored value.

[[_Redis documentation_]](https://redis.io/commands/getset)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_getset/<_id>
Method: POST  
Body:
```

```js
{
  "value": "new key value"
}
```

### Other protocols


```js
{
  "controller": "ms",
  "action": "getset",
  "_id": "<key>",
  "body": {
    "value": "new key value"
  }
}
```

---

## Arguments

* `_id`: key to get and set

---

## Body properties

* `value`: the new key value

---

## Response

Return the previously stored value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "getset",
  "collection": null,
  "index": null,
  "result": "key's previous value"
}
```
