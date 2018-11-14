---
layout: sdk.html.hbs
algolia: true
title: listIndexes
description: Kuzzle:listIndexes
---
  

# listIndexes

[snippet=list-indexes-1]
> Callback response:

[snippet=list-indexes-2]

Returns the list of indexes stored in Kuzzle.

---

## listIndexes([options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Callback Response

Returns an `array` of index names.
