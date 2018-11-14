---
layout: sdk.html.hbs
algolia: true
title: checkToken
description: Kuzzle:checkToken
---
  

# checkToken

[snippet=check-token-1]
> Callback response if the token is valid:

[snippet=check-token-2]

> Callback response if the token is invalid:

[snippet=check-token-3]

Checks the validity of a JSON Web Token.

<aside class="notice">
This method is non-queuable, meaning that during offline mode, it will be discarded and the callback return an error.
</aside>

---

## checkToken(token, callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``token``    | string   | The token to check |
| ``callback`` | function | Callback handling the response |

**Note:** this method sends an unauthenticated API call to Kuzzle, meaning it ignores the JWT Token property, even if it has been set.

---

## Callback Response

Returns a JSON object with a `valid` boolean property.  
If the token is valid, an `expiresAt` property is set with the expiration timestamp. If not, a `state` property is set explaining why the token is invalid.
