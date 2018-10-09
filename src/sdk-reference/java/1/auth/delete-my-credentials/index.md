---
layout: sdk.html
algolia: true
title: deleteMyCredentials
description: Delete the current user's credentials for the specified strategy
order: 200
---

# deleteMyCredentials

Delete the current user's credentials for the specified `<strategy>`. If the credentials that generated the current JWT are removed, the user will remain logged in until he logs out or his session expires, after that they will no longer be able to log in with the deleted credentials.

## Signature

```java
public void deleteMyCredentials(
  String,
  io.kuzzle.sdk.QueryOptions
);
public void deleteMyCredentials(
  String
);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | String | the strategy to use
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing query options.


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=delete-my-credentials]
