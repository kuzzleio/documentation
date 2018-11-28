---
layout: sdk.html.hbs
title: CheckToken
description: Checks a JWT Token's validity.
---

# CheckToken

Checks a JWT Token's validity.

## Signature

```go
func (a *Auth) CheckToken(token string) (*TokenValidity, error)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `token` | string | the token    | yes

## Return

A TokenValidity struct which contains:

| Name                | Type    | Description                                                                                                      
| ------------------- | ------- | -----------------------------------
| Valid               | boolean    | Tell if the token is valid or not
| State               | string  | Explain why the token is invalid
| Expires_at          | int     | Tells when the token expires

## Usage

[snippet=check-token]
