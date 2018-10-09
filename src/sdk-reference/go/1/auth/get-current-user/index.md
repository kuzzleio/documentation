---
layout: sdk.html
algolia: true
title: GetCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`
order: 200
---

# GetCurrentUser

Returns the profile object for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```go
func (a *Auth) GetCurrentUser() (*security.User, error)
```

## Return

A pointer to security.User object containing:

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `Id` | string | The user ID |
| `Content` | map[string]interface{} | The user content |
| `ProfileIds` | []string | An array containing the profile ids |

## Usage

[snippet=get-current-user]
