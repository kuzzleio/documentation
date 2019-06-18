---
code: true
type: page
title: next
description: SearchResult next method
---

# next

Returns a smart pointer to a new `SearchResult` object containing the next batch of search results.

## Arguments

```cpp
std::shared_ptr<kuzzleio::SearchResult> next() const;
```

## Behavior of the next method

In order to be able to compute the next search page, some initial conditions must be met.

Depending on the arguments given to the initial search, the `next` method will pick one of the following policies, by decreasing order of priority (i.e. a search including a `scroll`, `sort` and `size` will use the `scroll` policy).

If no policy is applicable, the `next` method throws an exception.

:::info
When processing a large number of documents (i.e. more than 1000), it is advised to use a scroll cursor (see the [scroll search option](/sdk/cpp/1/controllers/document/search/#arguments)).

It is also the only method that guarantees that all matching documents will be retrieved, without duplicates.
:::

## Usage with scroll

**This is the preferred way to get paginated results.**

If the original search is given a `scroll` parameter, the `next` method will use a cursor to paginate results.

The results that are returned from a scroll request reflect the state of the index at the time the initial `search` request was performed, like a persistent snapshot.

As such, even if some documents are added or deleted from the database between two calls to `next`, the result is guaranteed to include all items matching the query at the time the initial `search` was sent and to not get any duplicate between two search pages.

<<< ./snippets/scroll.cpp

## Usage with sort / size

If the initial search is given `sort` and `size` parameters, the `next` method will retrieve the next items matching the sort.

To avoid too many duplicates, it is advised to provide a sort combination that will always identify one item only. The recommended way is to use the field `_uid` which is certain to contain one unique value for each document.

Because this method does not freeze the research between two calls, if updates are applied to the database between two calls, it is possible to miss documents and/or to get duplicates between search pages.

## Usage with from / size

If the initial search is given `from` and `size` parameters, the `next` method will increment the `from` parameter to retrieve the next results.

Because this method does not freeze the research between two calls, if updates are applied to the database between two calls, it is possible to miss documents and/or to get duplicates between search pages.

:::info
It is not possible to retrieve more than 10000 items using this method. Beyond that limit, any call to `next` will throw an Exception.
:::

<<< ./snippets/fromsize.cpp

## Return

A smart pointer to a new `SearchResult` containing the next results of the search.
