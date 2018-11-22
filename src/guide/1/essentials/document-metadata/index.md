---
layout: full.html.hbs
algolia: true
title: Document Metadata
order: 450
algolia: true
---

# Document Metadata

Whenever a document gets created, updated or deleted, Kuzzle will add or update the document's metadata. This metadata provides information about the document's lifecycle.

---

## Overview

Metadata can be viewed in the document's `_meta` field and contains the following properties:

{{{since "1.3.0"}}}

Metadata can be viewed in the document's `_kuzzle_info` field and contains the following properties:  


* `author`: The [unique identifier]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuidd) of the user who created the document.
* `createdAt`: Timestamp of document creation (create or replace), in epoch-milliseconds format.
* `updatedAt`: Timestamp of last document update in epoch-milliseconds format, or `null` if no update has been made.
* `updater`: The [unique identifier]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid) of the user that updated the document, or `null` if the document has never been updated.
* `active`: The status of the document. `true` if the document is active and `false` if the document has been put in the trashcan.
* `deletedAt`: Timestamp of document deletion in epoch-milliseconds format, or `null` if the document has not been deleted.


Here is an example of a Kuzzle response, containing a document's `_id`, `_source` and `_meta` fields:

{{{deprecated "1.3.0"}}}

```json
{
  "_index": "myindex",
  "_type": "mycollection",
  "_id": "AVkDLAdCsT6qHI7MxLz4",
  "_score": 0.25811607,
  "_source": {
    "message": "Hello World!"
  },
  "_meta": {
    "author": "<kuid>",
    "createdAt": 1481816934209,
    "updatedAt": null,
    "updater": null,
    "active": true,
    "deletedAt": null
  }
}
```

{{{since "1.3.0"}}}

Here is an example of a Kuzzle response, containing a document's `_id` and `_source` fields:

```json
{
  "_index": "myindex",
  "_type": "mycollection",
  "_id": "AVkDLAdCsT6qHI7MxLz4",
  "_score": 0.25811607,
  "_source": {
    "message": "Hello World!",
    "_kuzzle_info": {
      "author": "<kuid>",
      "createdAt": 1481816934209,
      "updatedAt": null,
      "updater": null,
      "active": true,
      "deletedAt": null
    }
  }
}
```

---

## How metadata are physically stored

Documents metadata are managed by Kuzzle and cannot be changed using the API.  
Metadata are stored in the `_kuzzle_info` field of each document in Elasticsearch.

Elasticsearch might contain documents that don't have metadata. This can be the case for documents that were not inserted through Kuzzle. Such documents will automatically obtain metadata when they are updated through Kuzzle.

---

## Querying Metadata

Kuzzle allows search requests to access metadata directly. This means that you'll have to search in the `_kuzzle_info` document property.

For example, to query by a document's creation timestamp, we can use the following search filter:

```json
{
  "query": {
      "range": {
          "_kuzzle_info.createdAt": {
            "lte": 1481816930000
          }
      }
    }
}
```

---

## Documents Deletion

When a document gets deleted, Kuzzle first isolates it from other active documents by placing it in the `trashcan`.

Documents in the `trashcan` cannot be accessed, searched or counted, unless the `includeTrash` flag is set to `true` when invoking the API route.

---

## Garbage Collection

 Kuzzle will routinely search and permanently delete the oldest documents in the `trashcan`. This garbage collecting can be configured using the `services.garbageCollector` property in the  Kuzzle [configuration file]({{ site_base_path }}guide/1/essentials/configuration/). In general, garbage collection works as follows:

* When Kuzzle is started, it will check the `services.garbageCollector` property and wait the configured delay before running the garbage collection for the first time.
* If Kuzzle is in [overload]({{ site_base_path }}plugins/1/events/core-overload) the garbage collecting will be postponed until the load is reduced.
