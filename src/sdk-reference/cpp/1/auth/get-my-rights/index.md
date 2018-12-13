---
layout: sdk.html.hbs
title: getMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
---

# getMyRights

Returns the rights for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Arguments

```cpp
std::vector<std::unique_ptr<UserRight>> 
  getMyRights(query_options *options=nullptr);
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `options`  | <pre>kuzzleio::query_options*</pre>  | Optional query options |

### options

Additional query options:

| Property     | Type    | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A [UserRight]({{ site_base_path }}sdk-reference/cpp/1/user-right/) object.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get-my-rights]
