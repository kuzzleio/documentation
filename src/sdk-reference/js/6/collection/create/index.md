---
layout: sdk.html.hbs
algolia: true
title: create
description: Create a new collection
order: 200
---

# create

Creates a new [collection]({{ site_base_path }}guide/essentials/persisted) in Kuzzle via the persistence engine, in the provided `index`.  
You can also provide an optional data mapping that allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html)).  

This method will only update the mapping if the collection already exists.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} [mapping]
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
create(index, collection, mapping = {}, options = {})
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``mapping`` | Object | Collection data mapping    | no  |
| ``options`` | Object | An object containing query options    | no  |

### **mapping**

An object representing the data mapping of the collection.  

The mapping must have a root field `properties` that contain the mapping definition:
```js
const mapping = {
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

You can see the full list of Elasticsearch mapping types [here](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html).

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves if the collection is successfully created.

## Usage

[snippet=create]
