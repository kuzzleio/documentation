---
layout: full.html.hbs
algolia: true
title: updateUser
algolia: true
---

# updateUser

{{{since "1.0.0"}}}

Updates a user definition.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/users/<_id>/_update[?refresh=wait_for]
Method: PUT  
Body:
```

```js
{
  "fullname": "Walter Smith"
}
```

### Other protocols

```js
{
  "controller": "security",
  "action": "updateUser",
  "_id": "<kuid>",
  "body": {
    "fullname": "Walter Smith"
  }
}
```
---

## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier)

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the user changes are indexed


---

## Response

Returns the update user kuid and version number.

```javascript
{
  "status": 200,
  "error": null,
  "action": "updateUser",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<kuid>",
    "_version": 2
  }
}
```
