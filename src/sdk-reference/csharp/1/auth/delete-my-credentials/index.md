---
layout: sdk.html.hbs
title: deleteMyCredentials
description: Delete the current user's credentials for the specified strategy
---

# deleteMyCredentials

Delete the current user's credentials for the specified `<strategy>`. If the credentials that generated the current JWT are removed, the user will remain logged in until he logs out or his session expires, after that they will no longer be able to log in with the deleted credentials.

## Signature

```csharp
public void deleteMyCredentials(string strategy);
public void deleteMyCredentials(string strategy, QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `strategy` | string | the strategy to use    | yes
| `options`  | query_options\*    | A `Kuzzleio::QueryOptions` containing query options | no       |


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=delete-my-credentials]
