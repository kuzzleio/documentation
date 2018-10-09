---
layout: full.html.hbs
algolia: true
title: mGetRoles
---

# mGetRoles

{{{since "1.0.0"}}}

Get multiple security roles.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/roles/_mGet
Method: POST  
Body:
```

```js
{
  "ids": ["role1", "role2"]
}
```

### Other protocols

```js
{
  "controller": "security",
  "action": "mGetRoles",
  "body": {
    "ids": ["role1", "role2"]
  }
}
```
---

## Body properties

* `ids`: an array of role identifiers to get

---

## Response

Return a `hits` array of objects. Each object is a role description, with the following properties:

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
