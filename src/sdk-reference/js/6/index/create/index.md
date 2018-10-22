---
layout: sdk.html.hbs
algolia: true
title: create
description: Create an index
order: 200
---

# create

Create a new index in Kuzzle

## Signature

```javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
create(index, (options = null));
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `index`   | String | Index name                          | yes      |
| `options` | Object | An object containing query options. | no       |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to an object containing the index creation status

| Name                | Type    | Description                                                                                                       |
| ------------------- | ------- | ----------------------------------------------------------------------------------------------------------------- |
| acknowledged        | boolean | indicates whether the index was successfully created in the Elastic cluster                                       |
| shards_acknowledged | boolean | indicates whether the requisite number of shard copies were started for each shard in the index before timing out |

## Usage

[snippet=create]
