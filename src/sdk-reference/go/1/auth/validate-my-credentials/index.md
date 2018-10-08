---
layout: sdk.html.hbs
algolia: true
title: ValidateMyCredentials
description: Validate the current user's credentials for the specified `<strategy>`.
order: 200
---

# ValidateMyCredentials

Validate the current user's credentials for the specified `<strategy>`. The `result` field is `true` if the provided credentials are valid; otherwise an error is triggered. This route does not actually create or modify the user credentials. The credentials to send will depend on the authentication plugin and authentication strategy.

## Signature

```go
func (a *Auth) ValidateMyCredentials(strategy string, credentials json.RawMessage, options types.QueryOptions) (bool, error)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | the strategy to use
| `credentials` | string | the new credentials
| `options`  | QueryOptions | QueryOptions object containing query options


### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |


## Return

## Usage

[snippet=validate-my-credentials]
