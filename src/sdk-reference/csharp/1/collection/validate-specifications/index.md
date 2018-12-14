---
layout: sdk.html.hbs
title: validateSpecifications
description: Validate specifications format
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store nor modify the existing specification.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```csharp
public validation_response validateSpecifications(string index, string collection, string specifications);
public validation_response validateSpecifications(string index, string collection, string specifications, QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| `specifications` | string | Specification to validate in JSON format | yes
| ``options`` | Kuzzleio::QueryOptions | Query options    | no  |

### **specifications**

A JSON representation of the specifications.

The JSON must follow the [Specification Structure]({{ site_base_path }}validation-reference/schema):

```json
{
  "strict": "<bool>",
  "fields": {
    // ... specification for each field
  }
}
```

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

An allocated `validation_response` structure which contain information about the specifications validity.

| Property   | Type    | Description        |
| ---------- | ------- | --------------------- |
| `valid` | bool | Specification validity |
| `details` | const char * const * | Array of string with details about each specification errors |
| `Description` | const char * | General error message |

## Usage

[snippet=validate-specifications]
