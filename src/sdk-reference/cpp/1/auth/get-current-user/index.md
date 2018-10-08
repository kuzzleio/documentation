---
layout: sdk.html.hbs
algolia: true
title: getCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`
order: 200
---

# getCurrentUser

Returns the profile object for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```cpp
kuzzle_user* getCurrentUser();
```

## Return

A pointer to a kuzzle_user object containing:

| Option     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `id` | const char* | The user ID |
| `content` | const char* | The user content |
| `profile_ids` | char** | An array containing the profile ids |
| `profile_ids_length` | size_t | The size of the profile_ids array |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=get-current-user]
