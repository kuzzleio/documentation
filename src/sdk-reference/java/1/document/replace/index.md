---
layout: sdk.html
algolia: true
title: replace
description:
order: 200
---

# replace

Replaces an existing document in the persistent data storage.
Only documents in the persistent data storage layer can be replaced.

## Signature

```java
java.lang.String replace(java.lang.String index, java.lang.String collection, java.lang.String id, java.lang.String body, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
java.lang.String replace(java.lang.String index, java.lang.String collection, java.lang.String id, java.lang.String body) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `id` | String | The document id |
| `body` | String | A JSON string containing the body of the document |
| `options` | io.kuzzle.sdk.QueryOptions | The query options |

###### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | Boolean | Make this request queuable or not | `true`  |
| `refresh` | String | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=replace]
