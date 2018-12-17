---
layout: sdk.html.hbs
title: getMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
---

# getMyRights

Returns the rights for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__shared_ptrT_UserRight_t_t getMyRights();
public SWIGTYPE_p_std__vectorT_std__shared_ptrT_UserRight_t_t getMyRights(QueryOptions options);
```

### options

Additional query options:

| Property     | Type    | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A [UserRight]({{ site_base_path }}sdk-reference/csharp/1/user-right/) object.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=get-my-rights]
