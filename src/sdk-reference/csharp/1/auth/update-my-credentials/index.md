---
layout: sdk.html.hbs
title: updateMyCredentials
description: Update the current user's credentials for the specified `<strategy>`.
---

# updateMyCredentials

Update the current user's credentials for the specified `<strategy>`. The credentials to send will depend on the authentication plugin and the authentication strategy.

## Signature

```csharp
public string updateMyCredentials(string strategy, string credentials);
public string updateMyCredentials(string strategy, string credentials, QueryOptions options);
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


## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Return

A string representing a JSON object of the new credentials.

## Usage

[snippet=update-my-credentials]
