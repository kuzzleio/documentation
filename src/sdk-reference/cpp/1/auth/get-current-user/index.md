---
layout: sdk.html.hbs
title: getCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`
---

# getCurrentUser

Returns informations about the user currently loggued with the SDK instance.

## Signature

```cpp
kuzzleio::User getCurrentkuzzleio::User()
```

## Return

A [kuzzleio::User]({{ site_base_path }}sdk-reference/cpp/1/user/) object.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get-current-user]
