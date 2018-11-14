---
layout: sdk.html.hbs
algolia: true
title: getCredentialsFields
description: Security:getCredentialsFields
---
  

# getCredentialFields

[snippet=get-credentials-fields-1]
> Callback response:

[snippet=get-credentials-fields-2]

Get credential information for the specified `strategy`.

---

## getCredentialFields(strategy, [options], callback)

| Arguments | Type | Description
|-----------|------|------------
| `strategy` | string | Strategy you want to get credentials from
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

---

## Options

| Option | Type | Description | Default
|--------|------|-------------|---------
| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

The result is a an array of credential fields.
