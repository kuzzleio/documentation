---
layout: sdk.html.hbs
title: getCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`
---

# getCurrentUser

Returns the profile object for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Arguments

```cpp
User getCurrentUser();
```

## Return

A [User]({{ site_base_path }}sdk-reference/cpp/1/user/) object.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get-current-user]
