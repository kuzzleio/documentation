---
layout: sdk.html.hbs
algolia: true
title: deleteCredentials
description: Security:deleteCredentials
---
  

# deleteCredentials
Delete current user's credentials for the specified `strategy`.
 
---

## deleteCredentials(strategy, kuid, [options], [callback])

| Arguments | Type | Description
|-----------|------|------------
| `strategy` | string | Strategy you want to delete credentials from
| `kuid` | string | User's kuid
| `options` | JSON object | Optional parameters
| `callback`| function | Optional Callback handling the response

---

## Options

| Option | Type | Description | Default
|--------|------|-------------|---------
| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns an object reflecting the query status.

## Usage

[snippet=delete-credentials-1]
> Callback response

```json
{
  "acknowledged": true
}
```