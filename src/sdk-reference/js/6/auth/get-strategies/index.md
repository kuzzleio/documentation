---
layout: sdk.html
algolia: true
title: getStrategies
description: Get all authentication strategies registered in Kuzzle.
order: 200
---

# getStrategies

Get all authentication strategies registered in Kuzzle.

## Signature

```javascript
/**
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 */
getStrategies (options = {})
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | JSON Object | A structure containing query options.

###### **Options**

Additional query options

| Option     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | bool | Make this request queuable or not | `true`

## Resolve

An array of string.

## Usage

[snippet=get-strategies]
