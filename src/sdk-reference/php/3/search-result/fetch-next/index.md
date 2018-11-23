---
layout: sdk.html.hbs
algolia: true
title: fetchNext
description: SearchResult:fetchNext
algolia: true
---
  

# fetchNext
Fetches the next SearchResult, by triggering a new search/scroll request depending on the options and filters of the SearchResult.

If the previous request was a search or a scroll action which provided a `scroll` argument,
`fetchNext` will use the `scrollId` retrieved from the current result to make a new scroll request.

If the previous request was a search action which provided `size` argument and `sort` filtering,
`fetchNext` will use Elasticsearch's [`search_after`](https://www.elastic.co/guide/en/elasticsearch/reference/master/search-request-search-after.html) mechanism, which can efficiently search through a large volume of documents, bypassing internal hard limits<sup>\[1\]</sup>,
but at the cost of reflecting the latest changes of the index, as opposed to using scroll.

If the previous request was a search action which provided `from` and `size` arguments,
`fetchNext` will add `size` to `from` and make a new search request.

---

## How to process every document of a collection
The safest way to process all documents in a collection is to fetch them as a batch in order to avoid memory exhaustion and possibly hitting some hard limits<sup>\[1\]</sup> on the database layer.

<aside class="warning">Make sure your first search request includes <code>size</code> and <code>scroll</code> parameters</aside>

<aside class="notice"><sup>\[1\]</sup> Elasticsearch limits the number of documents inside a single page to [10,000 by default](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/index-modules.html#dynamic-index-settings).</aside>

## Usage

[snippet=fetch-next-1]

[snippet=fetch-next-2]
