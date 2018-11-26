---
layout: sdk.html.hbs
algolia: true
title: save
description: Profile:save
algolia: true
---
  

# save
Creates or replaces the profile in Kuzzle.

<aside class="warning">
Saving the object will return an error if the linked roles have not been previously created in Kuzzle.
</aside>

---

## save([options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Return Value

Returns the `Profile` object to allow chaining.

---

## Callback Response

Returns a `Profile` object.

## Usage

[snippet=save-1]
