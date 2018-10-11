---
layout: full.html.hbs
algolia: true
title: updateSelf
---

# updateSelf

{{{since "1.0.0"}}}

Update the current user object in Kuzzle.

The current user cannot update their security profile using this action. To change a user's security profile, use [security:updateUser]({{ site_base_path }}api/1/controller-security/update-user) instead.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/_updateSelf
Method: PUT  
Headers: Authorization: "Bearer <authentication token>"  
Body:
```

```js
{
    "foo": "bar",
    "fullname": "Walter Smith"
}
```

### Other protocols

```js
{
  "controller": "auth",
  "action": "updateSelf",
  "jwt": "<authentication token>",
  "body": {
    "foo": "bar",        
    "name": "Walter Smith"
  }
}
```

---

## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)

---

## Body properties

User properties that can be set or updated depend on the application hosted by Kuzzle. This document is free of limitations.

---

## Response

Return the following properties:

* `_id`: current user's [kuid]({{site_base_path}}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid)
* `_source`: additional (and optional) user properties

```js
{
  "status": 200,
  "error": null,
  "action": "updateSelf",
  "controller": "auth",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<kuid>",
    "_source": {
      "fullname": "Walter Smith"
    }
  }
}
```
