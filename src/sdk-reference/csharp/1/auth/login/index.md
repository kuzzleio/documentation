---
layout: sdk.html.hbs
title: login
description: Authenticate a user
---

# login

Authenticates a user.z


## Signature

```csharp
public string login(string strategy, string credentials);

public string login(string strategy, string credentials, int expiresIn);

```

## Arguments

| Arguments     | Type    | Description | 
|---------------|---------|----------------------------------|
| `strategy`    | <pre>string</pre>  | Strategy to use  |
| `credentials` | <pre>string</pre>  | JSON string representing the credentials             |
| `expiresIn`   | <pre>int</pre>     | Expiration time in milliseconds  |

### strategy

The name of the authentication [strategy]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments]({{ site_base_path}}guide/1/kuzzle-depth/authentication/## Return

Returns the encrypted JSON Web Token.  
Once `auth:login` has been called, the returned JWT is stored by the SDK and used for all the subsequent API call, ensuring they are properly authenticated.

## Return

Returns the encrypted JSON Web Token.  
Once `auth:login` has been called, the returned JWT is stored by the SDK and used for all the subsequent API call, ensuring they are properly authenticated.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=login]
