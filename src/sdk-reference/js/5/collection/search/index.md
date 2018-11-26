---
layout: sdk.html.hbs
algolia: true
title: search
description: Collection:search
algolia: true
---
  

# search
Executes a search on the data collection.

<aside class="notice">
There is a small delay between the time a document is created and its availability in our search layer (usually a couple of seconds). That means that a document that was just created might not be returned by this function at first.
</aside>

## Processing large data sets

When processing a large number of documents (i.e. more than 1000), using `search` is not always the best option.

Pagination of results can be done by using the from and size but the cost becomes prohibitive when deep pagination is reached. In fact, Elasticsearch, Kuzzle's embedded database, limits results to 10,000 records by default.

Instead, the recommended way to process a large number of documents is to use [`Collection.scroll`]({{ site_base_path }}sdk-reference/collection/scroll/) or, easier, [`SearchResult.fetchNext`]({{ site_base_path }}sdk-reference/search-result/fetch-next).

See [`SearchResult.fetchNext`]({{ site_base_path }}sdk-reference/search-result/fetch-next/#how-to-process-every-document-of-a-collection) for an example of how to process every document of a collection.

---

## search(body, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``body`` | JSON object | Search request body, using [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/search-request-body.html) format. <br>If given an empty object, matches all documents in the collection |
| ``options`` | JSON object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``from`` | number | Provide the starting offset of the request (used to paginate results) | ``0`` |
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``scroll`` | string | Start a scroll session, with a time to live equals to this parameter's value following the [Elastisearch time format](https://www.elastic.co/guide/en/elasticsearch/reference/5.0/common-options.html#time-units) | ``undefined`` |
| ``size`` | number | Provide the maximum number of results of the request (used to paginate results) | ``10`` |

<aside class="notice">
  To get more information about scroll sessions, please refer to the <a href="{{ site_base_path }}api-documentation/controller-document/search">API reference documentation</a>.
</aside>

---

## Callback Response

Returns an instance of [SearchResult]({{ site_base_path }}sdk-reference/search-result).

## Usage

[snippet=search-1]
