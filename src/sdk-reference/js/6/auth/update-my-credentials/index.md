---
layout: sdk.html.hbs
algolia: true
title: updateMyCredentials
description: Update the current user's credentials for the specified `<strategy>`.
order: 200
---

# updateMyCredentials

Update the current user's credentials for the specified `<strategy>`.  
The credentials to send will depend on the authentication plugin and the authentication strategy.

## Signature

```javascript
/**
 * Update the current user's credentials for the specified `<strategy>`.
 *
 * @param {string} strategy
 * @param {object} credentials
 * @param {object} [options]
 * @returns {Promise<object>}
 * updateMyCredentials (strategy, credentials, options = null);
 */
updateMyCredentials (strategy, credentials, options = null);
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
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

A object representing the new credentials.  
The content depends on the authentication strategy.  

## Usage

[snippet=update-my-credentials]
