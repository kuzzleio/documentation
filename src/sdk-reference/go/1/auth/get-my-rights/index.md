---
layout: sdk.html
algolia: true
title: GetMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
order: 200
---

# GetMyRights

Returns the rights for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```go
func (a *Auth) GetMyRights(options types.QueryOptions) ([]*types.UserRights, error)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `options`  | QueryOptions    | QueryOptions object containing query options | yes

###### **Options**

Additional query options

| Option     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `Queuable` | bool | Make this request queuable or not | `true`

## Return

A pointer to an array of UserRight object containing:


| Option     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `Controller` | string | The controller on wich the rights are applied |
| `Action` | string | The action on wich the rights are applied |
| `Index` | string | The index on wich the rights are applied |
| `Collection` | string | The collection on wich the rights are applied |
| `Value` | string | The rights |

and an error or `nil`

## Usage

[snippet=get-my-rights]
