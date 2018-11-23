---
layout: sdk.html.hbs
algolia: true
title: save
description: Role:save
algolia: true
---
  

# save
Creates or replaces the role in Kuzzle's database layer.

---

## save([options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | (Optional) Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Return Value

Returns the `Role` object to allow chaining.

---

## Callback Response

Returns a `Role` object.

## Usage

[snippet=save-1]
