---
layout: sdk.html.hbs
algolia: true
title: getMyCredentials
description: Returns the current user's credential information for the specified strategy.
---

# getMyCredentials

Returns credentials information for the currently logged in user.

The data returned depends on the specified authentication `<strategy>`, and they should not include any sensitive information.

The result can be an empty object.

## Arguments

```javascript
getMyCredentials (strategy, [options])
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | <pre>string</pre> | Strategy to use
| `options` | <pre>object</pre> | Query options


### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |


## Resolves

An `object` representing the credentials for the provided authentication strategy.
The content depends on the authentication strategy.

## Usage

[snippet=get-my-credentials]
