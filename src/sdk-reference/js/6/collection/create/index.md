---
layout: sdk.html.hbs
title: create
description: Create a new collection
---

# create

Creates a new [collection]({{ site_base_path }}guide/1/essentials/persisted) in Kuzzle via the persistence engine, in the provided index.

{{{since Kuzzle "1.3.0"}}}

You can also provide an optional body with a [collection mapping]({{ site_base_path }}guide/1/essentials/database-mappings) that allow you to exploit the full capabilities of our persistent data storage layer.

This method will only update the mapping when the collection already exists.

{{{since Kuzzle "1.7.1"}}}

You can define the collection [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) within the `_meta` root field.

<br/>

```javascript
create (index, collection, [mapping], [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | <pre>string</pre> | Index name    |
| ``collection`` | <pre>string</pre> | Collection name    |
| ``mapping`` | <pre>object</pre> | Describes the collection mapping   |
| ``options`` | <pre>object</pre> | Query options    |

### mapping

An object representing the data mapping of the collection.

```js
const mapping = {
  dynamic: 'false',
  _meta: {
    field: 'value'
  },
  properties: {
    field1: { type: 'text' },
    field2: {
      properties: {
        nestedField: { type: 'keyword'}
      }
    }
  }
};
```

More informations about collection mapping: {{ site_base_path}}guide/1/essentials/database-mappings

### options

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolves

Resolves if the collection is successfully created.

## Usage

[snippet=create]
