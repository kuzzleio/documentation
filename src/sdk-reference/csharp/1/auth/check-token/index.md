---
layout: sdk.html.hbs
title: checkToken
description: Checks a JWT Token's validity.
---

# checkToken

Checks a JWT Token's validity.

## Signature

```csharp
public TokenValidity checkToken(string token);

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `token`      | <pre>string</pre>  | JWT token   |

## Return

A TokenValidity class which has:

| Name                | Type     | Description                        
| ------------------- | -------- | -----------------------------------
| valid               | <pre>bool</pre>     | Token validity
| state               | <pre>string</pre>   | Explain why the token is invalid
| expires_at          | <pre>long</pre>      | Token expiration timestamp

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

