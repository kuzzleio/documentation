---
layout: sdk.html.hbs
algolia: true
title: getServerInfo
description: Kuzzle:getServerInfo
---
  

# getServerInfo
[snippet=get-server-info-1]

> Callback response example:
Retrieves information about Kuzzle plugins and active services.

---

## getServerInfo([options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON object | Optional parameters |
| ``callback`` | function | Optional callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Callback Response

Returns a JSON object containing server information.
