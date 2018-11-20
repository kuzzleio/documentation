---
layout: sdk.html.hbs
algolia: true
title: updateCredentials
description: Security:updateCredentials
---

  

# updateCredentials
Update the current user's credentials for the specified <strategy>. The credentials to send depend on the authentication plugin and the strategy.

| `strategy` | string | Strategy you want to create credentials in
| `kuid` | JSON object | User's kuid
| `credentials` | JSON object | The credentials
| `options` | JSON object | Optional parameters
| `callback`| function | Optional callback handling the response

| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns an object with the updated credentials.

## Usage

[snippet=update-credentials-1]
> Callback response

```json
{
  "username": "foo",
  "kuid": "<Kuzzle Unique User Identifier>"
}
```