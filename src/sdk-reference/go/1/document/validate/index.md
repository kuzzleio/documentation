---
layout: sdk.html.hbs
algolia: true
title: validate
description:
order: 200
---

# Validate

Validates data against existing validation rules. 

Note that if no validation specifications are set for the `<index>`/`<collection>`, the document will always be valid.

This request does **not** store or publish the document.

## Arguments

```go
Validate(
  index string, 
  collection string, 
  body json.RawMessage, 
  options types.QueryOptions
) (bool, error)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>string</pre> | A JSON string containing the body of the document |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns a boolean value set to true if the document is valid and false otherwise.

## Usage

[snippet=validate]
