---
layout: sdk.html.hbs
algolia: true
title: validateMyCredentials
description: Validate the current user's credentials for the specified `<strategy>`.
---

# validateMyCredentials

Validates the provided `<credentials>` against a specified authentication `<strategy>`.

This route neither creates nor modifies credentials.

## Arguments

```javascript
validateMyCredentials (strategy, credentials, options = null)
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | <pre>string</pre> | Strategy to use
| `credentials` | <pre>string</pre> | New credentials
| `options`  | <pre>object</pre> | Query options


### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |


## Return

A `boolean` indicating the credentials validity.

## Usage

[snippet=validate-my-credentials]
