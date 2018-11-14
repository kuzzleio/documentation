---
layout: sdk.html.hbs
algolia: true
title: truncate
description: Collection:truncate
---
  

# truncate
[snippet=truncate-1]
> Callback response:
Truncate the data collection, removing all stored documents but keeping all associated mappings.

This method is a lot faster than removing all documents using multiple delete requests.

---

## truncate([options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Optional callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``refresh`` | string | If set to ``wait_for``, Kuzzle will wait for the persistence layer to finish indexing (available with Elasticsearch 5.x and above) | ``undefined`` |

---

## Return Value

Returns the `Collection` object to allow chaining.

---

## Callback Response

Returns a `JSON object` containing the raw Kuzzle response.
