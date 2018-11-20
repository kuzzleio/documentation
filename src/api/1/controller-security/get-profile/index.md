---
layout: full.html.hbs
algolia: true
title: getProfile
---



# getProfile

{{{since "1.0.0"}}}

Gets a security profile.


## Arguments

* `_id`: profile identifier

---

## Response

Returns the queried profile information:

* `_id`: profile identifier
* `_source`: profile content

```javascript
{
  "status": 200,                     
  "error": null,                     
  "result": {
    "_id": "<profileId>",
    "_source": {
      "policies": [
        {
          "roleId": "<roleId>"
        },
        {
          "roleId": "<roleId>",
          "restrictedTo": [
            {
              "index": "<index>"
            },
            {
              "index": "<index>",
              "collections": [
                "<coll1>",
                "<coll2>"
              ]
            }
          ]
        }
      ]
    },
    "action": "getProfile",
    "controller": "security",
    "requestId": "<unique request identifier>"
  }
}
```
