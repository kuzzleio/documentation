---
layout: sdk.html
algolia: true
title: count
description: Count documents
order: 200
---

# count

Given some filters, gets the number of matching documents from Kuzzle.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/query-dsl.html) syntax.

## Signature

```java
int count(java.lang.String inde, java.lang.String collection, java.lang.String body, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
int count(java.lang.String index, java.lang.String collection, java.lang.String body) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `body` | String | A JSON string representing the query to match |
| `options` | io.kuzzle.sdk.QueryOptions | The query options. |

###### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `includeTrash` | boolean | If set to `true`, includes the documents from the trash | `false`  |

## Return

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=count]
