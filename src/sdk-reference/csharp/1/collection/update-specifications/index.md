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

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>string</pre> | Index name    | 
| `collection` | <pre>string</pre> | Collection name    |
| `specifications` | string | Specification in JSON format | yes
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options    | 

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
