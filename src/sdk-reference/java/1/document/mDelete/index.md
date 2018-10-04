---
layout: sdk.html
algolia: true
title: mDelete
description:
order: 200
---

# mDelete

Deletes documents in the persistent data storage.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more document can not be deleted.

The optional parameter `refresh` can be used
with the value `wait_for` in order to wait for the document indexation (indexed documents are available for `search`).

## Signature

```java
io.kuzzle.sdk.StringVector mDelete(java.lang.String index, java.lang.String collection, io.kuzzle.sdk.StringVector ids, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
io.kuzzle.sdk.StringVector mDelete(java.lang.String index, java.lang.String collection, io.kuzzle.sdk.StringVector ids) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `ids` | io.kuzzle.sdk.StringVector | The ids of the documents to delete |
| `options` | io.kuzzle.sdk.QueryOptions | The query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns an `io.kuzzle.sdk.StringVector` containing the list of the deleted document ids (more details about [StringVector]({{ site_base_path }}sdk-reference/essentials/stringvector))

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=m-delete]
