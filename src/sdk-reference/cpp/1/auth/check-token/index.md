---
layout: sdk.html.hbs
algolia: true
title: checkToken
description: Checks a JWT Token's validity.
order: 200
---

# checkToken

Checks a JWT Token's validity.

## Signature

```cpp
token_validity* checkToken(const std::string& token);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `token`      | string  | the token   | yes

## Return

A pointer to a token_validity struct which has:

| Name                | Type     | Description                        
| ------------------- | -------- | -----------------------------------
| valid               | bool     | Tell if the token is valid or not
| state               | char\*   | Explain why the token is invalid
| expires_at          | int      | Tells when the token expires

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=check-token]
