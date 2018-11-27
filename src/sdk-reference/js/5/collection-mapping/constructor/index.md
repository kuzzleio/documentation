---
layout: sdk.html.hbs
algolia: true
title: constructor
description: CollectionMapping:constructor
order: 1
algolia: true
---
  

# CollectionMapping
When creating a new data collection in the persistent data storage layer, Kuzzle uses a default mapping.
This means that, by default, you won't be able to exploit the full capabilities of our persistent data storage layer (currently handled by [ElasticSearch](https://www.elastic.co/products/elasticsearch)), and your searches may suffer from below-average performance, depending on the amount of data you stored in a collection and the complexity of your database.

The CollectionMapping object allows you to get the current mapping in a data collection and to modify it if necessary.

<aside class="notice">
Once a field mapping has been set, it cannot be removed without reconstructing the data collection.
</aside>

---

## CollectionMapping(Collection, [mapping])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `Collection` | [Collection]({{ site_base_path }}sdk-reference/collection/) | An instantiated Collection object |
| ``mapping`` | JSON Object | Optional mapping |

---

## Properties

| Property name | Type | Description | get/set |
|--------------|--------|-----------------------------------|---------|
| ``headers`` | JSON Object | Common headers for all sent documents. | get/set |
| ``mapping`` | object | Easy-to-understand list of mappings per field | get/set |

**Note:** the ``headers`` property is inherited from the provided [Collection]({{ site_base_path }}sdk-reference/collection/) object and can be overrided

## Usage

[snippet=constructor-1]
