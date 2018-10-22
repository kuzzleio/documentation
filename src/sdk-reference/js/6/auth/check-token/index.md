---
layout: sdk.html.hbs
algolia: true
title: checkToken
description:
order: 200
---

# checkToken

Checks a `<token>` validity.

This API route does not require the caller to be logged in.

## Signature

```javascript
/**
 * @param  {string} token
 * @return {Promise<object>}
 */
checkToken(token)
```

## Arguments

| Property    | Type    | Description
|--------------|---------|-------------
| ``token`` | string | JWT token

## Resolve

An object representing the token validity status

| Name                | Type    | Description                        
| ------------------- | ------- | -----------------------------------
| valid               | boolean | Tell if the token is valid or not
| state               | string  | Explain why the token is invalid
| expires_at          | number  | Tells when the token expires

## Usage

[snippet=check-token]
