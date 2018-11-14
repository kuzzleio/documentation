---
layout: sdk.html.hbs
algolia: true
title: mcreateDocument
description: Collection:mcreateDocument
---
  

# mCreateDocument
[snippet=mcreate-document-1]

> Callback response:
Create the input [Documents]({{ site_base_path }}sdk-reference/document/).

---

## mCreateDocument(documents, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``documents`` | Document[] | Array of [Document]({{ site_base_path }}sdk-reference/document/) to create |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Optional callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Return Value

Returns the `Collection` object to allow chaining.

---

## Callback Response

Returns a `JSON object` containing the raw Kuzzle response.
Can return a 206 partial error in cases where some documents could not be created.