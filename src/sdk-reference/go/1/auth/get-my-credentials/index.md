---
layout: sdk.html.hbs
algolia: true
title: GetMyCredentials
description:
algolia: true
---

# GetMyCredentials

Returns the current user's credential information for the specified `<strategy>`. The data returned will depend on the specified strategy. The result can be an empty object.

## Signature

```go
func (a *Auth) GetMyCredentials(strategy string, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `strategy` | string | the strategy to use    | yes
| `options`  | QueryOptions    | QueryOptions object containing query options | yes

### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns a string representing a JSON with the credentials for the provided authentication strategy and an error or nil.

## Usage

[snippet=get-my-credentials]
