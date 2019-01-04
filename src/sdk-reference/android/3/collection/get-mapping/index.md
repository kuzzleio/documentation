---
layout: sdk.html.hbs
title: getMapping
description: Collection:getMapping
---
  

# getMapping
Retrieves the current mapping of this collection as a [CollectionMapping]({{ site_base_path }}sdk-reference/android/3/collection-mapping/) object.

---

## getMapping([options], callback)

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

Returns a [CollectionMapping]({{ site_base_path }}sdk-reference/android/3/collection-mapping/) object.

## Usage

[snippet=get-mapping-1]
