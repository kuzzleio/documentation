---
layout: sdk.html.hbs
title: login
description: Authenticate a user
---

# login

Authenticates a user.

If this action is successful, all further requests emitted by this SDK instance will be in the name of the authenticated user, until either the authenticated token expires, the [logout]({{ site_base_path }}sdk-reference/java/1/auth/logout) action is called, or the [jwt]({{ site_base_path }}sdk-reference/java/1/kuzzle/introduction/#properties) property is manually unset.

## Arguments

```java
String login(String strategy, String credentials, int expiresIn);
String login(String strategy, String credentials);
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `strategy` | <pre>String</pre> | Name of the strategy to use |
| `credentials` | <pre>String</pre> | Credentials for that strategy |
| `expiresIn` | <pre>int</pre> | Token expiration time, in milliseconds |

### strategy

The name of the authentication [strategy]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments]({{ site_base_path}}guide/1/kuzzle-depth/authentication/#authentication) may be required.  
The API request example in this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin]({{ site_base_path }}plugins/1/essentials/strategies/) documentation to get the list of additional arguments to provide.

### expiresIn

The default value for the `expiresIn` option is defined at server level, in Kuzzle's [configuration file]({{ site_base_map }}guide/1/essentials/configuration).

## Return

The **login** action returns an encrypted JSON Web Token, that must then be sent in the [requests headers]({{ site_base_path }}api/1/essentials/query-syntax/).

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=login]
