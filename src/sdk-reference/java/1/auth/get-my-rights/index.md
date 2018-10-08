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

```java
public io.kuzzle.sdk.UserRight[] getMyRights(
  io.kuzzle.sdk.QueryOptions
);
public io.kuzzle.sdk.UserRight[] getMyRights();
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing query options.

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

An array of UserRight containing:

| Option     | Type    | Description                      
| ---------- | ------- | ---------------------------------
| `controller` | java.lang.String | The controller on wich the rights are applied |
| `action` | java.lang.String | The action on wich the rights are applied |
| `index` | java.lang.String | The index on wich the rights are applied |
| `collection` | java.lang.String | The collection on wich the rights are applied |
| `value` | java.lang.String | The rights |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=get-my-rights]
