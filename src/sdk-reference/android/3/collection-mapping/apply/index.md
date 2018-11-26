---
layout: sdk.html.hbs
algolia: true
title: apply
description: CollectionMapping:apply
algolia: true
---
  

# apply
Applies the new mapping to the data collection.

---

## apply([options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Optional callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Return Value

Returns this `CollectionMapping` object to allow chaining.

---

## Callback Response

Returns the updated `CollectionMapping` object.

## Usage

[snippet=apply-1]
