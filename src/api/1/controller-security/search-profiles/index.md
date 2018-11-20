---
layout: full.html.hbs
algolia: true
title: searchProfiles
---


# searchProfiles

{{{since "1.0.0"}}}

Searches security profiles, optionally returning only those linked to the provided list of security roles.


## Arguments

### Optional:

* `from`: the offset from the first result you want to fetch.  Usually used with the `size` argument
* `scroll`: create a new forward-only result cursor. This option must be set with a [time duration](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/common-options.html#time-units), at the end of which the cursor is destroyed. If set, a cursor identifier named `scrollId` will be returned in the results. This cursor can then be moved forward using the [scrollProfiles]({{ site_base_path }}api/1/controller-security/scroll-profiles) API action
* `size`: the maximum number of profiles returned in one response page


## Response

Returns an object with the following properties:

* `hits`: array of object. Each object describes a found profile:
  * `_id`: profile identifier
  * `_source`: profile definition
* `total`: total number of profiles found. Depending on pagination options, this can be greater than the actual number of profiles in a single result page

```javascript
{
  "status": 200,                     
  "error": null,                     
  "result":
  {
    "hits": [
      {
        "_id": "firstProfileId",
        "_source": {
          // Full profile definition
        }
      },
      {
        "_id": "secondProfileId",
        "_source": {
          // Full profile definition
        }
      }
    ],
    "total": 2
  },
  "action": "searchProfiles",
  "controller": "security",
  "requestId": "<unique request identifier>"
}
```
