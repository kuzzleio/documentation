---
layout: sdk.html.hbs
algolia: true
title: validateMyCredentials
description: Validate the current user's credentials for the specified `<strategy>`.
order: 200
---

# validateMyCredentials

Validate the current user's credentials for the specified `<strategy>`. The `result` field is `true` if the provided credentials are valid; otherwise an error is triggered.  
This route does not actually create or modify the user credentials. The credentials to send will depend on the authentication plugin and authentication strategy.

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
| `strategy` | string | the strategy to use
| `credentials` | string | the new credentials
| `options`  | object | Query options


### **options**

Additional query options

| Property     | Type    | Description                    | Default |
| ---------- | ------- | ------------------------------ | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Return

A boolean

## Usage

[snippet=validate-my-credentials]
