---
layout: sdk.html.hbs
algolia: true
title: getMyCredentials
description: Returns the current user's credential information for the specified `<strategy>`.
---

# getMyCredentials

Returns credential information for the currently logged in user.

The data returned will depend on the specified authentication `<strategy>`, and they should not include any sensitive information.

The result can be an empty object.

## Signature

```javascript
/**
 * @param {string} strategy
 * @param {object} [options]
 * @returns {Promise<object>}
 */
getMyCredentials (strategy, options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | Strategy to use
| `options` | object | Query options


### **options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

An `object` representing the credentials for the provided authentication strategy.  
The content depends on the authentication strategy.  

## Usage

[snippet=get-my-credentials]
