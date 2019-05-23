---
code: true
type: page
title: login
description: Authenticate a user
---

# login

Authenticates a user.

## Signature

```cpp
std::string login(const std::string& strategy, const std::string& credentials);

std::string login(
    const std::string& strategy,
    const std::string& credentials,
    int expiresIn);
```

## Arguments

| Arguments     | Type                          | Description                               |
| ------------- | ----------------------------- | ----------------------------------------- |
| `strategy`    | <pre>const std::string&</pre> | Strategy to use                           |
| `credentials` | <pre>const std::string&</pre> |  JSON string representing the credentials |
| `expiresIn`   | <pre>int</pre>                |  Expiration time in milliseconds          |

#### **_strategy_**

The name of the authentication [strategy](/core/1/guide/guides/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments](/core/1/guide/guides/kuzzle-depth/authentication/#authentication) may be required.  
The API request example on this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin](/core/1/plugins/essentials/strategies/) documentation to get the list of additional arguments to provide.

## Return

Returns the encrypted JSON Web Token.  
Once `auth:login` has been called, the returned JWT is stored by the SDK and used for all the subsequent API call, ensuring they are properly authenticated.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/error-handling).

## Usage

<<< ./snippets/login.cpp
