---
layout: sdk.html.hbs
title: getMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
---

# getMyRights

Returns the rights for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```cpp
std::vector<std::unique_ptr<UserRight>> getMyRights(query_options *options=nullptr);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `options`  | query_options*    | A pointer to a `kuzzleio::query_options` containing query options | no

### **Options**

Additional query options

| Property     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | bool | Make this request queuable or not | `true`

## Return

A std::vector of `user_right*`. The `user_right` structure contain:

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `controller` | const char* | Controller on wich the rights are applied |
| `action` | const char* | Action on wich the rights are applied |
| `index` | const char* | Index on wich the rights are applied |
| `collection` | const char* | Collection on wich the rights are applied |
| `value` | const char* | Rights (`allowed|denied|conditional`) |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get-my-rights]
