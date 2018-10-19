---
layout: sdk.html.hbs
algolia: true
title: getMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
order: 200
---

# getMyRights

Returns the rights for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```cpp
user_right* getMyRights(query_options *options=nullptr);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `options`  | query_options*    | A pointer to a `query_options` containing query options | no

### **Options**

Additional query options

| Property     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | bool | Make this request queuable or not | `true`

## Return

A pointer to a user_right object containing:

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `controller` | const char* | The controller on wich the rights are applied |
| `action` | const char* | The action on wich the rights are applied |
| `index` | const char* | The index on wich the rights are applied |
| `collection` | const char* | The collection on wich the rights are applied |
| `value` | const char* | The rights |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get-my-rights]
