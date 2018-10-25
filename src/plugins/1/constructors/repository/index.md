---
layout: full.html.hbs
algolia: true
title: Repository
---

# Repository

{{{since "1.0.0"}}}

Provides access to a data collection inside the plugin's dedicated and secure storage.

If this is not already the case, the data collection must first be created, using the [storage]({{ site_base_path }}plugins/1/accessors/storage) accessor.

---

## Constructor

`new context.Repository(collection, [ObjectConstructor])`

* `collection`: {string} the repository's data collection to link to this class instance
* `ObjectConstructor`: {optional, class} fetched data are returned as plain objects. If an `ObjectConstructor` is provided, the data will be returned as instances of that class instead

---

## create

{{{since "1.0.0"}}}

Creates a document.

### Arguments

`create(document, [options])`

* `document`: {object} the document to create. The provided object must contain a `_id` property, which is the document unique identifier
* `options`: {optional, object} optional arguments. The following options are available:
  * `refresh`: if set with the `wait_for` string value, then the function will respond only after the document has been indexed

### Return

The `create` function returns a promise, resolving to the document creation result.

### Example

```js
const content = {
  _id: '<unique id>',
  someField: 'some content',
  anotherField: 'another content'
};

try {
  const result = await repository.create(content);
  /*
   * Outputs:
   * { _index: '%<plugin name>',
   *   _type: '<data collection>',
   *   _id: '<a unique id>',
   *   _version: 1,
   *   result: 'created',
   *   _shards: { total: 2, successful: 1, failed: 0 },
   *   created: true,
   *   _source: { 
   *     someField: 'some content', 
   *     anotherField: 'another content' 
   *   } 
   * }
   */
  console.dir(result, {depth: null});
} catch (error) {
  // "error" is a KuzzleError object
}
```

---

## createOrReplace

{{{since "1.0.0"}}}

Creates or replaces a document.

### Arguments

`createOrReplace(document, [options])`

* `document`: {object} the document to create. The provided object must contain a `_id` property, which is the document unique identifier
* `options`: {optional, object} optional arguments. The following options are available:
  * `refresh`: if set with the `wait_for` string value, then the function will respond only after the document has been indexed

### Return 

The `createOrReplace` function returns a promise, resolving to the document creation/replacement result.

### Example

```js
const content = {
  _id: '<unique id>',
  someField: 'some content',
  anotherField: 'another content'
};

try {
  const result = await repository.createOrReplace(content);
  /*
   * Outputs:
   * { _index: '%<plugin name>',
   *   _type: '<data collection>',
   *   _id: '<a unique id>',
   *   _version: 3,
   *   result: 'created',
   *   _shards: { total: 2, successful: 1, failed: 0 },
   *   created: false,
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

---

## delete

{{{since "1.0.0"}}}

Deletes a document.

### Arguments

`delete(id, [options])`

* `id`: {string} document unique identifier
* `options`: {optional, object} optional arguments. The following options are available:
  * `refresh`: if set with the `wait_for` string value, then the function will respond only after the document deletion has been indexed

### Return 

The `delete` function returns a promise, resolving to the document deletion result.

### Example

```js
try {
  await repository.delete('someDocumentId');
  /*
   * Outputs:
   *  { found: true,
   *    _index: '%<plugin name>',
   *    _type: '<data collection>',
   *    _id: 'someDocumentId',
   *    _version: 3,
   *    result: 'deleted',
   *    _shards: { total: 2, successful: 1, failed: 0 } 
   *  }
   */
  console.dir(result, {depth: null});
} catch(error) {
  // "error" is a KuzzleError object
}
```

---

## get

{{{since "1.0.0"}}}

Gets a document.

### Arguments

`get(id)`

* `id`: {string} document unique identifier

### Return

The `get` function returns a promise, resolving to the retrieved document's content.

If an `ObjectConstructor` argument was provided to the repository constructor, then the content is returned as an instance of that class instead of a raw object.

---

## mGet

{{{since "1.0.0"}}}

Gets multiple documents.

### Arguments

`mGet([id1, id2, ...])`

* `[id1, id2, ...]`: {string[]} document unique identifiers 

### Return

The `mGet` function returns a promise, resolving to the list of documents contents

If an `ObjectConstructor` argument was provided to the repository constructor, then each  content is returned as an instance of that class instead of a raw object.

---

## replace

{{{since "1.0.0"}}}

Replaces the content of a document.

### Arguments

`replace(document, [options])`

* `id`: {string} document unique identifier
* `options`: {optional, object} optional arguments. The following options are available:
  * `refresh`: if set with the `wait_for` string value, then the function will respond only after the document deletion has been indexed

### Return

The `replace` function returns a promise, resolving to the document replacement result.

### Example

```js
const content = {
  _id: '<unique id>', 
  someField: 'some content',
  anotherField: 'another content'
};

try {
  const result = await repository.replace(content);
  /*
   * Outputs:
   * { _index: '%<plugin name>',
   *   _type: '<data collection>',
   *   _id: '<a unique id>',
   *   _version: 3,
   *   _shards: { total: 2, successful: 1, failed: 0 },
   *   created: false,
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

---

## search

{{{since "1.0.0"}}}

Searches documents.

### Arguments

`search(query, [opts])`

* `query`: {object} search query, using Elasticsearch [query format]({{ site_base_path }}elasticsearch-cookbook/basic-queries)
* `options`: {optional, object} search options. Available values:
  * `from`: {integer} paginates search results by defining the offset from the first result you want to fetch. Usually used with the `size` option
  * `scroll`: {string} creates a forward-only result cursor. This option must be set with a [time duration](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/common-options.html#time-units), at the end of which the cursor is destroyed. If set, a cursor identifier named `scrollId` is returned in the results. This cursor can then be moved forward using the [scroll](#scroll-default) function
  * `size`: {integer} set the maximum number of documents returned per result page

### Return 

The `search` function returns a promise resolving to a search result object, with the following properties:

* `hits`: {object[]} array of found documents. If a `ObjectConstructor` argument was provided to the repository constructor, then each hit is an instance of that class instead of a raw object
* `total`: {integer} total number of found documents. Can be greater than the number of documents returned in this result set

---

## scroll

Moves a search cursor forward.

A search cursor is created by a [search](#search-default) function call, with a `scroll` option value provided.

### Arguments

`scroll(scrollId, [ttl])`

* `scrollId`: {string} scroll unique identifier, obtained by the last search/scroll function call (scroll identifiers may change from page to page)
* `ttl`: {optional, string} refresh the cursor duration, using the [time to live](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/common-options.html#time-units) syntax

### Return

The `scroll` function returns a promise resolving to a search result object, with the following properties:

* `hits`: {object[]} array of found documents. If a `ObjectConstructor` argument was provided to the repository constructor, then each hit is an instance of that class instead of a raw object
* `total`: {integer} total number of found documents. Can be greater than the number of documents returned in this result set

---

## update

{{{since "1.0.0"}}}

Updates parts of a document's content.

### Arguments

`update(document, [options])`

* `document`: {object} parts of the document to update. The provided object must contain a `_id` property, which is the document unique identifier
* `options`: {optional, object} optional arguments. The following options are available:
  * `refresh`: if set with the `wait_for` string value, then the function will respond only after the document has been indexed

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
