---
layout: full.html.hbs
title: Koncorde
---

# Koncorde

{{{since "1.6.0"}}}

Instantiates a new [Koncorde]({{ site_base_path }}koncorde/1) engine.

---

## Constructor

This class constructor takes no argument.

---

## exists

{{{since "1.0.0"}}}

Returns a boolean telling whether filters exist for an index-collection pair.

### Arguments

```js
exists(index, collection)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `index` | <pre>string</pre> | Data index name |
| `collection` | <pre>string</pre> | Data collection name |

### Return

The `exists` function returns a boolean telling whether at least one filter exists in the provided index-collection pair.

---

## getFilterIds

{{{since "1.0.0"}}}

Retrieves the list of filter identifiers registered on an index-collection pair.

### Arguments

```js
getFilterIds(index, collection)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `index` | <pre>string</pre> | Data index name |
| `collection` | <pre>string</pre> | Data collection name |

### Return

The `getFilterIds` function returns an array of strings, containing the exhaustive list of filter identifiers registered in the provided index-collection pair.

---

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
|-----------|------|-------------|
| `index` | <pre>string</pre> | Data index name |
| `collection` | <pre>string</pre> | Data collection name |
| `filters` | <pre>object</pre> | Filters, in [Koncorde]({{ site_base_path }}koncorde/1) format |

### Return

The `normalize` function returns a promise resolving to an object with the following properties:

| Field | Type | Description |
|-------|------|-------------|
| `collection` | <pre>string</pre> | Data collection name |
| `id` | <pre>string</pre> | The filter unique identifier |
| `index` | <pre>string</pre> | Data index name |
| `normalized` | <pre>object[]</pre> | Normalized/optimized version of the supplied filters |

---

## register

{{{since "1.0.0"}}}

Registers a filter to this Koncorde instance. 

This method is equivalent to executing [normalize](#normalize-default) + [store](#store-default).

### Arguments

```js
register(index, collection, filters)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `index` | <pre>string</pre> | Data index name |
| `collection` | <pre>string</pre> | Data collection name |
| `filters` | <pre>object</pre> | Filters, in [Koncorde]({{ site_base_path }}koncorde/1) format |

### Return

The `register` functions returns a promise, resolving to an object with the following attributes:

| Field | Type | Description |
|-------|------|-------------|
| `id` | <pre>string</pre> | The filter unique identifier |
| `diff` | <pre>object</pre> | If the filter doesn't already exist in the engine, contains the normalized version of the provided filters |

---

## remove

{{{since "1.0.0"}}}

Removes a filter.

### Arguments

```js
remove(filterId)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `filterId` | <pre>string</pre> | Filter unique identifier, obtained either with [normalize](#normalize-default) or [register](#register-default) |

### Return

The `remove` function returns a promise, resolved once the filter has been completely removed from Koncorde.

---

## store

{{{since "1.1.0"}}}

Stores filters, normalized with the [normalize](#normalize-default)) function.

### Arguments

```js
store(normalized)
```
<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `normalized` | <pre>object</pre> | Normalized filters |

### Return 

The `store` function returns an object with the following attributes:

| Field | Type | Description |
|-------|------|-------------|
| `id` | <pre>string</pre> | The filter unique identifier |
| `diff` | <pre>object</pre> | If the filter didn't already exist, contains the normalized version of the provided filters |

---

## test

{{{since "1.0.0"}}}

Tests data and returns the matching filter identifiers.

### Arguments

```js
test(index, collection, data, [documentId])
```
<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `index` | <pre>string</pre> | Data index name |
| `collection` | <pre>string</pre> | Data collection name |
| `data` | <pre>object</pre> | Data to test |
| `documentId` | <pre>string</pre> | Document unique identifier |

### Return

The `test` function returns an array of strings, which is the exhaustive list of matching filter identifiers.

---

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
| `filters` | <pre>object</pre> | Filters, in [Koncorde]({{ site_base_path }}koncorde/1) format |

### Return

The `validate` function returns a promise, which is resolved if the filters are well-formed, and rejected otherwise.
