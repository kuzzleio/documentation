---
code: true
type: page
title: getCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`
---

# getCurrentUser

Returns informations about the user currently loggued with the SDK instance.

## Signature

```cpp
kuzzleio::User getCurrentkuzzleio::User();
```

## Return

A [kuzzleio::User](/sdk/cpp/1/core-classes/user/) object.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/error-handling).

## Usage

<<< ./snippets/get-current-user.cpp
