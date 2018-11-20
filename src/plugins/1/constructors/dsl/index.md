---
layout: full.html.hbs
algolia: true
title: Dsl
---


# Dsl

{{{since "1.0.0"}}}

Instantiates a new [Koncorde]({{ site_base_path }}kuzzle-dsl/1) engine.


## exists

{{{since "1.0.0"}}}

Returns a boolean telling whether filters exist for an index-collection pair.

### Arguments

```js
exists(index, collection)
```

<br/>

| Arguments | Type | Description |
|
## getFilterIds

{{{since "1.0.0"}}}

Retrieves the list of filter identifiers registered on an index-collection pair.

### Arguments

```js
getFilterIds(index, collection)
```

<br/>

| Arguments | Type | Description |
|
## normalize

{{{since "1.1.0"}}}

Normalizes filters without storing them.

The result can be directly used with the [store](#store-default) function.

### Arguments

```js
normalize(index, collection, filters)
```

<br/>

| Arguments | Type | Description |
|
## register

{{{since "1.0.0"}}}

Registers a filter to the DSL. 

This method is equivalent to executing [normalize](#normalize-default) + [store](#store-default).

### Arguments

```js
register(index, collection, filters)
```

<br/>

| Arguments | Type | Description |
|
## remove

{{{since "1.0.0"}}}

Removes a filter.

### Arguments

```js
remove(filterId)
```

<br/>

| Arguments | Type | Description |
|
## store

{{{since "1.1.0"}}}

Stores filters, normalized with the [normalize](#normalize-default)) function.

### Arguments

```js
store(normalized)
```
<br/>

| Arguments | Type | Description |
|
## test

{{{since "1.0.0"}}}

Tests data and returns the matching filter identifiers.

### Arguments

```js
test(index, collection, data, [documentId])
```
<br/>

| Arguments | Type | Description |
|
## validate

{{{since "1.0.0"}}}

Validates the provided filters without storing them.

### Arguments

```js
validate(filters)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `filters` | <pre>object</pre> | Filters, in [Koncorde]({{ site_base_path }}kuzzle-dsl/1) format |

### Return

The `validate` function returns a promise, which is resolved if the filters are well-formed, and rejected otherwise.
