---
layout: sdk.html.hbs
algolia: true
title: credentialsExist
description: Checks a jwt token's validity.
---

# credentialsExist

Checks that the current authenticated user has credentials for the specified authentication `<strategy>`.

## Arguments

```javascript
credentialsExist (strategy, options = null);
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `local` | <pre>string</pre> | Strategy to use
| `options` | <pre>object</pre> | Query options

### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |


## Resolve

A `boolean` indicating whether the credentials exists or not.

## Usage

[snippet=credentials-exist]
