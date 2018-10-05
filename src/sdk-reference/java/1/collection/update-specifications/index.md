---
layout: sdk.html.hbs
algolia: true
title: updateSpecifications
description: Update the validation specifications
order: 200
---

# updateSpecifications

The updateSpecifications method allows you to create or update the validation specifications for one or more index/collection pairs.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```java
public String updateSpecifications(java.lang.String index, java.lang.String collection, java.lang.String specifications) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.NotFoundException;
public String updateSpecifications(java.lang.String index, java.lang.String collection, java.lang.String specifications, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.NotFoundException;
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | java.lang.String | Index name    | yes  |
| ``collection`` | java.lang.String | Collection name    | yes  |
| ``specifications`` | java.lang.String | Specifications in JSON format  | yes  |
| `options` | io.kuzzle.sdk.QueryOptions | The query options | no       |

### **specifications**

A JSON representation of the specifications.  

The JSON must follow the [Specification Structure]({{ site_base_path }}validation-reference/schema):

```json
{
  "myindex": {
    "mycollection": {
      "strict": "<true|false>",
      "fields": {
        // ... specification for each field
      }
    }
  }
}
```

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Return a string containing a JSON representation of the specifications

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=update-specifications]
