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

```cpp
bool validateSpecifications(const std::string& specifications, query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `specifications` | const std::string& | Specification to validate in JSON format | yes
| ``options`` | kuzzleio::query_options* | Query options    | no  |

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
