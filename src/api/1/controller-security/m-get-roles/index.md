---
layout: full.html.hbs
algolia: true
title: mGetRoles
---


# mGetRoles

{{{since "1.0.0"}}}

Gets multiple security roles.


## Body properties

* `ids`: an array of role identifiers to get

---

## Response

Returns a `hits` array of objects. Each object is a role description, with the following properties:

* `_id`: role unique identifier
* `_source`: role description

```javascript
{
  "status": 200,
  "action": "mGetRoles",
  "controller": "security",
  "error": null,
  "requestId": "<unique request identifier>",
  "result": {
    "hits": [
      {
        "_id": "role1",
        "_source": {
          "controllers": {
            "*": {
              "actions": true
            }
          }
        }
      },
      {
        "_id": "role2",
        "_source": {
          "controllers": {
            "document": {
              "actions": {
                "get": true,
                "search": true
              }
            }
          }
        }
      }
    ]
  }
}
```
