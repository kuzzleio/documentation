---
layout: sdk.html.hbs
algolia: true
title: searchSpecifications
description: Search for specifications
order: 200
---

# searchSpecifications

Allows to search in the persistence layer for collection specifications.

## Signature

```javascript
/**
 * @param {object} [body]
 * @param {object} [options]
 * @returns {Promise.<SpecificationsSearchResult>}
 */
searchSpecifications(body = {}, options = null)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``body`` | object | An object containing the search query    | no  |
| ``options`` | object | Query options    | no  |


### **body**

The body is a set of filters using [Elasticsearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/search-request-body.html) to match the documents you are looking for.  
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

### **options**

| Arguments    | Type    | Description | Default |
|--------------|---------|-------------|---------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| ``from`` | number | Offset of the first document    | `0`  |
| ``size`` | number | Maximum number of documents returned    | `10` |
| ``scroll`` | number | Maximum duration for scroll session    | undefined |

* `size` controls the maximum number of documents returned in the response
* `from` is usually used with the `size` argument, and defines the offset from the first result you want to fetch
* `scroll` is used to fetch large result sets, and it must be set with a [time duration](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/common-options.html#time-units). If set, a forward-only cursor will be created (and automatically destroyed at the end of the set duration), and its identifier will be returned in the `scrollId` property, along with the first page of the results.

## Resolve

Resolve to a [SpecificationsSearchResult]({{ site_base_path }}sdk-reference/js/6/essentials/search-result).

## Usage

[snippet=search-specifications]
