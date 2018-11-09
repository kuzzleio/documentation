---
layout: sdk.html.hbs
algolia: true
title: validateSpecifications
description: Validate specifications format
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store nor modify the existing specification.  

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```java
io.kuzzle.sdk.ValidationResponse validateSpecifications((String index, String collection, String specifications) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException;
io.kuzzle.sdk.ValidationResponse validateSpecifications((String index, String collection, String specifications, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException;
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | java.lang.String | Index name    | yes  |
| ``collection`` | java.lang.String | Collection name    | yes  |
| `specifications` | java.lang.String | Specification to validate in JSON format | yes  |
| `options` | io.kuzzle.sdk.QueryOptions | The query options | no       |

### **specifications**

A JSON representation of the specifications.  

The JSON must follow the [Specification Structure]({{ site_base_path }}validation-reference/schema):

```json
{
  "strict": "<boolean>",
  "fields": {
    // ... specification for each field
  }
}
```

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

A `io.kuzzle.sdk.ValidationResponse` which contain informations about the specifications validity.  
These properties are accessible with the standard getters.

| Property   | Type    | Description        |
| ---------- | ------- | --------------------- |
| `valid` | boolean | Specification validity |
| `details` | String[] | Details about each specification errors |
| `description` | String | General error message |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=validate-specifications]
