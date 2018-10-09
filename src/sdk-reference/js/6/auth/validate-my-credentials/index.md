---
layout: sdk.html.hbs
algolia: true
title: validateMyCredentials
description: Validate the current user's credentials for the specified `<strategy>`.
order: 200
---

# validateMyCredentials

Validate the current user's credentials for the specified `<strategy>`. The `result` field is `true` if the provided credentials are valid; otherwise an error is triggered. This route does not actually create or modify the user credentials. The credentials to send will depend on the authentication plugin and authentication strategy.

## Signature

```javascript
/**
 * Validate credentials of the specified <strategy> for the current user.
 *
 * @param strategy
 * @param credentials
 * @param options
 * @returns a boolean
 */
validateMyCredentials (strategy, credentials, options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | the strategy to use
| `credentials` | string | the new credentials
| `options`  | JSON Object | A JSON Object containing the options


### **Options**

Additional query options

| Property     | Type    | Description                    | Default |
| ---------- | ------- | ------------------------------ | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |


## Return

A boolean

## Usage

[snippet=validate-my-credentials]
