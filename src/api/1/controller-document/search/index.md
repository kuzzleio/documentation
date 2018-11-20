---
layout: full.html.hbs
algolia: true
title: search
---


# search

{{{since "1.0.0"}}}

Searches documents.

There is a limit to how many documents can be returned by a single search query.  
That limit is by default set at 10000 documents, and you can't get over it even with the `from` and `size` pagination options.

To handle larger result sets, you have to either create a cursor by providing a value to the `scroll` option or, if you sort the results, by using the Elasticsearch [search_after](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-search-after.html) command.


## Arguments

* `collection`: data collection
* `index`: data index

### Optional:

* `from`: paginates search results by defining the offset from the first result you want to fetch. Usually used with the `size` argument
* `includeTrash`: if true, include documents in the [trashcan]({{ site_base_path }}guide/essentials/document-metadata/)
* `scroll`: creates a forward-only result cursor. This option must be set with a [time duration](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/common-options.html#time-units), at the end of which the cursor is destroyed. If set, a cursor identifier named `scrollId` is returned in the results. This cursor can then be moved forward using the [scroll]({{ site_base_path }}api/1/controller-document/scroll) API action
* `size`: set the maximum number of documents returned per result page


## Response

Returns a paginated search result set, with the following properties:

* `aggregations`: provides aggregation information. Present only if an `aggregations` object has been provided in the search body
* `hits`: array of found documents. Each document has the following properties:
  * `_id`: document unique identifier
  * `_score`: relevance score
  * `_source`: new document content
* `scrollId`: identifier to the next page of result. Present only if the `scroll` argument has been set
* `total`: total number of found documents. Can be greater than the number of documents in a result page, meaning that other matches than the one retrieved are available

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "action": "search",
  "controller": "document",
  "requestId": "<unique request identifier>",
  "result": {
    "scrollId": "<scroll id>",
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
    // Present only if aggregation parameters have been set
    "aggregations": {
      "aggs_name": {

      }
    },
    "total": 42
  }
}
```
