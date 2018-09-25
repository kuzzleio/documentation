---
layout: sdk.html
algolia: true
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
order: 200
---

# credentialsExist

## Signature

```java
public boolean credentialsExist(String, QueryOptions) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException, PartialException, PreconditionException, UnauthorizedException;
public boolean credentialsExist(String) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException, PartialException, PreconditionException, UnauthorizedException;
```

## Arguments

| Arguments  | Type             | Description                                             | Required |
| ---------- | ---------------- | ------------------------------------------------------- | -------- |
| `strategy` | java.lang.String      | Strategy to use                                         | yes      |
| `options` | io.kuzzle.sdk.QueryOptions | An object containing query options. | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Return

True if exists, false if not.

## Usage

[snippet=credentials-exist]