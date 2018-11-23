---
layout: sdk.html.hbs
algolia: true
title: exists
description: Check for index existence
algolia: true
---

# Exists

Checks if the given index exists in Kuzzle.

## Signature

```java
boolean exists(String index) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException;
boolean exists(String index, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException;
```

## Arguments

| Arguments | Type         | Description       | Required |
| --------- | ------------ | ----------------- | -------- |
| `index`   | String       | Index name        | yes      |
| `options` | io.kuzzle.sdk.QueryOptions | The query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns a `boolean` that indicate whether the index exists or not.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=exists]
