---
layout: sdk.html.hbs
algolia: true
title: validateCredentials
description: Security:validateCredentials
---

  

# validateCredentials
>

Validate credentials of user with `kuid` for the specified `strategy`. Resolves to an error if the credentials are invalid.

| `strategy` | string | Strategy you want to create credentials in
| `kuid` | JSON object | User's kuid
| `credentials` | JSON object | The credentials
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns a boolean value if the credentials are valid, otherwise returns an error.

## Usage

[snippet=validate-credentials-1]
> Callback response

```json
true
```