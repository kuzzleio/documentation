---
layout: full.html.hbs
algolia: true
title: updateCredentials
---

# updateCredentials

{{{since "1.0.0"}}}

Updates a user credentials for the specified authentication strategy.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/credentials/<strategy>/<_id>/_update
Method: PUT  
Body:
```

```js
{
  // example with the "local" authentication strategy
  "password": "<new password>"
}
```

### Other protocols

```js
{
  "controller": "security",
  "action": "updateCredentials",
  "strategy": "<strategy>",
  "_id": "<kuid>",
  "body": {
    // example with the "local" authentication strategy
    "password": "<new password>"
  }
}
```

---

## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier) 
* `strategy`: authentication strategy

---

## Body properties

The properties that can be sent to update a user credentials depend entirely of the authentication strategy. 

---

## Response

Returns the authentication strategy response.

### Example for the "local" authentication strategy:

```javascript
{
  "status": 200,
  "error": null,
  "action": "updateCredentials",
  "controller": "security",
  "_id": "<kuid>",
  "result": {
    "username": "MyUser",
    "kuid": "<kuid>"
  }
}
```
