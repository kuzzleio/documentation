---
layout: full.html.hbs
title: getCurrentUser
---

# getCurrentUser

Returns information about the currently logged in user.

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

The result contains the following properties:

* `_id`: current user's [kuid]({{site_base_path}}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid)
* `_source`: user information
  * `profileIds`: list of [profile]({{site_base_path}}guide/1/essentials/security/#users-profiles-and-roles) names associated to the user
  * any other properties: additional (and optional) user information
* `strategies`: available authentication strategies for that user

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
      "profileIds": ["customProfile1", "customProfile2"],
      "name": {
        "first": "Steve",
        "last": "Wozniak"
      }
    },
    "strategies": ["local"]
  }
}
```
