---
layout: sdk.html
algolia: true
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
order: 200
---

# credentialsExist

## Signature

```go
func (a *Auth) CredentialsExist(strategy string, options types.QueryOptions) (bool, error)
```

## Arguments

| Arguments  | Type             | Description                                             | Required |
| ---------- | ---------------- | ------------------------------------------------------- | -------- |
| `strategy` | string      | Strategy to use                                         | yes      |
| `options` | QueryOptions | A structure containing query options. | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Return

True if exists, false if not.

## Usage

[snippet=credentials-exist]
