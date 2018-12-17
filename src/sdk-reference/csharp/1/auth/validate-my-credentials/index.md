---
layout: sdk.html.hbs
title: validateMyCredentials
description: Validate the current user's credentials for the specified `<strategy>`.
---

# validateMyCredentials

Validate the current user's credentials for the specified `<strategy>`. The `result` field is `true` if the provided credentials are valid; otherwise an error is triggered. This route does not actually create or modify the user credentials. The credentials to send will depend on the authentication plugin and authentication strategy.

## Signature

```csharp
public bool validateMyCredentials(string strategy, string credentials);
public bool validateMyCredentials(string strategy, string credentials, QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | the strategy to use
| `credentials` | string | the new credentials
| `options`  | query_options*    | A `Kuzzleio::QueryOptions` containing query options


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |


## Return

A bool.

## Usage

[snippet=validate-my-credentials]
