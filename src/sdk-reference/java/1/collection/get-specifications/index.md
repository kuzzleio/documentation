---
layout: sdk.html
algolia: true
title: getSpecifications
description: Returns the validation specifications
order: 200
---

# getSpecifications

Returns the validation specifications associated to the collection.

## Signature

```java
  public String getSpecifications(String index, String collection) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException;
  public String getSpecifications(String index, String collection, QueryOptions) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException, NotFoundException;
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``options`` | QueryOptions | Query options    | no  |

###### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Return a string in JSON format representing the validation specifications.

## Usage

[snippet=get-specifications]
