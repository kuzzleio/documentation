---
layout: full.html.hbs
algolia: true
title: getUser
---

# getUser

{{{since "1.0.0"}}}

Get a user.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/users/<_id>
Method: GET
```

### Other protocols

```js
{
  "controller": "security",
  "action": "getUser",
  "_id": "<kuid>"
}
```

---

## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier)

---

## Response

Return the user information:

* `_id`: user kuid
* `_source`: user description

```javascript
{
  "status": 200,
  "error": null,
  "controller": "security",
  "action": "getUser",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<kuid>",
    "_source": {
      "profileIds": ["<profileId>"]
    }
  }
}
```
