---
layout: sdk.html
algolia: true
title: validateSpecifications
description: Validate specifications format
order: 200
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store nor modify the existing specification.  

## Signature

```java
public boolean validateSpecifications(String specifications) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException;
public boolean validateSpecifications(String specifications, QueryOptions options) throws BadRequestException, ForbiddenException, GatewayTimeoutException, InternalException, ServiceUnavailableException;
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `specifications` | String | Specification to validate in JSON format | yes  |
| `options` | QueryOptions | The query options | no       |

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

A boolean indicating whether the specifications are correct or not.

## Usage

[snippet=validate-specifications]
