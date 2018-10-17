---
layout: sdk.html.hbs
algolia: true
title: validate
description:
order: 200
---

# validate

Validates data against existing validation rules. 

Note that if no validation specifications are set for the `<index>`/`<collection>`, the document will always be valid.

This request does **not** store or publish the document.

## Signature

```go
Validate(index string, collection string, body json.RawMessage, options types.QueryOptions) (bool, error)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `body` | string | A JSON string containing the body of the document |
| `options` | types.QueryOptions | The query options |

###### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns a boolean value set to true if the document is valid and false otherwise.

## Usage

[snippet=validate]
