---
layout: sdk.html.hbs
title: getMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
---

# getMyRights

Returns the rights for the currently logged in user within the SDK instance.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__shared_ptrT_kuzzleio__UserRight_t_t getMyRights();

public SWIGTYPE_p_std__vectorT_std__shared_ptrT_kuzzleio__UserRight_t_t getMyRights(
    query_options options);

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `options`  | <pre>Kuzzleio::QueryOptions\*</pre>  | Optional query options |

### options

Additional query options:

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A vector of pointer to [kuzzleio::UserRight]({{ site_base_path }}sdk-reference/csharp/1/user-right/) object.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=get-my-rights]
