---
layout: sdk.html.hbs
algolia: true
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
order: 200
---

# credentialsExist

Checks that the current authenticated user has credentials for the specified authentication `<strategy>`.

## Signature

```javascript
/**
 * @param {string} strategy
 * @param {object} [options]
 * @returns {Promise<boolean>}
 */
credentialsExist (strategy, options = null);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `local` | string | Strategy to use
| `options` | object | Query options

### **options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

A `boolean` indicating whether the credentials exists or not.

## Usage

[snippet=credentials-exist]
