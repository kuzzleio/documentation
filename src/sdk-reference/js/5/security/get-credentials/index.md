---
layout: sdk.html.hbs
algolia: true
title: getCredentials
description: Security:getCredentials
---
  

# getCredentials

[snippet=get-credentials-1]
> Callback response

[snippet=get-credentials-2]

Get credential information of user with `kuid` for the specified `strategy`. 

---

## getCredentials(strategy, kuid, [options], callback)

| Arguments | Type | Description
|-----------|------|------------
| `strategy` | string | Strategy you want to get credentials from
| `kuid` | string | User's kuid
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

---

## Options

| Option | Type | Description | Default
|--------|------|-------------|---------
| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

The result is a an object with the credentials.
