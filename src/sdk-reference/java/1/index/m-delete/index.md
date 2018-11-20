---
layout: sdk.html.hbs
algolia: true
title: mDelete
description: Deletes multiple indexes
---


# mDelete

Deletes multiple indexes at once.

## Signature

```java
io.kuzzle.sdk.StringVector mDelete(io.kuzzle.sdk.StringVector indexes) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException;
io.kuzzle.sdk.StringVector mDelete(io.kuzzle.sdk.StringVector indexes, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException;
```

## Arguments

| Arguments | Type         | Description           | Required |
| --------- | ------------ | --------------------- | -------- |
| `indexes` | StringVector | List of indexes names | yes      |
| `options` | io.kuzzle.sdk.QueryOptions | The query options     | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns an `io.kuzzle.sdk.StringVector` containing the list of indexes names deleted (more details about [StringVector]({{ site_base_path }}sdk-reference/essentials/stringvector))

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=mDelete]
