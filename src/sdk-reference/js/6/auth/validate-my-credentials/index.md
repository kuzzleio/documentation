---
layout: sdk.html.hbs
algolia: true
title: validateMyCredentials
description: Validate the current user's credentials for the specified strategy.
algolia: true
---

# validateMyCredentials

Validates the provided credentials against a specified authentication strategy.

This route neither creates nor modifies credentials.

## Arguments

```javascript
validateMyCredentials (strategy, credentials, [options])
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | <pre>string</pre> | Strategy to use
| `credentials` | <pre>string</pre> | New credentials
| `options`  | <pre>object</pre> | Query options


### options

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |


## Resolves

Resolves to `true` if the credentials are valid, `false`otherwise.

## Usage

[snippet=validate-my-credentials]
