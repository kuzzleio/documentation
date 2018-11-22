---
layout: sdk.html.hbs
algolia: true
title: replaceDocument
description: Collection:replaceDocument
algolia: true
---
  

# replaceDocument
Replace an existing document and return the updated version as a [Document]({{ site_base_path }}sdk-reference/document/) object.

---

## replaceDocument(documentId, content, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``documentId`` | string | Unique document identifier |
| ``content`` | JSON Object | Content of the document to create |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Optional callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``volatile`` | JSON Object | Additional information passed to notifications to other users | ``null`` |
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``refresh`` | string | If set to ``wait_for``, Kuzzle will wait for the persistence layer to finish indexing (available with Elasticsearch 5.x and above) | ``undefined`` |

---

## Return Value

Returns the `Collection` object to allow chaining.

---

## Callback Response

Returns an updated [Document]({{ site_base_path }}sdk-reference/document/) object.

## Usage

[snippet=replace-document-1]
