---
layout: sdk.html.hbs
title: Login
description: Authenticate a user
---

# Login

Authenticates a user.

If this action is successful, all further requests emitted by this SDK instance will be in the name of the authenticated user, until either the authenticated token expires, the [logout]({{ site_base_path }}sdk-reference/go/1/auth/logout) action is called, or the [jwt]({{ site_base_path }}sdk-reference/go/1/kuzzle/introduction/#properties) property is manually unset.

## Arguments

```go
func (a *Auth) Login(
  strategy string, 
  credentials json.RawMessage, 
  expiresIn *int) (string, error)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `strategy` | <pre>string</pre> | Name of the strategy to use    |
| `credentials` | <pre>json.RawMessage</pre> | Credentials for that strategy |
| `expiresIn` | int | Expiration time, in milliseconds |

### strategy

The name of the authentication [strategy]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments]({{ site_base_path}}guide/1/kuzzle-depth/authentication/#authentication) may be required.  
The API request example in this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin]({{ site_base_path }}plugins/1/essentials/strategies/) documentation to get the list of additional arguments to provide.

### expiresIn

The default value for the `expiresIn` option is defined at server level, in Kuzzle's [configuration file]({{ site_base_map }}guide/1/essentials/configuration).

## Return

The **login** action returns an encrypted JSON Web Token, that must then be sent in the [requests headers]({{ site_base_path }}api/1/essentials/query-syntax/).

## Usage

[snippet=login]
