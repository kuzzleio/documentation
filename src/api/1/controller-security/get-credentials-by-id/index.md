---
layout: full.html.hbs
algolia: true
title: getCredentialsById
---


# getCredentialsById

{{{since "1.0.0"}}}

Gets credential information for the user identified by the strategy's unique user identifier `userId`.

The returned `result` object will vary depending on the strategy (see the [getById plugin function]({{ site_base_path }}plugins/1/essentials/strategies/#optional-getbyid-default)), and it can be empty.

**Note:** the user identifier to use depends on the specified strategy. If you wish to get credential information using a [kuid]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid) identifier, use the [getCredentials]({{ site_base_path }}api/1/controller-security/get-credentials/) API route instead.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/credentials/<strategy>/<_id>/_byId
Method: GET  
```

### Other protocols

```js
{
  "controller": "security",
  "action": "getCredentialsById",
  "strategy": "<strategy>",
  "_id": "<userId>"
}
```

---

## Arguments

* `_id`: user credential identifier (this is NOT the kuid)
* `strategy`: authentication strategy

---

## Response

Returns credentials information (depend on the authentication strategy).

### Example with the "local" authentication strategy:

```javascript
{
  "status": 200,
  "error": null,
  "action": "getCredentialsById",
  "controller": "security",
  "result": {
    "username": "<userId>",
    "kuid": "<kuid>"
  }
}
```
