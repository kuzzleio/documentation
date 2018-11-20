---
layout: sdk.html.hbs
algolia: true
title: updateMyCredentials
description: Kuzzle:updateMyCredentials
---

  

# updateMyCredentials
Update current user credentials for the specified `strategy`. The credentials to send depend on the authentication plugin and the strategy.

| `strategy` | string | Strategy you want to create credentials in
| `credentials` | JSON object | The credentials
| `options` | JSON object | Optional parameters
| `callback`| function | Optional callback handling the response

| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback response

Returns an object reflecting the updated credentials.

## Usage

[snippet=update-my-credentials-1]
