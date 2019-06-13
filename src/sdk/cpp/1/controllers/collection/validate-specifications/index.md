---
code: true
type: page
title: validateSpecifications
description: Validate specifications format
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store nor modify the existing specification.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```cpp
kuzzleio::validation_response* validateSpecifications(
    const std::string& index,
    const std::string& collection,
    const std::string& specifications);

kuzzleio::validation_response* validateSpecifications(
    const std::string& index,
    const std::string& collection,
    const std::string& specifications,
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments        | Type                        | Description                                               |
| ---------------- | --------------------------- | --------------------------------------------------------- |
| `index`          | `const std::string&`        | Index name                                                |
| `collection`     | `const std::string&`        | Collection name                                           |
| `specifications` | `const std::string&`        | JSON string representating the specifications to validate |
| `options`        | `kuzzleio::query_options\*` | Query options                                             |

### specifications

A JSON string representing the specifications the specifications.

The JSON must follow the [Specification Structure](/core/1/guides/cookbooks/datavalidation):

```json
{
  "strict": "true",
  "fields": {
    "licence": {
      "mandatory": true,
      "type": "string"
    }
    // ... specification for each field
  }
}
```

### options

Additional query options

| Property   | Type<br/>(default) | Description                                                                  |
| ---------- | ------------------ | ---------------------------------------------------------------------------- |
| `queuable` | `bool`(`true`)     | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A `kuzzleio::validation_response` object which contain information about the specifications validity.

| Property      | Type                     | Description                                                  |
| ------------- | ------------------------ | ------------------------------------------------------------ |
| `valid`       | `bool`                   | Specification validity                                       |
| `details`     | `const char \* const \*` | Array of string with details about each specification errors |
| `description` | `const char \*`          | General error message                                        |

## Usage

<<< ./snippets/validate-specifications.cpp
