---
layout: sdk.html.hbs
algolia: true
title: list
description: List the indexes
order: 400
---

# List

Get the complete list of data indexes handled by Kuzzle.

## Signature

```java
io.kuzzle.sdk.StringVector list() throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException;
io.kuzzle.sdk.StringVector list(io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException;
```

## Arguments

| Arguments | Type         | Description       |
| --------- | ------------ | ----------------- |
| `options` | io.kuzzle.sdk.QueryOptions | The query options |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns an `io.kuzzle.sdk.StringVector` containing the list of indexes names present in Kuzzle (more details about [StringVector]({{ site_base_path }}sdk-reference/essentials/string-vector))

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=list]
