---
layout: sdk.html.hbs
algolia: true
title: getStrategies
description: Get all authentication strategies registered in kuzzle.
---

# getStrategies

Gets the exhaustive list of registered authentication strategies.

## Signature

```javascript
/**
 * @param {object} [options]
 * @returns {Promise<array<string>>}
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

An `array<string>` of available strategies name.

## Usage

[snippet=get-strategies]
