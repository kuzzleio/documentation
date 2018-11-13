---
layout: sdk.html.hbs
algolia: true
title: import
description: Performs a bulk import on a collection
---

# Import


Create, update or delete large amount of documents as fast as possible.

This route is faster than the `document:m*` routes family (e.g. [document:mCreate]({{ site_base_path }}sdk-reference/js/6/document/m-create)), but no real-time notifications will be generated, even if some of the documents in the import match subscription filters.

If some documents actions fail, the client will receive a [PartialError]({{ site_base_path }}api/1/documentation/errors/#partialerror) error.

## Arguments

```javascript
import (bulkData, [options])
```

<br/>

| Arguments  | Type        | Description                                         |
| ---------- | ----------- | --------------------------------------------------- |
| `bulkData` | <pre>object[]</pre> | List of documents to be added to the collection |
| `options`  | <pre>object</pre> | Query options         |


### **bulkData**

This API takes a JSON array containing a list of objects working in pairs.
In each pair, the first object specifies the action to perform (the most common is `create`) and the second specifies the document itself, like in the example below:

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

Learn more at [Elasticsearch Bulk API](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/docs-bulk.html)

### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

---

## Resolves

An array containing objects indicating the import status for each document.
Each object has the following structure:

```javascript
{
  "<action>": {
    _id: "another-id",
    status: 200
  }
}
```

## Usage

[snippet=import]
