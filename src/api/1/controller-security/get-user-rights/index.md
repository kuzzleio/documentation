---
layout: full.html.hbs
algolia: true
title: getUserRights
---


# getUserRights

{{{since "1.0.0"}}}

Gets the detailed rights granted to a user.


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
