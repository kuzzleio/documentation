---
layout: sdk.html.hbs
algolia: true
title: getAllCredentialFields
description: Security:getAllCredentialFields
---

  

# getAllCredentialFields
Fetches a list of accepted fields per authentication strategy.

| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns an object with the credential fields.

## Usage

[snippet=get-all-credential-fields-1]
> Callback response:

```json
{
  "local": [
    "kuid",
    "username"
  ]
}
```