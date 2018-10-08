---
layout: sdk.html.hbs
algolia: true
title: UpdateMyCredentials
description: Update the current user's credentials for the specified `<strategy>`.
order: 200
---

# UpdateMyCredentials

Update the current user's credentials for the specified `<strategy>`. The credentials to send will depend on the authentication plugin and the authentication strategy.

## Signature

```go
func (a *Auth) UpdateMyCredentials(strategy string, credentials json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | the strategy to use
| `credentials` | json.RawMessage | the new credentials
| `options`  | QueryOptions    | QueryOptions object containing query options


### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | bool | Make this request queuable or not | `true`  |


## Return

A JSON representing the new credentials and an error or `nil`.

## Usage

[snippet=update-my-credentials]
