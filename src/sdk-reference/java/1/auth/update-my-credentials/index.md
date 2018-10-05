---
layout: sdk.html
algolia: true
title: updateMyCredentials
description: Update the current user's credentials for the specified `<strategy>`.
order: 200
---

# updateMyCredentials

Update the current user's credentials for the specified `<strategy>`. The credentials to send will depend on the authentication plugin and the authentication strategy.

## Signature

```java
public java.lang.String updateMyCredentials(java.lang.String, java.lang.String, io.kuzzle.sdk.QueryOptions) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
public java.lang.String updateMyCredentials(java.lang.String, java.lang.String) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;

```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | java.lang.String | the strategy to use
| `credentials` | java.lang.String | the new credentials
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing query options.


###### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

A String representing a JSON Object of the updated credentials.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=update-my-credentials]
