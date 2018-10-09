---
layout: full.html.hbs
algolia: true
title: scrollProfiles
---

# scrollProfiles

{{{since "1.0.0"}}}

This method moves a result set cursor forward, created by a [searchProfiles]({{ site_base_path }}api/1/controller-security/search-profiles) query with the `scroll` argument provided.

The results that are returned from a `scrollProfiles` request reflect the state of the index at the time that the initial search request was made, like a snapshot in time. Subsequent changes to documents (index, update or delete) will only affect later search requests.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/profiles/_scroll/<scrollId>[?scroll=<time to live>]
Method: GET
```

### Other protocols

```js
{
  "controller": "security",
  "action": "scrollProfiles",
  "scrollId": "<scrollId>",
  "scroll": "<time to live>"
}
```

---

## Arguments

* `scrollId`: cursor unique identifier, obtained by either a searchProfiles or a scrollProfiles query

### Optional:

* `scroll`: refresh the cursor duration, using the [time to live](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/common-options.html#time-units) syntax.

---

## Response

Return a paginated search result set, with the following properties:

* `hits`: array of found profiles. Each document has the following properties:
  * `_id`: profile unique identifier
  * `_source`: profile definition
* `scrollId`: identifier to the next page of result. Can be different than the previous one(s)
* `total`: total number of found profiles. Usually greater than the number of profiles in a result page

```javascript
{
  "status": 200,
  "error": null,
  "action": "scrollProfiles",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": {
    "scrollId": "<new scroll id>",
    "hits": [
      {
        "_id": "profile1",
        "_source": {
          "policies": [
            // list of policies
          ]
        }
      },
      {
        "_id": "profile2",
        "_source": {
          "policies": [
            // list of policies
          ]
        }
      }
    ],
    "total": 42
  }
}
```
