---
layout: sdk.html.hbs
algolia: true
title: createMyCredentials
description: Create the current user's credentials for the specified `<strategy>`.
---

# createMyCredentials

Creates new credentials for the specified `<strategy>` for the current user.

## Signature

```javascript
/**
 * @param {string} strategy
 * @param {object} credentials
 * @param {object} [options]
 * @returns {Promise<object>}
 */
createMyCredentials (strategy, credentials, options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | Strategy to use
| `credentials` | object | New credentials
| `options`  | object | Query options


### **options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean| Make this request queuable or not | `true`  |


## Resolve

An `object` representing the new credentials.  
The content depends on the authentication strategy.  

## Usage

[snippet=create-my-credentials]
