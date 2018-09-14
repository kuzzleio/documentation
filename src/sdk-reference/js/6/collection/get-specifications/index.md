---
layout: sdk.html
algolia: true
title: getSpecifications
description: Returns the validation specifications
order: 200
---

# getSpecifications

Returns the validation specifications associated to the collection.

## Signature

```javascript
/**
* @param {string} index
* @param {string} collection
* @param {object} [options]
* @returns {Promise.<object>}
 */
getSpecifications(index, collection, options = {})
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``options`` | Object | Query options    | no  |

###### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolve to an object representing the collection specifications.

## Usage

[snippet=get-specifications]
