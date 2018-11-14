---
layout: sdk.html.hbs
algolia: true
title: set
description: CollectionMapping:set
---
  

# set

[snippet=set-1]
Adds or updates a field mapping.

<aside class="notice">
Changes made by this function won't be applied until you call the <code>apply</code> method
</aside>

---

## set(field, mapping)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``field`` | string | Name of the field from which the mapping is to be added or updated |
| ``mapping`` | JSON Object | Mapping for this field, following the [Elasticsearch Mapping format](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/mapping.html)

---

## Return Value

Returns this `CollectionMapping` object to allow chaining.
