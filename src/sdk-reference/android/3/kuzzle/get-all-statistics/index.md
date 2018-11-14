---
layout: sdk.html.hbs
algolia: true
title: getAllStatistics
description: Kuzzle:getAllStatistics
---
  

# getAllStatistics
[snippet=get-all-statistics-1]
> Callback response example:
Kuzzle monitors active connections, and ongoing/completed/failed requests.  
This method returns all available statistics from Kuzzle.

---

## getAllStatistics([options], callback)

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

### Callback Response

Returns an array of JSON objects that each contain a statistics frame.
