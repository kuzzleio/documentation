---
layout: sdk.html.hbs
title: getMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
---

# getMyRights

Returns the rights for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```java
public io.kuzzle.sdk.UserRight[] getMyRights(
  io.kuzzle.sdk.QueryOptions
);
public io.kuzzle.sdk.UserRight[] getMyRights();
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing query options

### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

An array of `UserRight`. The `UserRight` class contain:

| Property     | Type    | Description
| ---------- | ------- | ---------------------------------
| `controller` | String | Controller on wich the rights are applied |
| `action` | String | Action on wich the rights are applied |
| `index` | String | Index on wich the rights are applied |
| `collection` | String | Collection on wich the rights are applied |
| `value` | String | Rights (`allowed|denied|conditional`) |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=get-my-rights]
