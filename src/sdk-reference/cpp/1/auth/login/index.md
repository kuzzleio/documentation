---
layout: sdk.html.hbs
title: login
description: Authenticate a user
---

# login

Authenticates a user.


## Signature

```cpp
std::string login(const std::string& strategy, const std::string& credentials, int expiresIn);
std::string login(const std::string& strategy, const std::string& credentials);
```


## Arguments

| Arguments     | Type    | Description | 
|---------------|---------|----------------------------------|
| `strategy`    | <pre>const std::string&</pre>  | Strategy to use  |
| `credentials` | <pre>const std::string&</pre>  | JSON string representing the credentials             |
| `expiresIn`   | <pre>int</pre>     | Expiration time in milliseconds  |

#### ***strategy***

The name of the authentication [strategy]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments]({{ site_base_path}}guide/1/kuzzle-depth/authentication/#authentication) may be required.  
The API request example in this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin]({{ site_base_path }}plugins/1/essentials/strategies/) documentation to get the list of additional arguments to provide.

## Return

Returns the encrypted JSON Web Token.  
Once `auth:login` is used, the returned JWT will stored by the SDK and used for all the following actions, ensuring they will be authenticated.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=login]
