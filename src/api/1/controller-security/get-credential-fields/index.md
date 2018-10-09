---
layout: full.html.hbs
algolia: true
title: getCredentialFields
---


# getCredentialFields

{{{since "1.0.0"}}}

Retrieve the list of accepted field names by the specified authenticaiton strategy.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/credentials/<strategy>/_fields
Method: GET  
```

### Other protocols

```js
{
  "controller": "security",
  "action": "getCredentialFields",
  "strategy": "<strategy>"
}
```

---

## Arguments

* `strategy`: authentication strategy

---

## Response

Return an array of accepted field names.

### Example with the "local" authentication strategy:

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "getCredentialFields",
  "controller": "security",
  "result": ["username", "password"]
}
```
