---
layout: sdk.html.hbs
algolia: true
title: login
description:
order: 200
---

# login

Authenticates a user.

## Signature

```javascript
/**
 * @param {string} strategy
 * @param {object} credentials
 * @param {string} expiresIn
 * @returns {Promise<object>}
 */
login(strategy, credentials, expiresIn)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``strategy`` | string | Name of the strategy to use    |
| ``credentials`` | string | Credentials for the strategy |
| ``expiresIn`` | string | Expiration time in [ms library](https://www.npmjs.com/package/ms) format. (e.g. `2h`) |

#### ***strategy***

The name of the authentication [strategy]({{ site_base_path }}guide/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `<strategy>`, additional [credential arguments]({{ site_base_path}}guide/kuzzle-depth/authentication/#authentication) may be required.  
The API request example in this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin]({{ site_base_path }}plugins-reference/plugins-features/adding-authentication-strategy/) documentation to get the list of additional arguments to provide.

## Resolve

Return the encrypted JSON Web Token.

## Usage

[snippet=login]
