---
layout: sdk.html.hbs
algolia: true
title: validateMyCredentials
description: Validate the current user's credentials for the specified `<strategy>`.
algolia: true
---

# validateMyCredentials

Validate the current user's credentials for the specified `<strategy>`. The `result` field is `true` if the provided credentials are valid; otherwise an error is triggered. This route does not actually create or modify the user credentials. The credentials to send will depend on the authentication plugin and authentication strategy.

## Signature

```java
public boolean validateMyCredentials(
  String,
  String,
  io.kuzzle.sdk.QueryOptions
);
public boolean validateMyCredentials(
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

A boolean

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=validate-my-credentials]
