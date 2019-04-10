---
layout: sdk.html.hbs
title: checkToken
description: Checks a JWT's validity.
---

# checkToken

Checks a token validity.

<br/>

```javascript
checkToken (token)
```

<br/>

| Property    | Type    | Description
|--------------|---------|-------------
| ``token`` | <pre>string</pre> | JWT token

## Resolves

An `object` representing the token validity status

| Name                | Type    | Description
| ------------------- | ------- | -----------------------------------
| `valid`               | <pre>boolean</pre> | Tell if the token is valid or not
| `state`               | <pre>string</pre> | Explain why the token is invalid
| `expires_at`          | <pre>number</pre> | Token expiration timestamp |

## Usage

[snippet=check-token]
