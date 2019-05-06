---
layout: sdk.html.hbs
title: updateMapping
description: Update the collection mapping
---

# updateMapping

Updates a collection mapping.

{{{since "Kuzzle 1.7.1"}}}

You can define the collection [dynamic mapping policy]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata) within the `_meta` root field.

<br/>

```javascript
updateMapping (index, collection, mapping, [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | <pre>string</pre> | Index name    |
| ``collection`` | <pre>string</pre> | Collection name    |
| ``mapping`` | <pre>object</pre> | Describes the collection mapping |
| ``options`` | <pre>object</pre> | Query options    |

### mapping

An object representing the data mapping of the collection.

```js
const mapping = {
  dynamic: '[true|false|strict]',
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

More informations about database mappings [here]({{ site_base_path}}guide/1/essentials/database-mappings)

### options

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolves

Resolve if the collection is successfully updated.

## Usage

[snippet=update-mapping]
