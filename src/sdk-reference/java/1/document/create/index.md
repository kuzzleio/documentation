---
layout: sdk.html
algolia: true
title: create
description:
order: 200
---

# create

Create a new document in Kuzzle

Throws if a document with the same given id already exists in Kuzzle.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Signature

```java
java.lang.String create(java.lang.String, java.lang.String, java.lang.String, java.lang.String, io.kuzzle.sdk.QueryOptions) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
java.lang.String create(java.lang.String, java.lang.String, java.lang.String, java.lang.String) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | String | Index name | yes |
| `collection` | String | Collection name | yes |
| `id` | String | Optional document id. If set to a blank string, will use a auto-generated id | yes |
| `body` | String | A JSON string containing the body of the document | yes |
| `options` | io.kuzzle.sdk.QueryOptions | The query options. | no |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | String | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=create]
