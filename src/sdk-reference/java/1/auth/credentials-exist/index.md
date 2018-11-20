---
layout: sdk.html.hbs
algolia: true
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
---


# credentialsExist

## Signature

```java
public boolean credentialsExist(
  String,
  io.kuzzle.sdk.QueryOptions
);
public boolean credentialsExist(
  String
);
```

## Arguments

| Arguments  | Type             | Description
| | `strategy` | String      | Strategy to use
| `options` | io.kuzzle.sdk.QueryOptions | An object containing query options

### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Return

True if exists, false if not.

## Usage

[snippet=credentials-exist]
