---
layout: sdk.html
algolia: true
title: mCreate
description:
order: 200
---

# mCreate

Creates new documents in the persistent data storage.

Returns a partial error (with status 206) if one or more documents fail to create.


## Signature

```java
java.lang.String mCreate(java.lang.String index, java.lang.String collection, java.lang.String body, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
java.lang.String mCreate(java.lang.String index, java.lang.String collection, java.lang.String body) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `body` | String | A JSON string containing the documents to create |
| `options` | io.kuzzle.sdk.QueryOptions | The query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | String | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns an JSON string containing the created documents.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=m-create]
