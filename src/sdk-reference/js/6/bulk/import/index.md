---
layout: sdk.html.hbs
algolia: true
title: import
description: Performs a bulk import on a collection
order: 200
---

# Import

Performs a bulk import on a collection

## Signature

```javascript
/**
* @param {Array} bulkData
* @param {Object} [options]
* @returns {Promise.<Object>}
*/
import(bulkData, options = null)
```

## Description

The bulk import allows to save a list of documents into a specific collection (belonging to a specific index). If a subset of the documents fails to save, a [PartialError](https://docs.kuzzle.io/api-documentation/errors#partialerror) is triggered. The `data` argument passed to the method must specify the set of documents to import and must satisfy the [Elasticsearch Bulk API](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/docs-bulk.html).

### The Elasticsearch Bulk API in brief

This API takes a JSON array containing a list of objects working in pairs. In each pair, the first object specifies the action to perform (the most common is `create`) and the second specifies the document itself, like in the example below:

```javascript
[
  // The action object
  { create: { _id: 'id', _index: 'index', _type: 'collection' } },
  // The document object
  { myField: 'myValue', myOtherField: 'myOtherValue' },
  // Another action object
  { create: { _id: 'another-id', _index: 'index', _type: 'collection' } },
  // Another document object
  { myField: 'anotherValue', myOtherField: 'yetAnotherValue' }
];
```

Note that the action object always has an attribute whose key specifies the action to take and whose value is an object specifying the location of ano object in the database (the `_index`, `_type` and `_id` tuple). Also note that, in Elasticsearch, the field `_type` correspond to `collection` in Kuzzle.

Possible actions are `create`, `index`, `update`, `delete`.

Learn more at [Elasticsearch Bulk API](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/docs-bulk.html)

## Arguments

| Arguments  | Type        | Description                                         |
| ---------- | ----------- | --------------------------------------------------- |
| `bulkData` | Array       | The list of documents to be added to the collection |
| `options`  | object | An object containing query options.                 |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

---

## Resolve

On resolve, the response object is the one directly sent by Elasticsearch for the bulk request.

## Usage

[snippet=import]
