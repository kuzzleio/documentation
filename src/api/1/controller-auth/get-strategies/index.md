---
layout: full.html.hbs
algolia: true
title: getStrategies
---

# getStrategies

{{{since "1.0.0"}}}

Get all authentication strategies registered in Kuzzle

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

Result is an array of every available strategy names:

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
