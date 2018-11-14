---
layout: sdk.html.hbs
algolia: true
title: update
description: User:update
---
  

# update
[snippet=update-1]
Performs a partial content update on this object.

---

## update(content, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``content`` | JSON Object | User content |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Optional callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Return Value

Returns the `User` object to allow chaining.

---

## Callback Response

Returns the updated version of this object.
