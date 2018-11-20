---
layout: full.html.hbs
algolia: true
title: mGetProfiles
---


# mGetProfiles

{{{since "1.0.0"}}}

Gets multiple security profiles.


## Body properties

* `ids`: an array of profile identifiers to get

---

## Response

Returns a `hits` array of objects. Each object is a profile description, with the following properties:

* `_id`: profile unique identifier
* `_source`: profile description

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "mGetProfiles",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": {
    "hits": [
      {
        "_id": "profile1",
        "_source": {
          "policies": [{"roleId": "admin"}]
        }
      },
      {
        "_id": "profile2",
        "_source": {
          "policies": [{"roleId": "default"}]
        }
      }
    ]
  }
}
```
