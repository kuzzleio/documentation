---
layout: sdk.html.hbs
algolia: true
title: getMyCredentials
description:
---

# getMyCredentials

Returns the current user's credential information for the specified `<strategy>`. The data returned will depend on the specified strategy. The result can be an empty object.

## Signature

```java
public String getMyCredentials(
  String,
  io.kuzzle.sdk.QueryOptions
);
public String getMyCredentials(
  String
);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | String | the strategy to use
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing query options

### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns a JSON with the credentials for the provided authentication strategy and an error or nil.


## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=get-my-credentials]
