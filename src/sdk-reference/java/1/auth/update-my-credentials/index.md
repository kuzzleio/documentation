---
layout: sdk.html.hbs
title: updateMyCredentials
description: Update the current user's credentials for the specified `<strategy>`.
---

# updateMyCredentials

Update the current user's credentials for the specified `<strategy>`. The credentials to send will depend on the authentication plugin and the authentication strategy.

## Signature

```java
public String updateMyCredentials(
  String,
  String,
  io.kuzzle.sdk.QueryOptions
);
public String updateMyCredentials(
  String,
  String
);

```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | String | the strategy to use
| `credentials` | String | the new credentials
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing query options


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

A String representing a JSON Object of the updated credentials.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=update-my-credentials]
