---
layout: full.html.hbs
algolia: true
title: getCurrentUser
---

# getCurrentUser

{{{since "1.0.0"}}}

Returns the profile object for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/users/_me
Method: GET  
Headers: Authorization: "Bearer <authentication token>"
```

### Other protocols

```js
{
  "controller": "auth",
  "action": "getCurrentUser",
  "jwt": "<authentication token>"
}
```

---

## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)

---

## Response

Result contains the following properties:

* `_id`: current user's [kuid]({{site_base_path}}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid)
* `_source`: additional (and optional) user properties
* `profile`: current user's [profile]({{site_base_path}}guide/1/essentials/security/#users-profiles-and-roles)
* `strategies`: available authentication strategies

```js
{
  "status": 200,
  "error": null,
  "controller": "auth",
  "action": "getCurrentUser",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<kuid>",
    "_source": {
      "name": {
        "first": "Steve",
        "last": "Wozniak"
      },
      "profile": {
        "_id": "admin",
        "roles": [
          "admin"
        ]
      }
    },
    "strategies": ["local"]
  }
}
```
