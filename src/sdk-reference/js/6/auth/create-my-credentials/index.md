---
layout: sdk.html.hbs
algolia: true
title: createMyCredentials
description: Create the current user's credentials for the specified `<strategy>`.
order: 200
---

# createMyCredentials

Create the current user's credentials for the specified `<strategy>`.

## Signature

```javascript
/**
 * Create credentials of the specified <strategy> for the current user.
 *
 * @param credentials
 * @param strategy
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 */
createMyCredentials (strategy, credentials, options = null)
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
| `queuable` | bool | Make this request queuable or not | `true`  |


## Resolve

A JSON Object representing the new credentials.

## Usage

[snippet=create-my-credentials]
