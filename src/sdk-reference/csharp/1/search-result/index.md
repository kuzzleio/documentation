---
layout: sdk.html.hbs
title: SearchResult
description: How to deal with Kuzzle search results
order: 400
---

# SearchResult

When performing a [document:search]({{ site_base_path }}sdk-reference/csharp/1/document/search), Kuzzle returns an `SearchResult` object, which holds the items matching the given query and allows to drill through next result pages if applicable.

The type of object returned depends on the method called. For instance, [document:search]({{ site_base_path }}sdk-reference/csharp/1/document/search) method returns a `SearchResult` object and [collection:searchSpecifications]({{ site_base_path }}sdk-reference/csharp/1/collection/search-specifications) method returns a `SpecificationSearchResult` object. Both types behave the same way.

## Properties

| Property | Type | Description |
|--- |--- |--- |
| `aggregations` | <pre>string</pre> | Search aggregations if any |
| `hits` | <pre>string</pre> | JSON string representing the retrieved items for the current page |
| `total` | <pre>int</pre> | Total number of items matching the given query in Kuzzle database |
| `fetched` | <pre>int</pre> | Number of retrieved items so far |
| `scroll_id` | <pre>string</pre> | Scroll identifier if the search was given a `scroll` parameter |
