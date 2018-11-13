---
layout: sdk.html.hbs
algolia: true
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
---

# credentialsExist

Checks that the current authenticated user has credentials for the specified authentication `<strategy>`.

## Arguments

```javascript
credentialsExist (strategy, [options]);
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `local` | <pre>string</pre> | Strategy to use
| `options` | <pre>object</pre> | Query options

### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |


## Resolves

A `boolean` indicating whether the credentials exists or not.

## Usage

[snippet=credentials-exist]
