---
layout: sdk.html.hbs
algolia: true
title: updateMapping
description: Update the collection mapping
---

# updateMapping

Update the collection mapping.

Mapping allow you to exploit the full capabilities of our persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping.html)).

## Arguments

```javascript
updateMapping (index, collection, mapping, [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | <pre>string</pre> | Index name    |
| ``collection`` | <pre>string</pre> | Collection name    |
| ``mapping`` | <pre>object</pre> | Collection data mapping    |
| ``options`` | <pre>object</pre> | Query options    |

### mapping

An object representing the collection data mapping.

This object must have a root field `properties` that contain the mapping definition:
```javascript
const mapping = {
  properties: {
    field1: { type: 'text' },
    field2: {
      properties: {
        nestedField: { type: 'keyword' }
      }
    }
  }
};
```

You can see the full list of Elasticsearch mapping types [here](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping.html).

### options

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolves

Resolve if the collection is successfully updated.

## Usage

[snippet=update-mapping]
