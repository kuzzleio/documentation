---
layout: sdk.html.hbs
algolia: true
title: searchSpecifications
description: Search for specifications
---

# searchSpecifications

Allows to search in the persistence layer for collection specifications.

## Arguments

```javascript
searchSpecifications (body = {}, [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``body`` | <pre>object</pre> | An object containing the search query    |
| ``options`` | <pre>object</pre> | Query options    |


### body

The body is a set of filters using [Elasticsearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-body.html) to match the documents you are looking for.
The filters must be inside the `query` property of the body.

Example:

```js
const body = {
  query: {
    match_all: {
      boost: 1
    }
  }
};
```

### options

|  Arguments     |  Type     |  Description  |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | Make this request queuable or not |
| ``from`` | <pre>number</pre><br/>(`0`) | Offset of the first document    |
| ``size`` | <pre>number</pre><br/>(`10`) | Maximum number of documents returned    |
| ``scroll`` | <pre>number</pre><br/>(`"`) | Maximum duration for scroll session    |

* `size` controls the maximum number of documents returned in the response
* `from` is usually used with the `size` argument, and defines the offset from the first result you want to fetch
* `scroll` is used to fetch large result sets, and it must be set with a [time duration](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/common-options.html#time-units). If set, a forward-only cursor will be created (and automatically destroyed at the end of the set duration), and its identifier will be returned in the `scrollId` property, along with the first page of the results.

## Resolves

Resolve to a [SpecificationsSearchResult]({{ site_base_path }}sdk-reference/js/6/essentials/search-result).

## Usage

[snippet=search-specifications]
