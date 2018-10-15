---
layout: full.html.hbs
algolia: true
title: getUserRights
---

# getUserRights

{{{since "1.0.0"}}}

Gets the detailed rights granted to a user.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/users/<_id>/_rights
Method: GET
```

### Other protocols

```js
{
  "controller": "security",
  "action": "getUserRights",
  "_id": "<kuid>"
}
```

--- 

## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier)

---

## Response

Returns a `hits` array of objects. Each object is a security right granted or denied to the user:

* `controller`: impacted Kuzzle controller
* `action`: impacted controller action
* `index`: data index
* `collection`: data collection
* `value`: tell if access is `allowed` or `denied`. If closures have been configured on the detailed scope, the value is `conditional`.

```javascript
{
  "status": 200,
  "error": null,
  "result": {
    "hits": [
      {
        "controller": "auth",
        "action": "login",
        "value": "allowed"
      }, 
      { 
        "controller": "document",
        "action": "get",
        "index": "foo",
        "collection": "bar",
        "value": "allowed"
      },
      {
        "controller": "document",
        "action": "create",
        "index": "foo",
        "collection": "bar",
        "value": "denied"
      }
    ]
  }
}
```
