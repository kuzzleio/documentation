---
layout: sdk.html.hbs
title: updateSpecifications
description: Update the validation specifications
---

# updateSpecifications

The updateSpecifications method allows you to create or update the validation specifications for one or more index/collection pairs.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```csharp
public string updateSpecifications(string index, string collection, string specifications);
public string updateSpecifications(string index, string collection, string specifications, QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | string | Index name    | yes  |
| ``collection`` | string | Collection name    | yes  |
| `specifications` | string | Specification in JSON format | yes
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

Return a string containing a JSON representation of the specifications

## Usage

[snippet=update-specifications]
