---
layout: sdk.html.hbs
title: next
description: SearchResult next method
---

# next

Returns a pointer to a new `SearchResult` object which contain the subsequent results of the search.

## Signature

```csharp
public SearchResult next();

```

## Behaviour of the next method

In order to be able to compute the next search page, some initial conditions must be met.

Depending on the arguments given to the initial search, thhe `next` method will pick one of the following policies, by decreasing order of priority (i.e. a search including a `scroll`, `sort` and `size` will use the `scroll` policy).

If no policy is applicable, the `next` method will throw an exception.

<div class="alert alert-info">
  <p>
  When processing a large number of documents (i.e. more than 1000), it is advised to use a <code>scroll</code> cursor.
  </p>
  <p>
  It is also the only method that garantees all matching documents will be retrieved and no duplicates will be included.
  </p>
</div>

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Return

A new `SearchResult` containing the next results of the search.

## Usage

[snippet=search-result]
## Usage

[snippet=search-result]
