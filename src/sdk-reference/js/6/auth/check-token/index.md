---
layout: sdk.html.hbs
algolia: true
title: checkToken
description:
order: 200
---

# checkToken

## Signature

```javascript
/**
 * Checks whether a given jwt token still represents a valid session in Kuzzle.
 *
 * @param  {string}   token     The jwt token to check
 * @return {Promise|*|PromiseLike<T>|Promise<T>}
 */
checkToken(token)
```

## Arguments

| Property    | Type    | Description
|--------------|---------|-------------
| ``token`` | string | the token

## Resolve

An object representing the token validity status

| Name                | Type    | Description                                                                                                      
| ------------------- | ------- | -----------------------------------
| valid               | bool    | Tell if the token is valid or not
| state               | string  | Explain why the token is invalid
| expires_at          | number  | Tells when the token expires

## Usage

[snippet=check-token]
