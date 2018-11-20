---
layout: sdk.html.hbs
algolia: true
title: createMyCredentials
description: Kuzzle:createMyCredentials
---

  

# createMyCredentials
Create the current user's credentials for the specified strategy. The credentials required will depend on the authentication plugin and strategy.

| `strategy` | string | Strategy you want to create credentials for
| `credentials` | JSON object | The credentials
| `options` | JSON object | Optional parameters
| `callback`| function | Optional callback handling the response

| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns an object with the created credentials.

## Usage

[snippet=create-my-credentials-1]
