---
layout: sdk.html.hbs
algolia: true
title: validateSpecifications
description: Validate specifications format
order: 200
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store nor modify the existing specification.  

## Signature

```go
ValidateSpecifications(body json.RawMessage, options types.QueryOptions) (bool, error)
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

A bool indicating whether the specifications are correct or not.

## Usage

[snippet=validate-specifications]
