---
layout: sdk.html.hbs
title: createMyCredentials
description: Create the current user's credentials for the specified `<strategy>`.
---

# createMyCredentials

Create the current user's credentials for the specified `<strategy>`.

## Signature

```csharp
public string createMyCredentials(string strategy, string credentials);
public string createMyCredentials(string strategy, string credentials, QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | the strategy to use
| `credentials` | string | the new credentials
| `options`  | Kuzzleio::QueryOptions    | A `Kuzzleio::QueryOptions` containing query options


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |


## Return

A string representing a JSON object of the new credentials.

## Usage

[snippet=create-my-credentials]
