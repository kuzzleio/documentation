---
layout: sdk.html
algolia: true
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
order: 200
---

# credentialsExist

## Signature

```javascript
/**
 * Check the existence of the specified <strategy>'s credentials for the current user.
 *
 * @param strategy
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 */
credentialsExist (strategy, options = null);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `local` | string | Strategy to use
| `options` | JSON Object | A JSON Object containing the options

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

A boolean.

## Usage

[snippet=credentials-exist]
