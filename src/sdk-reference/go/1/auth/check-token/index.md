---
layout: sdk.html
algolia: true
title: checkToken
description: Checks a JWT Token's validity.
order: 200
---

# checkToken

Checks a JWT Token's validity.

## Signature

```go
func (a *Auth) CheckToken(token string) (*TokenValidity, error)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``token`` | string | the token    | yes

## Return

A pointer to a token_validity struct which has:

| Name                | Type    | Description                                                                                                      
| ------------------- | ------- | -----------------------------------
| valid               | bool    | Tell if the token is valid or not
| state               | string  | Explain why the token is invalid
| expires_at          | int     | Tells when the token expires

## Usage

[snippet=check-token]
