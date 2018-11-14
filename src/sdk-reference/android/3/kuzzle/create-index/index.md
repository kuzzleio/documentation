---
layout: sdk.html.hbs
algolia: true
title: createIndex
description: Kuzzle:createIndex
---
  

# createIndex
[snippet=create-index-1]
> Callback response:
Create a new empty data index, with no associated mapping.

---
## createIndex([index], [options], callback)

| Arguments | Type | Description
|-----------|------|------------
| `index` | string | Optional index to query. If no set, defaults to [Kuzzle.defaultIndex]({{ site_base_path }}sdk-reference/kuzzle/#properties)
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

---

## Options

| Option | Type | Description | Default
|--------|------|-------------|---------
| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns an object with the index creation status.
