---
layout: sdk.html.hbs
algolia: true
title: exists
description: Document:exists
---
  

# exists
[snippet=exists-1]

Checks if the document exists in Kuzzle.

---

## exists([options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not | ``true`` |

---

## Callback Response

Return a boolean indicating whether or not the document exists in Kuzzle.
