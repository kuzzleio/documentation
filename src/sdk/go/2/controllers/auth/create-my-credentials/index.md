---
code: true
type: page
title: CreateMyCredentials
description: Create the current user's credentials for the specified `<strategy>`.
---

# CreateMyCredentials

Create the current user's credentials for the specified `<strategy>`.

## Arguments

```go
func (a *Auth) CreateMyCredentials(strategy string, credentials json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
```

| Arguments     | Type            | Description                                  |
| ------------- | --------------- | -------------------------------------------- |
| `strategy`    | string          | the strategy to use                          |
| `credentials` | json.RawMessage | the new credentials                          |
| `options`     | QueryOptions    | QueryOptions object containing query options |

### **Options**

Additional query options

| Property   | Type | Description                       | Default |
| ---------- | ---- | --------------------------------- | ------- |
| `Queuable` | bool | Make this request queuable or not | `true`  |

## Return

A JSON representing the new credentials and an error or `nil`.

## Usage

<<< ./snippets/create-my-credentials.go
