---
layout: sdk.html.hbs
algolia: true
title: refresh
description: Document:refresh
algolia: true
---
  

# refresh
Creates a new `Document` object with the last version of this document stored in Kuzzle.

---

## refresh([options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Callback Response

Return a new `Document` object containing the last document version.

## Usage

[snippet=refresh-1]
