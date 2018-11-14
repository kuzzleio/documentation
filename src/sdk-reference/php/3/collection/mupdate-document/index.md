---
layout: sdk.html.hbs
algolia: true
title: mupdateDocument
description: Collection:mupdateDocument
---
  

# mUpdateDocument
[snippet=mupdate-document-1]

> Callback response:
Update the provided [Documents]({{ site_base_path }}sdk-reference/document/).

---

## mUpdateDocument(documents, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``documents`` | Document[] | Array of [Documents]({{ site_base_path }}sdk-reference/document/) to update |
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
Can return a 206 partial error in cases where documents could not be updated. 
