---
layout: sdk.html.hbs
algolia: true
title: createMyCredentials
description: Create the current user's credentials for the specified `<strategy>`.
---

# createMyCredentials

Create the current user's credentials for the specified `<strategy>`.

## Signature

```java
public String createMyCredentials(
  String,
  String, 
  io.kuzzle.sdk.QueryOptions
);
public String createMyCredentials(
  String,
  String
);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `content` | String | the new credentials
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing Query options


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Return

A User object.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=create-my-credentials]
