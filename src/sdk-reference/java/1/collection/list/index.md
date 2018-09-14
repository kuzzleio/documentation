---
layout: sdk.html
algolia: true
title: list
description: Returns the collection list of an index
order: 200
---

# list

Returns the complete list of realtime and stored data collections in requested index sorted by name in alphanumerical order.  
The `from` and `size` arguments allow pagination. They are returned in the response if provided.


## Signature

```java
public String list(String index) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException;
public String list(String index, QueryOptions queryOptions) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException;
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
| `from` | int | Start of returned list | `0` |
| `size` | int | Size of returned list | `all` |

## Return

Returns a string containing a JSON representation of the API response.

## Usage

[snippet=list]
