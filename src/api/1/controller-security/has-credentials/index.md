---
layout: full.html.hbs
algolia: true
title: hasCredentials
algolia: true
---

# hasCredentials

{{{since "1.0.0"}}}

Checks if a user has credentials registered for the specified authentication strategy.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/credentials/<strategy>/<_id>/_exists
Method: GET  
```

### Other protocols

```js
{
  "controller": "security",
  "action": "hasCredentials",
  "strategy": "<strategy>",
  "_id": "<kuid>"
}
```

---

## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier) 
* `strategy`: authentication strategy

---

## Response

Returns a boolean telling whether the user can log in using the provided authentication strategy.

```javascript
{
  "status": 200,
  "error": null,
  "action": "hasCredentials",
  "controller": "security",
  "_id": "<kuid>",
  "result": true
}
```
