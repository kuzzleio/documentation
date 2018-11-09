---
layout: sdk.html.hbs
algolia: true
title: CreateMyCredentials
description: Create the current user's credentials for the specified `<strategy>`.
---

# CreateMyCredentials

Create the current user's credentials for the specified `<strategy>`.

## Signature

```go
func (a *Auth) CreateMyCredentials(strategy string, credentials json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
```

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | the strategy to use
| `credentials` | json.RawMessage | the new credentials
| `options`  | QueryOptions    | QueryOptions object containing query options


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | bool | Make this request queuable or not | `true`  |


## Return

A JSON representing the new credentials and an error or `nil`.


## Usage

[snippet=create-my-credentials]
