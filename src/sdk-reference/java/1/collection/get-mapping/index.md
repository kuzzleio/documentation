---
layout: sdk.html
algolia: true
title: getMapping
description: Return collection mapping
order: 200
---

# getMapping

Returns the mapping for the given `collection`.

## Signature

```java
  public String getMapping(String index, String collection) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException;
  public String getMapping(String index, String collection, QueryOptions queryOptions) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException;
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

## Return

Return a string containing the collection mapping in JSON format.

## Usage

[snippet=get-mapping]
