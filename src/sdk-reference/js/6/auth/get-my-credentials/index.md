---
layout: sdk.html.hbs
algolia: true
title: getMyCredentials
---

# getMyCredentials

Returns the current user's credential information for the specified `<strategy>`. The data returned will depend on the specified strategy. The result can be an empty object.

## Signature

```javascript
/**
 * Get credential information of the specified <strategy> for the current user.
 * 
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 */
getMyCredentials(strategy, options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | the strategy to use
| `options` | JSON Object | A JSON Object containing the options


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

A JSON with the credentials for the provided authentication strategy and an error or nil.

## Usage

[snippet=get-my-credentials]
