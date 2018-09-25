---
layout: sdk.html
algolia: true
title: deleteMyCredentials
description: Delete the current user's credentials for the specified strategy
order: 200
---

# deleteMyCredentials

Delete the current user's credentials for the specified `<strategy>`. If the credentials that generated the current JWT are removed, the user will remain logged in until he logs out or his session expires, after that they will no longer be able to log in with the deleted credentials.

## Signature

```java
public void deleteMyCredentials(String, QueryOptions) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException, PartialException, PreconditionException, UnauthorizedException;
public void deleteMyCredentials(String) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException, PartialException, PreconditionException, UnauthorizedException;
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `strategy` | java.lang.String | the strategy to use    | yes
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing query options. | no       |


### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=delete-my-credentials]
