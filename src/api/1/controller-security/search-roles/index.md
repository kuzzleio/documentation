---
layout: full.html.hbs
algolia: true
title: searchRoles
---


# searchRoles

{{{since "1.0.0"}}}

Searches security roles, optionally returning only those allowing access to the provided controllers.


## Arguments

### Optional:

* `from`: the offset from the first result you want to fetch.  Usually used with the `size` argument
* `size`: the maximum number of profiles returned in one response page


## Response

Returns an object with the following properties:

* `hits`: array of object. Each object describes a found role:
  * `_id`: role identifier
  * `_source`: role definition
* `total`: total number of roles found. Depending on pagination options, this can be greater than the actual number of roles in a single result page

```javascript
{
  "action": "searchRoles",
  "controller": "security",
  "error": null,
  "requestId": "<unique request identifier>",
  "result": 
  {
    "total": 1,
    "hits": [
      {
        "_id": "<roleId>",
        "_source": {
          "controllers": {
            "*": {
              "actions": {
                "*": true
              }
          }
        }
      }
    ]
  }
  "status": 200
}
```
