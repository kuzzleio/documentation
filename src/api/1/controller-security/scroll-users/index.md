---
layout: full.html.hbs
algolia: true
title: scrollUsers
---

# scrollUsers

{{{since "1.0.0"}}}

This method moves a result set cursor forward, created by a [searchUsers]({{ site_base_path }}api/1/controller-security/search-users) query with the `scroll` argument provided.

The results that are returned from a `scrollUsers` request reflect the state of the index at the time that the initial search request was made, like a snapshot in time. Subsequent changes to documents (index, update or delete) will only affect later search requests.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/users/_scroll/<scrollId>[?scroll=<time to live>]
Method: GET
```

### Other protocols

```js
{
  "controller": "security",
  "action": "scrollUsers",
  "scrollId": "<scrollId>",
  "scroll": "<time to live>"
}
```

---

## Arguments

* `scrollId`: cursor unique identifier, obtained by either a searchUsers or a scrollUsers query

### Optional:

* `scroll`: refresh the cursor duration, using the [time to live](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/common-options.html#time-units) syntax.

---

## Response

Return a paginated search result set, with the following properties:

* `hits`: array of found profiles. Each document has the following properties:
  * `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier)
  * `_source`: user definition
* `scrollId`: identifier to the next page of result. Can be different than the previous one(s)
* `total`: total number of found users. Usually greater than the number of users in a result page

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "scrollUsers",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": {
    "scrollId": "<new scroll id>",
    "hits": [
      {
        "_id": "kuid1",
        "_source": { 
          "profileIds": [{"roleId": "default"}],
          "fullname": "John Doe"
        }
      },
      {
        "_id": "kuid2",
        "_source": {
          "profileIds": [{"roleId": "admin"}],
          "fullname": "Beardy McBeardface"
        }
      }
    ],
    "total": 42
  }
}
```
