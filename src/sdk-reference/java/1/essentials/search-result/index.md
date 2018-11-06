---
layout: sdk.html.hbs
algolia: true
title: Search Results
description: How to deal with Kuzzle search results
order: 100
---
# Search Results

When performing a search, Kuzzle returns an `SearchResult` object, which holds the items matching the given query and allow to drill through next result pages if applicable.

The type of the `SearchResult` object depends on the method which is called. All these objects share some common properties.

## Common structure

### Properties

| Name | Type | Description |
|--- |--- |--- |
| aggregations | `std::string` | The search` aggregations if any |
| hits | `std::string` | A JSON string containing the retrieved items |
| total | unsigned | The total number of items matching the given query in Kuzzle database |
| fetched | unsigned | The number of retrieved items so far |
| scroll_id | std::string | A scroll identifier if the search was given a `scroll` parameter |

### Methods

The `SearchResult` objects have a unique `next` method, which returns a new `SearchResult` object of the same type.

#### Signature

```cpp
SearchResult* SearchResult::next();
SpecificationSearchResult* SpecificationSearchResult::next();
```

## Behaviour of the next method

In order to be able to compute the next search page, some initial conditions must be met.

<div class="alert alert-info">
  <p>
  When processing a large number of documents (i.e. more than 1000), it is advised to use a <code>scroll</code> cursor.
  </p>
  <p>
  It is also the only method that garantees all matching documents will be retrieved and no duplicates will be included.
  </p>
</div>

The `next` method behaves using the following policies, by decreasing order of priority (i.e. a search including a scroll + sort + size will use the scroll policy).

If no policy is applicable, the `next` method will throw an exception.

### 1. scroll

**This is the preferred way to get some paginated results**.

If the original search is given a `scroll` parameter, the `next` method will use the scroll_id to paginate results.

The results that are returned from a scroll request reflect the state of the index at the time the initial `search` request was performed, like a snapshot in time.

As such, even if some documents are added or deleted from the database between two calls to `next`, the result is garanteed to include all items matching the query at the time the initial `search` was sent and to not get any duplicate between two search pages.

### 2. sort / size

If the initial search is given some `sort` and `size` parameters, the `next` method will retrieved the next items matching the sort.

To avoid too many duplicates, it is advised to provide a sort combination that will always identify one item only. The recommended way is to use the field `_uid` which is certain to contain one unique value for each document.

Because this method does not freeze the research between two calls, if some updates are applied to the database between two calls, it is possible to miss some documents and/or to get some duplicates between search pages.

### 3. from / size

If the initial search is given some `from` and `size` parameters, the `next` method will increment the `from` parameter to retrieved the next results.

Because this method does not freeze the research between two calls, if some updates are applied to the database between two calls, it is possible to miss some documents and/or to get some duplicates between search pages.

<div class="alert alert-info">
  <p>
    NB: It is not possible to retrieve more than 10000 items using this method. Above that limit, any call to <code>next</code> will throw an Exception.
  </p>
</div>


## SearchResult types

| Name | Returned by | |
|--- |--- |--- |
| SearchResult | [`document.search`]({{ site_base_path }}/sdk-reference/cpp/1/document/search/) | 
| SpecificationSearchResult | [`collection.searchSpecifications`]({{ site_base_path }}/sdk-reference/cpp/1/collection/searchSpecifications) | 

## Usage

[snippet=search-result]