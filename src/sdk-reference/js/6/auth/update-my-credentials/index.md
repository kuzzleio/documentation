---
layout: sdk.html
algolia: true
title: updateMyCredentials
description: Update the current user's credentials for the specified `<strategy>`.
order: 200
---

# updateMyCredentials

Update the current user's credentials for the specified `<strategy>`. The credentials to send will depend on the authentication plugin and the authentication strategy.

## Signature

```javascript
/**
 * Update the current user's credentials for the specified `<strategy>`.
 * 
 * @param strategy
 * @param credentias
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 * updateMyCredentials (strategy, credentials, options = {});
 */
updateMyCredentials (strategy, credentials, options = {});
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | the strategy to use
| `credentials` | JSON Object | the new credentials
| `options`  | JSON Object | A JSON Object containing the options


###### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

A JSON Object representing the new credentials.

## Usage

[snippet=update-my-credentials]
