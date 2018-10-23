---
layout: full.html.hbs
algolia: true
title: Dsl
---

# Dsl

{{{since "1.0.0"}}}

Instantiates a new [Koncorde]({{ site_base_path }}kuzzle-dsl/1) engine.

---

## Constructor

This class constructor takes no argument.

---

## exists

{{{since "1.0.0"}}}

Returns a boolean telling whether filters exist for an index-collection pair.

### Arguments

`exists(index, collection)`

* `index`: {string} data index name
* `collection`: {string} data collection name

### Return

The `exists` function returns a boolean telling whether at least one filter exists in the provided index-collection pair.

---

## getFilterIds

{{{since "1.0.0"}}}

Retrieves the list of filter identifiers registered on an index-collection pair.

### Arguments

`getFilterIds(index, collection)`

* `index`: {string} data index name
* `collection`: {string} data collection name

### Return

The `getFilterIds` function returns an array of strings, contaning the exhaustive list of filter identifiers registered in the provided index-collection pair.

---

## normalize

{{{since "1.1.0"}}}

Normalizes filters without storing them.

The result can be directly used with the [store](#store-default) function.

### Arguments

`normalize(index, collection, filters)`

* `index`: {string} data index name
* `collection`: {string} data collection name
* `filters`: {object} filters, in [Koncorde]({{ site_base_path }}kuzzle-dsl/1) format

### Return

The `normalize` function returns a promise resolving to an object with the following properties:

* `index`: {string} data index name
* `collection`: {string} data collection name
* `normalized`: {object[]} normalized/optimized version of the supplied filters
* `id`: {string} the filter unique identifier

---

## register

{{{since "1.0.0"}}}

Registers a filter to the DSL. 

This method is equivalent to executing [normalize](#normalize-default) + [store](#store-default).

### Arguments

`register(index, collection, filters)`

* `index`: {string} data index name
* `collection`: {string} data collection name
* `filters`: {object} filters, in [Koncorde]({{ site_base_path }}kuzzle-dsl/1) format

### Return

The `register` functions returns a promise, resolving to an object with the following attributes:

* `id`: {string} the filter unique identifier
* `diff`: `false` if the filter already exists in the engine. Otherwise, contains an object with the normalized version of the provided filters

---

## remove

{{{since "1.0.0"}}}

Removes a filter.

### Arguments

`remove(filterId)`

* `filterId`: {string} filter unique identifier, obtained either with [normalize](#normalize-default) or [register](#register-default)

### Return

The `remove` function returns a promise, resolved once the filter has been completely removed from the DSL.

---

## store

{{{since "1.1.0"}}}

Stores filters, normalized with the [normalize]({{ site_base_path }}plugins/1/plugins-context/constructors/#normalize)) function.

### Arguments

`store(normalized)`

* `normalized`: {object} normalized filters

### Return 

The `store` function returns an object with the following attributes:

* `id`: {string} the filter unique identifier
* `diff`: {object} `false` if the filter already existed in the engine. Otherwise, contains an object with the normalized version of the provided filters

---

## test

{{{since "1.0.0"}}}

Tests data and returns the matching filter identifiers.

### Arguments

`test(index, collection, data, [documentId])`

* `index`: {string} data index name
* `collection`: {string} data collection name
* `data`: {object} data to test
* `documentId`: {optional, string} document unique identifier 

### Return

The `test` function returns an array of strings, which is the exhaustive list of matching filter identifiers.

---

## validate

{{{since "1.0.0"}}}

Validates the provided filters without storing them.

### Arguments

`validate(filters)`

* `filters`: {object} filters, in [Koncorde]({{ site_base_path }}kuzzle-dsl/1) format

### Return

The `validate` function returns a promise, which is resolved if the filters are well-formed, and rejected otherwise.
