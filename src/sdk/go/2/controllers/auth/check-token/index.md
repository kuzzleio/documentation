---
code: true
type: page
title: CheckToken
description: Checks the validity of an authentication token.
---

# CheckToken

Checks the validity of an authentication token.

## Arguments

```go
func (a *Auth) CheckToken(token string) (*TokenValidity, error)
```

| Arguments | Type   | Description | Required |
| --------- | ------ | ----------- | -------- |
| `token`   | string | the token   | yes      |

## Return

A TokenValidity struct which contains:

| Name        | Type    | Description                       |
| ----------- | ------- | --------------------------------- |
| Valid       | boolean | Tell if the token is valid or not |
| State       |  string | Explain why the token is invalid  |
|  `Expires_at` | <pre>int</pre>     | Token expiration timestamp     |

## Usage

<<< ./snippets/check-token.go
