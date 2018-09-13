---
layout: sdk.html
algolia: true
title: deleteSpecifications
description: Delete validation specifications for a collection
order: 200
---

# deleteSpecifications

Delete the validation specifications associated with the collection.  

## Signature

```java
  public void deleteSpecifications(String index, String collection) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException;
  public void deleteSpecifications(String index, String collection, QueryOptions queryOptions) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException;
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

## Usage

[snippet=delete-specifications]
