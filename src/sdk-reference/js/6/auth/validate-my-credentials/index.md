---
layout: sdk.html.hbs
algolia: true
title: validateMyCredentials
description: Validate the current user's credentials for the specified `<strategy>`.
order: 200
---

# validateMyCredentials

Validates the provided `<credentials>` against a specified authentication `<strategy>`.

This route neither creates nor modifies credentials.

## Signature

```javascript
/**
 * @param {string} strategy
 * @param {object} credentials
 * @param {object} [options]
 * @returns a boolean
 */
validateMyCredentials (strategy, credentials, options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | Strategy to use
| `credentials` | string | New credentials
| `options`  | object | Query options


### **options**

Additional query options

| Property     | Type    | Description                    | Default |
| ---------- | ------- | ------------------------------ | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Return

A `boolean` indicating the credentials validity.

## Usage

[snippet=validate-my-credentials]
