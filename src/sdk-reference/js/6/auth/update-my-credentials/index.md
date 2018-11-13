---
layout: sdk.html.hbs
algolia: true
title: updateMyCredentials
description: Update the current user's credentials for the specified strategy.
---

# updateMyCredentials

Updates the credentials of the currently logged in user for the specified strategy.

## Arguments

```javascript
updateMyCredentials (strategy, credentials, [options])
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | <pre>string</pre> | Strategy to use
| `credentials` | <pre>object</pre> | New credentials
| `options`  | <pre>object</pre> | Query options


### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |


## Resolves

An `object` representing the new credentials.
The content depends on the authentication strategy.

## Usage

[snippet=update-my-credentials]
