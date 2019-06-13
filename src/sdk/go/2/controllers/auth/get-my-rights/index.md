---
code: true
type: page
title: GetMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
---

# GetMyRights

Returns the rights for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Arguments

```go
func (a *Auth) GetMyRights(options types.QueryOptions) ([]*types.UserRights, error)
```

| Arguments | Type         | Description                                  | Required |
| --------- | ------------ | -------------------------------------------- | -------- |
| `options` | QueryOptions | QueryOptions object containing query options | yes      |

### **Options**

Additional query options

| Property   | Type | Description                       | Default |
| ---------- | ---- | --------------------------------- | ------- |
| `Queuable` | bool | Make this request queuable or not | `true`  |

## Return

A pointer to an array of UserRight object containing:

| Property      | Type   | Description                               |
| ------------- | ------ | ----------------------------------------- |
| `Controller`  | string | Controller on wich the rights are applied |
| `Action`      | string | Action on wich the rights are applied     |
| `Index`       | string | Index on wich the rights are applied      |
|  `Collection` | string | Collection on wich the rights are applied |
|  `Value`      | string | Rights (`allowed|denied|conditional`)     |

and an error or `nil`

## Usage

<<< ./snippets/get-my-rights.go
