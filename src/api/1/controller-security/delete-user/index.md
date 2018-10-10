---
layout: full.html.hbs
algolia: true
title: deleteUser
---


# deleteUser

{{{since "1.0.0"}}}

Deletes a user and all their associate credentials.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/users/<_id>[?refresh=wait_for]
Method: DELETE
```

### Other protocols

```js
{
  "controller": "security",
  "action": "deleteUser",
  "_id": "<kuid>"
}
```

---

## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier) to delete

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the user deletion is indexed

---

## Response

Returns the deleted kuid.


```javascript
{
  "status": 200,
  "error": null,
  "result": {
    "_id": "<kuid>",
  }
  "action": "deleteUser",
  "controller": "security",
  "requestId": "<unique request identifier>"
}
```
