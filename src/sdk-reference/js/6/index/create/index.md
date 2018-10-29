---
layout: sdk.html.hbs
algolia: true
title: create
description: Create an index
order: 200
---

# create

Create a new `<index>` in Kuzzle.

## Signature

```javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise<object>}
 */
create (index, options = null)
```

## Arguments

| Arguments | Type   | Description      |
| --------- | ------ | ----------------- |
| `index`   | string | Index name        |
| `options` | object | Query options |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to an `object` containing the index creation status

| Name                | Type    | Description                                                                                                       |
| ------------------- | ------- | ----------------------------------------------------------------------------------------------------------------- |
| `acknowledged`        | boolean | Indicates whether the index was successfully created in the Elastic cluster                                       |
| `shards_acknowledged` | boolean | Indicates whether the requisite number of shard copies were started for each shard in the index before timing out |

## Usage

[snippet=create]
