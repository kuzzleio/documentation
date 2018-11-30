---
layout: sdk.html.hbs
title: getAllCredentialFields
description: Security:getAllCredentialFields
---
  

# getAllCredentialFields
Fetches a list of accepted fields per authentication strategy.

---

## getAllCredentialFields([options], callback)

| Arguments | Type | Description
|-----------|------|------------
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

---

## Options

| Option | Type | Description | Default
|--------|------|-------------|---------
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