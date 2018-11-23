---
layout: sdk.html.hbs
algolia: true
title: UpdateSelf
description: Updates the current user object in Kuzzle.
algolia: true
---

# UpdateSelf

Updates the current user object in Kuzzle.

## Signature

```go
func (a *Auth) UpdateSelf(data json.RawMessage, options types.QueryOptions) (*security.User, error)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `content` | string | the new credentials
| `options`  | QueryOptions | QueryOptions object containing query options


### **Options**

Additional query options

| Property     | Type | Description                       | Default |
| ---------- | -----| --------------------------------- | ------- |
| `Queuable` | bool | Make this request queuable or not | `true`  |


## Return

A pointer to a security.User object and an error or `nil`


## Usage

[snippet=update-self]
