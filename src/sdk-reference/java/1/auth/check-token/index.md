---
layout: sdk.html.hbs
title: checkToken
description: Checks a JWT Token's validity.
---

# checkToken

Checks a JWT Token's validity.

## Signature

```java
TokenValidity checkToken(String);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| ``token`` | String | the token

## Return

A TokenValidity object which has:

| Name                | Type    | Description
| ------------------- | ------- | -----------------------------------
| valid               | boolean | Tell if the token is valid or not
| state               | String  | Explain why the token is invalid
| expires_at          | BigInteger     | Tells when the token expires

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=check-token]
