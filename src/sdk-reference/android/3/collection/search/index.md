---
layout: sdk.html.hbs
algolia: true
title: search
description: Collection:search
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


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an instance of [SearchResult]({{ site_base_path }}sdk-reference/search-result).

## Usage

[snippet=search-1]
