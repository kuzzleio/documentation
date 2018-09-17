---
layout: sdk.html
algolia: true
title: truncate
description: Remove all documents from collection
order: 200
---

# truncate

Remove all documents from a collection while keeping the associated mapping.  
It is faster than deleting all documents from a collection.

## Signature

```java
public void truncate(String index, String collection) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException;
public void truncate(String index, String collection, QueryOptions) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException;
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``options`` | QueryOptions | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Usage

[snippet=truncate]
