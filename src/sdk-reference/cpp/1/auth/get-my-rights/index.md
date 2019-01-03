---
layout: sdk.html.hbs
title: getMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
---

# getMyRights

Returns the rights for the currently logged in user within the SDK instance.

## Signature

```cpp
std::vector<std::shared_ptr<kuzzleio::kuzzleio::UserRight>> getMyRights();

std::vector<std::shared_ptr<kuzzleio::kuzzleio::UserRight>> getMyRights(
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `options`  | <pre>kuzzleio::query_options\*</pre>  | Optional query options |

### options

Additional query options:

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A vector of pointer to [kuzzleio::UserRight]({{ site_base_path }}sdk-reference/cpp/1/user-right/) object.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get-my-rights]
