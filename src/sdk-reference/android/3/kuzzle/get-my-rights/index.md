---
layout: sdk.html.hbs
algolia: true
title: getMyRights
description: Kuzzle:getMyRights
algolia: true
---
  

# getMyRights
Gets the rights for the current user.

---

## getMyRights([options], callback)

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

## Callback Response

Returns an array of rights.

## Usage

[snippet=get-my-rights-1]
> Callback response

```json
[
  {
    "controller": "my-controller",
    "action": "my-action",
    "index": "*",
    "collection": "*",
    "value": "allowed"
  },
  {
    "controller": "another-controller",
    "action": "*",
    "index": "my-index",
    "collection": "*",
    "value": "conditional"
  }
]
```