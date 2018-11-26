---
layout: sdk.html.hbs
algolia: true
title: getMyCredentials
description: Kuzzle:getMyCredentials
algolia: true
---
  

# getMyCredentials
Get [credential information]({{ site_base_path }}guide/essentials/user-authentication/#user-credentials) for the current user.

---

## getMyCredentials(strategy, [options], callback)

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

Returns an object with the credentials for the provided authentication strategy.

## Usage

[snippet=get-my-credentials-1]
> Callback response

```json
{
  "username": "foo", 
  "kuid": "<Kuzzle Unique User Identifier>"
}
```