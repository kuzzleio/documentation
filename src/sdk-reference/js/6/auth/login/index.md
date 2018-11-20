---
layout: sdk.html.hbs
algolia: true
title: login
description: Authenticate a user
---


# login

Authenticates a user.

## Arguments

```javascript
login (strategy, credentials, expiresIn)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``strategy`` | <pre>string</pre> | Name of the strategy to use    |
| ``credentials`` | <pre>string</pre> | Credentials for the strategy |
| ``expiresIn`` | <pre>string</pre> | Expiration time in [ms library](https://www.npmjs.com/package/ms) format. (e.g. `2h`) |

#### strategy

The name of the authentication [strategy]({{ site_base_path }}guide/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication strategy, additional [credential arguments]({{ site_base_path}}guide/kuzzle-depth/authentication/#authentication) may be required.
The API request example in this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin]({{ site_base_path }}plugins/1/essentials/strategies/) documentation to get the list of additional arguments to provide.

## Resolves

The **login** action returns an encrypted JSON Web Token, that must then be sent in the [requests headers]({{ site_base_path }}api/1/query-syntax/).

## Usage

[snippet=login]
