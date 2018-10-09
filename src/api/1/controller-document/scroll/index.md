---
layout: full.html.hbs
algolia: true
title: scroll
---

# scroll

{{{since "1.0.0"}}}

This method moves a result set cursor forward, created by a [`search` query]({{ site_base_path }}api/1/controller-document/search/) with the `scroll` argument provided.

The results that are returned from a `scroll` request reflect the state of the index at the time that the initial search request was made, like a snapshot in time. Subsequent changes
to documents (index, update or delete) will only affect later search requests.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/_scroll/<scrollId>[?scroll=<time to live>]
Method: GET
```

### Other protocols

```js
{
  "controller": "document",
  "action": "scroll",
  "scrollId": "<scrollId>",
  "scroll": "<time to live>"
}
```

---

## Arguments

* `collection`: data collection
* `index`: data index
* `scrollId`: cursor unique identifier, obtained by either a search or a scroll query

### Optional:

* `scroll`: refresh the cursor duration, using the [time to live](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/common-options.html#time-units) syntax.

---

## Response

Return a paginated search result set, with the following properties:

* `hits`: array of found documents. Each document has the following properties:
  * `_id`: document unique identifier
  * `_score`: relevance score
  * `_source`: new document content
* `scrollId`: identifier to the next page of result. Can be different than the previous one(s)
* `total`: total number of found documents. Usually greater than the number of documents in a result page

```javascript
{
  "status": 200,
  "error": null,
  "action": "scroll",
  "controller": "document",
  "requestId": "<unique request identifier>",
  "result": {
    "scrollId": "<new scroll id>",
    "hits": [
      {
        "_id": "<document unique identifier>",
        "_score": 1,
        "_source": { 
          // document content
        }
      },
      {
        "_id": "<another document unique identifier>",
        "_score": 1,
        "_source": { 
          // document content
        }
      }
    ],
    "total": 42
  }
}
```
