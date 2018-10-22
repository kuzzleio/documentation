---
layout: sdk.html.hbs
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
 * @param {object} [options]
 * @returns {Promise<object>}
 */
getStrategies (options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | object | Query options

### **options**

Additional query options

| Property     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | boolean | Make this request queuable or not | `true`

## Resolve

An array of available strategies name.

## Usage

[snippet=get-strategies]
