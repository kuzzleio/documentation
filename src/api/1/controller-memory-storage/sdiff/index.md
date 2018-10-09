---
layout: full.html.hbs
algolia: true
title: sdiff
---

# sdiff

{{{since "1.0.0"}}}

Return the difference between a reference set, and a list of other sets.

[[_Redis documentation_]](https://redis.io/commands/sdiff)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_sdiff/<_id>?keys=key1,key2,...
Method: GET
```

### Other protocols


```js
{
  "controller": "ms",
  "action": "sdiff",
  "_id": "<key>",
  "keys": ["key1", "key2", "..."]
}
```

---

## Argument

* `_id`: reference set key identifier
* `keys`: list of sets to compare to the reference set

---

## Response

Return an array of differences.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sdiff",
  "collection": null,
  "index": null,
  "result": [
    "diff value1",
    "diff value2",
    "..."
  ]
}
```
