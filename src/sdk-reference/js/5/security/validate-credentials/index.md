---
layout: sdk.html.hbs
algolia: true
title: validateCredentials
description: Security:validateCredentials
---
  

# validateCredentials

[snippet=validate-credentials-1]
>> Callback response

[snippet=validate-credentials-2]

Validate credentials of user with `kuid` for the specified `strategy`. Resolves to an error if the credentials are invalid.

---

## validateCredentials(strategy, credentials, [options], callback)

| Arguments | Type | Description
|-----------|------|------------
| `strategy` | string | Strategy you want to create credentials in
| `kuid` | JSON object | User's kuid
| `credentials` | JSON object | The credentials
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

---

## Options

| Option | Type | Description | Default
|--------|------|-------------|---------
| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns a boolean value if the credentials are valid, otherwise returns an error.
