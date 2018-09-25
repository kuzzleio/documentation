---
layout: sdk.html
algolia: true
title: DeleteMyCredentials
description: Delete the current user's credentials for the specified strategy
order: 200
---

# DeleteMyCredentials

Delete the current user's credentials for the specified `<strategy>`. If the credentials that generated the current JWT are removed, the user will remain logged in until he logs out or his session expires, after that they will no longer be able to log in with the deleted credentials.

## Signature

```go
func (a *Auth) DeleteMyCredentials(strategy string, options types.QueryOptions) error
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `strategy` | string | the strategy to use    | yes
| `options`  | QueryOptions    | QueryOptions object containing query options | no       |


### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return an error or `nil` if the credentials are successfully deleted

## Usage

[snippet=delete-my-credentials]
