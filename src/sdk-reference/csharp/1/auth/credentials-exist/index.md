---
layout: sdk.html.hbs
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
---

# credentialsExist

Check that the current user has credentials for the specified strategy.

## Signature

```csharp
public bool credentialsExist(string strategy);
public bool credentialsExist(string strategy, QueryOptions options);
```

## Arguments

| Arguments  | Type             | Description                                             | Required |
| ---------- | ---------------- | ------------------------------------------------------- | -------- |
| `strategy` | string      | Strategy to use                                         | yes      |
| `options`  | query_options\*    | A `Kuzzleio::QueryOptions` containing query options | no       |

### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |


## Return

True if exists, false if not.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=credentials-exist]
