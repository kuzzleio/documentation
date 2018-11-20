---
layout: sdk.html.hbs
algolia: true
title: getCredentials
description: Security:getCredentials
---

  

# getCredentials
Get credential information of user with `kuid` for the specified `strategy`. 

| `strategy` | string | Strategy you want to get credentials from
| `kuid` | string | User's kuid
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

The result is a an object with the credentials.

## Usage

[snippet=get-credentials-1]
> Callback response

```json
{
  "username": "foo", 
  "kuid": "<Kuzzle User Unique Identifier>"
}
```