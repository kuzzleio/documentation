---
layout: sdk.html.hbs
algolia: true
title: CredentialsExist
description: Check that the current user has credentials for the specified strategy
algolia: true
---

# CredentialsExist

Check that the current user has credentials for the specified strategy.

## Signature

```go
func (a *Auth) CredentialsExist(strategy string, options types.QueryOptions) (bool, error)
```

## Arguments

| Arguments  | Type             | Description                                             | Required |
| ---------- | ---------------- | ------------------------------------------------------- | -------- |
| `strategy` | string      | Strategy to use                                         | yes      |
| `options` | QueryOptions | A structure containing query options | yes       |

### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |


## Return

True if exists, false if not.

## Usage

[snippet=credentials-exist]
