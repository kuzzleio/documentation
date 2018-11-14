---
layout: full.html.hbs
algolia: true
title: keys
---


# keys

{{{since "1.0.0"}}}

Returns all keys matching the provided pattern.

[[_Redis documentation_]](https://redis.io/commands/keys)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_keys/<pattern>
Method: GET
```

### Other protocols


```js
{
  "controller": "ms",
  "action": "keys",
  "pattern": "foo*"
}
```

---

## Arguments

* `pattern`: match pattern

---

## Response

Returns the list of matching keys.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "keys",
  "collection": null,
  "index": null,
  "result": [
    "fookey1",
    "fookey2",
    "foo..."
  ]
}
```
