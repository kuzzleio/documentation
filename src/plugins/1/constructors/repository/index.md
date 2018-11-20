---
layout: full.html.hbs
algolia: true
title: Repository
---


# Repository

{{{since "1.0.0"}}}

Provides access to a data collection inside the plugin's dedicated and secure storage.

If this is not already the case, the data collection must first be created, using the [storage]({{ site_base_path }}plugins/1/accessors/storage) accessor.


## create

{{{since "1.0.0"}}}

Creates a document.

### Arguments

```js
create(document, [options])
```
<br/>

| Arguments | Type | Description |
|
## createOrReplace

{{{since "1.0.0"}}}

Creates or replaces a document.

### Arguments

```js
createOrReplace(document, [options])
```
<br/>

| Arguments | Type | Description |
|
## delete

{{{since "1.0.0"}}}

Deletes a document.

### Arguments

```js
delete(id, [options])
```

<br/>

| Arguments | Type | Description |
|
## get

{{{since "1.0.0"}}}

Gets a document.

### Arguments

```js
get(id)
```

<br/>

| Arguments | Type | Description |
|
## mGet

{{{since "1.0.0"}}}

Gets multiple documents.

### Arguments

```js
mGet(ids)
```

<br/>

| Arguments | Type | Description |
|
## replace

{{{since "1.0.0"}}}

Replaces the content of a document.

### Arguments

```js
replace(document, [options])
```

<br/>

| Arguments | Type | Description |
|
## search

{{{since "1.0.0"}}}

Searches documents.

### Arguments

```js
search(query, [options])
```

<br/>

| Arguments | Type | Description |
|
## scroll

Moves a search cursor forward.

A search cursor is created by a [search](#search-default) function call, with a `scroll` option value provided.

### Arguments

```js
scroll(scrollId, [ttl])
```

<br/>

| Arguments | Type | Description |
|
## update

{{{since "1.0.0"}}}

Updates parts of a document's content.

### Arguments

```js
update(document, [options])
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `document` | <pre>object</pre> | Parts of the document to update. The provided object must contain a `_id` property, which is the document unique identifier |
| `options` | <pre>object</pre> | Optional arguments |

#### options

The `options` argument accepts the following parameters:

| Options | Type | Description |
|---------|------|-------------|
| `refresh` | <pre>string</pre> | If set with the `wait_for` string value, then the function will respond only after the document has been indexed |

### Return

The `update` function returns a promise , resolving to the document update result.

### Example

```js
const content = {
  _id: '<unique id>', 
  someField: 'some content',
  anotherField: 'another content'
};

try {
  const result = repository.update(content);
  /*
   * Outputs:
   * { _index: '%<plugin name>',
   *   _type: '<data collection>',
   *   _id: '<a unique id>',
   *   _version: 1,
   *   result: 'updated',
   *   _shards: { total: 2, successful: 1, failed: 0 },
   *   _source: { 
   *     someField: 'some content', 
   *     anotherField: 'another content' 
   *   } 
   * }
   */
  console.dir(result, {depth: null});
} catch(error) {
  // "error" is a KuzzleError object
}
```
