---
layout: sdk.html.hbs
algolia: true
title: login
description: Kuzzle:login
---

  

# login
Login a user using a specified strategy and their credentials.

If the Kuzzle response contains a JWT Token, the Kuzzle SDK token is set and the `loginAttempt` event is fired immediately with the following object:
`{ success: true }`
This is the case, for instance, with the `local` authentication strategy.

If the request succeeds but there is no token, then it means that the chosen strategy is a two-steps authentication method, such as the OAUTH strategy. In that case, the `loginAttempt` event is **not** fired. To complete the login, the `setJwtToken` method must be called either with a token or with an appropriate Kuzzle response.

If the login attempt fails, the `loginAttempt` event is fired with the following response:  
`{ success: false, error: 'error message' }`

<aside class="notice">
This method is non-queuable, meaning that during offline mode, it will be discarded and the callback will be called with an error. <a href="{{ site_base_path }}guide/essentials/user-authentication/#local-strategy">Learn more.</a>
</aside>


## Callback Response

Returns a JSON object containing the Kuzzle response.

## Usage

[snippet=login-1]
