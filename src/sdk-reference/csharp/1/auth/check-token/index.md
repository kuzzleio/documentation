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

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `token`      | string  | the token   | yes

## Return

A TokenValidity class which has:

| Name                | Type     | Description                        
| ------------------- | -------- | -----------------------------------
| valid               | bool     | Tell if the token is valid or not
| state               | string   | Explain why the token is invalid
| expires_at          | int      | Tells when the token expires

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

