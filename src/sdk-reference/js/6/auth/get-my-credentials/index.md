---
layout: sdk.html.hbs
algolia: true
title: getMyCredentials
description:
order: 200
---

# getMyCredentials

Returns the current user's credential information for the specified `<strategy>`.  
The data returned will depend on the specified strategy. The result can be an empty object.

## Signature

```javascript
/**
 * Get credential information of the specified <strategy> for the current user.
 *
 * @param {object} [options]
 * @returns {Promise<object>}
 */
getMyCredentials(strategy, options = null)
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

An object representing the credentials for the provided authentication strategy.  
The content depends on the authentication strategy.  

## Usage

[snippet=get-my-credentials]
