---
layout: sdk.html.hbs
algolia: true
title: update
description:
order: 200
---

# update

Update a document in Kuzzle.

Only documents in the persistent data storage layer can be updated.

Conflicts may occur if the same document gets updated multiple times within a short time on a database cluster. When this happens, Kuzzle answers with an error that clients have to handle.  
You may set the `retryOnConflict` optional argument with a positive integer, asking Kuzzle to retry updating the document that number of times before rejecting the request with an error.

## Signature

```java
java.lang.String update(java.lang.String, java.lang.String, java.lang.String, java.lang.String, io.kuzzle.sdk.QueryOptions) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
java.lang.String update(java.lang.String, java.lang.String, java.lang.String, java.lang.String) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
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
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | String | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |
| `retryOnConflict` | int | The number of times the database layer should retry in case of version conflict | 0 |

## Return

Returns a JSON string containing the document update result.

| Name | Type | Description
| --- | --- | ---
| _id | string | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| result | string | set to `updated` in case of success

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=update]
