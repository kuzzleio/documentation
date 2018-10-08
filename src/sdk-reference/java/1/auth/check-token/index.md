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

```java
TokenValidity checkToken(java.lang.String);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``token`` | java.lang.String | the token    | yes

## Return

A TokenValidity object which has:

| Name                | Type    | Description                                                                                                      
| ------------------- | ------- | -----------------------------------
| valid               | boolean | Tell if the token is valid or not
| state               | java.lang.String  | Explain why the token is invalid
| expires_at          | int     | Tells when the token expires

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=check-token]
