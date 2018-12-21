---
layout: sdk.html.hbs
title: validateSpecifications
description: Validate specifications format
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store nor modify the existing specification.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```cpp
kuzzleio::validation_response* validateSpecifications(const std::string& index, const std::string& collection, const std::string& specifications, query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>const std::string&</pre> | Index name    | 
| `collection` | <pre>const std::string&</pre> | Collection name    |
| `specifications` | <pre>const std::string&<pre> | JSON string representating the specifications to validate |
| `options` | <pre>kuzzleio::query_options*</pre> | Query options    | 

### specifications

A JSON string representing the specifications the specifications.

The JSON must follow the [Specification Structure]({{ site_base_path }}validation-reference/schema):

```json
{
  "strict": "true",
  "fields": {
    "licence": {
      "mandatory": true,
      "type": "string"
    },
    // ... specification for each field
  }
}
```

### options

Additional query options

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A `kuzzleio::validation_response` object which contain information about the specifications validity.

| Property   | Type    | Description        |
| ---------- | ------- | --------------------- |
| `valid` | <pre>bool</pre> | Specification validity |
| `details` | <pre>const char * const *</pre> | Array of string with details about each specification errors |
| `description` | <pre>const char *</pre> | General error message |

## Usage

[snippet=validate-specifications]
