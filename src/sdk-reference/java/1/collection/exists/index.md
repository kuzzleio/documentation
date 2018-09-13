---
layout: sdk.html
algolia: true
title: exists
description: Check if collection exists
order: 200
---

# exists

Check if a collection exists in Kuzzle.

## Signature

```java
public boolean exists(String index, String collection) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException;
public boolean exists(String index, String collection, QueryOptions options) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException;
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``options`` | QueryOptions | An object containing query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

True if the collection exists

## Usage

[snippet=exists]
