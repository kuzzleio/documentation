---
layout: sdk.html.hbs
algolia: true
title: delete
description: Profile:delete
algolia: true
---
  

# delete
Deletes this security profile from Kuzzle.

---

## delete([options], [callback])

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

## Callback Response

Returns the ID of the deleted profile.

## Usage

[snippet=delete-1]
