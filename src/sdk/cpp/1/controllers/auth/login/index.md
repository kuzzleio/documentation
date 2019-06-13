---
code: true
type: page
title: login
description: Authenticate a user
---

# login

Authenticates a user.

If this action is successful, all further requests emitted by this SDK instance will be in the name of the authenticated user, until either the authenticated token expires, the [logout](/sdk/cpp/1/controllers/auth/logout) action is called, or the [jwt](/sdk/cpp/1/core-classes/kuzzle/introduction/) property is manually unset.

## Arguments

```cpp
std::string login(const std::string& strategy, const std::string& credentials);

std::string login(
    const std::string& strategy,
    const std::string& credentials,
    int expiresIn);
```

<br/>

| Arguments     | Type                          | Description                               |
| ------------- | ----------------------------- | ----------------------------------------- |
| `strategy`    | <pre>const std::string&</pre> | Strategy to use                           |
| `credentials` | <pre>const std::string&</pre> |  JSON string representing the credentials |
| `expiresIn`   | <pre>int</pre>                |  Expiration time, in milliseconds          |

#### strategy

The name of the authentication [strategy](/core/1/guides/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments](/core/1/guides/kuzzle-depth/authentication/#authentication) may be required.
The API request example on this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin](/core/1/plugins/guides/strategies/overview/) documentation to get the list of additional arguments to provide.


### expiresIn

The default value for the `expiresIn` option is defined at server level, in Kuzzle's [configuration file](/core/1/guides/essentials/configuration).


## Return

Returns the encrypted JSON Web Token.
Once `auth:login` has been called, the returned JWT is stored by the SDK and used for all the subsequent API call, ensuring they are properly authenticated.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling/).

## Usage

<<< ./snippets/login.cpp
