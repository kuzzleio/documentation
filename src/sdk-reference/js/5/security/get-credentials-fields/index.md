---
layout: sdk.html.hbs
algolia: true
title: getCredentialsFields
description: Security:getCredentialsFields
---

  

# getCredentialFields
Get credential information for the specified `strategy`.

| `strategy` | string | Strategy you want to get credentials from
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

The result is a an array of credential fields.

## Usage

[snippet=get-credentials-fields-1]
> Callback response:

```json
[
  "kuid",
  "username"
]
```