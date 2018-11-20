---
layout: sdk.html.hbs
algolia: true
title: query
description: Base method to send API query to Kuzzle
---


# query

Base method used to send queries to Kuzzle, following the [API Documentation]({{ site_base_path }}api/1).

<div class="alert alert-warning">
This is a low-level method, exposed to allow advanced SDK users to bypass high-level methods.
</div>

## Signature

```java
io.kuzzle.sdk.KuzzleResponse query(io.kuzzle.sdk.KuzzleRequest request) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.UnauthorizedException;
io.kuzzle.sdk.KuzzleResponse query(io.kuzzle.sdk.KuzzleRequest request, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.UnauthorizedException;
```

## Arguments

| Argument  | Type          | Description              | Required |
| --------- | ------------- | ------------------------ | -------- |
| `request` | io.kuzzle.sdk.KuzzleRequest | API request options      | yes      |
| `options` | io.kuzzle.sdk.QueryOptions  | Additional query options | no       |

### **request**

A `io.kuzzle.sdk.KuzzleRequest` object containing the properties required for the Kuzzle API request.
Each property can be accessed with standard getter/setter.
The following properties are the most common.

| Property     | Type   | Description                                                        | Required |
| ------------ | ------ | ------------------------------------------------------------------ | -------- |
| `controller` | String | Controller name                                                    | yes      |
| `action`     | String | Action name                                                        | yes      |
| `body`       | String | JSON query body for this action                                    | no       |
| `index`      | String | Index name for this action                                         | no       |
| `collection` | String | Collection name for this action                                    | no       |
| `id`         | String | id for this action                                                 | no       |
| `volatile`   | String | JSON string representing additional information to send to Kuzzle | no       |

### **options**

A `io.kuzzle.sdk.QueryOptions` object containing the additional options for this request.
Each property can be accessed with standard getter/setter.
The following properties are the most common.

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | true    |

## Return

A `io.kuzzle.sdk.KuzzleResponse` object containing the Kuzzle API response. See the [API Documentation]({{ site_base_path }}api/1).
The following properties are the most common.

| Property    | Type   | Description                         |
| ----------- | ------ | ----------------------------------- |
| `requestId` | String | Request unique id                   |
| `result`    | String | Raw JSON result                     |
| `error`     | String | Error message                       |
| `status`    | int    | Request status (eg: 200, 403, etc.) |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=query]
