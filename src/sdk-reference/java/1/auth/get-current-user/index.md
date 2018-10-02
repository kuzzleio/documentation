---
layout: sdk.html
algolia: true
title: getCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`
order: 200
---

# getCurrentUser

Returns the profile object for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```java
public User getCurrentUser() throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException, PartialException, PreconditionException, UnauthorizedException;
```

## Return

A User object with setters and getters containing:

| Option     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `id` | java.lang.String | The user ID |
| `content` | java.lang.String | The user content |
| `profileIds` | []string | An array containing the profile ids |

## Usage

[snippet=get-current-user]
