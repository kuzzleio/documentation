---
layout: full.html.hbs
algolia: true
title: Document Metadata
order: 450
---


# Document Metadata

Whenever a document gets created, updated or deleted, Kuzzle will add or update the document's metadata. This metadata provides information about the document's lifecycle.


## How metadata are physically stored

Documents metadata are managed by Kuzzle and cannot be changed using the API.  
Metadata are stored in the `_kuzzle_info` field of each document in Elasticsearch.

Elasticsearch might contain documents that don't have metadata. This can be the case for documents that were not inserted through Kuzzle. Such documents will automatically obtain metadata when they are updated through Kuzzle.


## Documents Deletion

When a document gets deleted, Kuzzle first isolates it from other active documents by placing it in the `trashcan`.

Documents in the `trashcan` cannot be accessed, searched or counted, unless the `includeTrash` flag is set to `true` when invoking the API route.

---

## Garbage Collection

 Kuzzle will routinely search and permanently delete the oldest documents in the `trashcan`. This garbage collecting can be configured using the `services.garbageCollector` property in the  Kuzzle [configuration file]({{ site_base_path }}guide/1/essentials/configuration/). In general, garbage collection works as follows:

* When Kuzzle is started, it will check the `services.garbageCollector` property and wait the configured delay before running the garbage collection for the first time.
* If Kuzzle is in [overload]({{ site_base_path }}plugins/1/events/core-overload) the garbage collecting will be postponed until the load is reduced.
