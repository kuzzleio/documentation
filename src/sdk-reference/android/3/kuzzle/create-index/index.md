---
layout: sdk.html.hbs
algolia: true
title: createIndex
description: Kuzzle:createIndex
---

  

# createIndex
Create a new empty data index, with no associated mapping.

| `index` | string | Optional index to query. If no set, defaults to [Kuzzle.defaultIndex]({{ site_base_path }}sdk-reference/kuzzle/#properties)
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns an object with the index creation status.

## Usage

[snippet=create-index-1]
> Callback response:

```json
{
  "acknowledged": true,
  "shards_acknowledged": true
}
```