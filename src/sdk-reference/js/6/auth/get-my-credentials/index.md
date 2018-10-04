---
layout: sdk.html
algolia: true
title: getMyCredentials
description:
order: 200
---

# getMyCredentials

Returns the current user's credential information for the specified `<strategy>`. The data returned will depend on the specified strategy. The result can be an empty object.

## Signature

```javascript
/**
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 */
getMyCredentials(strategy, options = {})
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `strategy` | string | the strategy to use    | yes
| `options` | JSON Object | A structure containing query options. | no


### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

A JSON with the credentials for the provided authentication strategy and an error or nil.

## Usage

[snippet=get-my-credentials]
