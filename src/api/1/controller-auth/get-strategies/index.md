---
layout: full.html.hbs
algolia: true
title: getStrategies
---

# getStrategies

{{{since "1.0.0"}}}

Gets the exhaustive list of authentication strategies registered in Kuzzle

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/strategies
Method: GET
```

### Other protocols

```js
{
  "controller": "auth",
  "action": "getStrategies"
}
```

---

## Response

The result is an array of available strategy names:

```javascript
{
  "status": 200,
  "error": null,
  "action": "getStrategies",
  "controller": "auth",
  "result": [
    "local",
    "facebook"
  ]
}
```
