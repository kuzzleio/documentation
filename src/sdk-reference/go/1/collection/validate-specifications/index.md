---
layout: sdk.html.hbs
algolia: true
title: validateSpecifications
description: Validate specifications format
order: 200
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store nor modify the existing specification.  

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```go
ValidateSpecifications(specifications json.RawMessage, options types.QueryOptions) (types.ValidationResponse, error)
```

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `specifications` | json.RawMessage | Collection data mapping in JSON format  | yes  |
| `options` | QueryOptions | Query options. | no       |

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
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

A `types.ValidationResponse` which contain informations about the specifications validity.  

| Property   | Type    | Description        |
| ---------- | ------- | --------------------- |
| `Valid` | bool | Specification validity |
| `Details` | []string | Details about each specification errors |
| `Description` | string | General error message |

## Usage

[snippet=validate-specifications]
