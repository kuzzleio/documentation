---
layout: sdk.html.hbs
title: updateSpecifications
description: Update the validation specifications
---

# updateSpecifications

The updateSpecifications method allows you to create or update the validation specifications for one or more index/collection pairs.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```cpp
std::string updateSpecifications(const std::string& index, const std::string& collection, const std::string& specifications, kuzzleio::query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>const std::string&</pre> | Index name    | 
| `collection` | <pre>const std::string&</pre> | Collection name    |
| `specifications` | const std::string& | Specification in JSON format | yes
| `options` | <pre>kuzzleio::query_options*</pre> | Query options    | 

### specifications

A JSON string representing the specifications.  

The JSON must follow the [Specification Structure]({{ site_base_path }}guide/1/datavalidation/schema/):

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

A JSON string representing the specifications

## Usage

[snippet=update-specifications]
